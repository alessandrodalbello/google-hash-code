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

        Map<Street, Integer> times = new HashMap();

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

        for (Map.Entry<Integer, Set<Street>> entry : nodesMap.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }




        String streetName = inputData.getStreets().get(0).getName();

        IncomingStreet incomingStreet = new IncomingStreet(streetName, 1);
        Schedule schedule = new Schedule(0, List.of(incomingStreet));
        return new TrafficOutput(inputData.getDuration(), inputData.getBonusPoints(), inputData.getCars(), List.of(schedule));
    }

}