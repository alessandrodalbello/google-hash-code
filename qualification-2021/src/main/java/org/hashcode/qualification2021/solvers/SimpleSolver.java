package org.hashcode.qualification2021.solvers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hashcode.Solver;
import org.hashcode.qualification2021.model.Car;
import org.hashcode.qualification2021.model.IncomingStreet;
import org.hashcode.qualification2021.model.Schedule;
import org.hashcode.qualification2021.model.Street;
import org.hashcode.qualification2021.model.TrafficInput;
import org.hashcode.qualification2021.model.TrafficOutput;

public class SimpleSolver implements Solver<TrafficInput, TrafficOutput> {

    @Override
    public TrafficOutput solve(TrafficInput inputData) {
        int duration = inputData.getDuration();
        List<Car> sortedCars = inputData.getCars()
                .stream()
                .filter(car -> car.getTravelTime() <= duration)
                .sorted(Comparator.comparingInt(Car::getPathLength))
                .collect(Collectors.toCollection(LinkedList::new));

        Schedule[] schedules = new Schedule[inputData.getNumberOfIntersections()];
        for (int s = 0; s < inputData.getNumberOfIntersections(); s++) {
            Schedule schedule = new Schedule(s);
            schedules[s] = schedule;
        }

        int streetIndex = 0;
        while (!sortedCars.isEmpty()) {
            Iterator<Car> carIterator = sortedCars.iterator();
            while (carIterator.hasNext()) {
                Car car = carIterator.next();
                Street street = car.getPath().get(streetIndex);
                Schedule schedule = schedules[street.getEndIntersection()];
                String streetName = street.getName();
                Optional<IncomingStreet> optionalIncomingStreet = schedule.findIncomingStreetByName(streetName);
                if (optionalIncomingStreet.isEmpty()) {
                    IncomingStreet incomingStreet = new IncomingStreet(streetName, 1);
                    schedule.addIncomingStreet(incomingStreet);
                } else {
                    IncomingStreet incomingStreet = optionalIncomingStreet.get();
                    if (incomingStreet.getDuration() < incomingStreet.getDuration()) {
                        incomingStreet.increaseDuration(1);
                    }
                }

                if ((streetIndex + 1) >= car.getPath().size()) {
                    carIterator.remove();
                }
            }
            streetIndex += 1;
        }
        List<Schedule> sch = Arrays.stream(schedules)
                .filter(s -> !s.getIncomingStreets().isEmpty())
                .collect(Collectors.toList());
        return new TrafficOutput(sch);
    }

}
