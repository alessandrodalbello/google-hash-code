package org.hashcode.qualification2021.solvers;

import org.hashcode.Solver;
import org.hashcode.qualification2021.model.*;

import java.util.*;
import java.util.stream.Collectors;


public class IgnorantSolver implements Solver<TrafficInput, TrafficOutput> {

    @Override
    public TrafficOutput solve(TrafficInput inputData) {
        int duration = inputData.getDuration();
        List<Car> sortedCars = inputData.getCars()
                .stream()
                .filter(car -> car.getTravelTime() <= duration)
                .sorted(Comparator.comparingInt(Car::getPathLength))
                .collect(Collectors.toList());

        Map<Street, Integer> times = new HashMap<>();

        for (Car car : sortedCars) {
            List<Street> path = car.getPath();
            for (Street street : path) {
                if (!times.containsKey(street)) {
                    times.put(street, 1);
                } else {
                    times.put(street, times.get(street)+1);
                }
            }
        }

        Map<Integer, Set<Street>> nodesMap = new HashMap<>();
        for (Street s: inputData.getStreets()){
            if (!nodesMap.containsKey(s.getEndIntersection())) {
                nodesMap.put(s.getEndIntersection(), new HashSet<>());
            }
            Set<Street> streets = nodesMap.get(s.getEndIntersection());
            streets.add(s);
        }

        List<Schedule> schedules = new ArrayList<>();
        for (Map.Entry<Integer, Set<Street>> entry : nodesMap.entrySet()) {
            List<IncomingStreet> incomingStreets = new ArrayList<>();
            for (Street street : entry.getValue()) {
                if (times.containsKey(street)) {
                    IncomingStreet incomingStreet = new IncomingStreet(street.getName(), times.get(street));
                    incomingStreets.add(incomingStreet);
                }
            }
            Schedule schedule = new Schedule(entry.getKey(), incomingStreets);
            schedules.add(schedule);
        }
        return new TrafficOutput(schedules);
    }

}