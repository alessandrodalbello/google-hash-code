package org.hashcode.qualification2021.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simulator {

    private final int duration;
    private final List<Car> cars;
    private final Map<Street, List<Car>> carsByStreet;
    private final Map<Car, CarMovement> carMovements;
    private final Schedule[] schedules;

    public Simulator(int duration, int numberOfIntersections, List<Car> cars) {
        this.duration = duration;
        this.cars = cars;
        this.carsByStreet = new HashMap<>();
        this.carMovements = new HashMap<>();

        for (Car car : cars) {
            Street street = car.getPath().get(0);
            carsByStreet.putIfAbsent(street, new ArrayList<>());
            carsByStreet.get(street).add(car);

            carMovements.put(car, new CarMovement(street, 0));
        }

        schedules = new Schedule[numberOfIntersections];
        for (int s = 0; s <= numberOfIntersections; s++) {
            Schedule schedule = new Schedule(s);
            schedules[s] = schedule;
        }
    }

    public List<Schedule> execute() {
//        List<Car> sortedCars = cars.stream()
//                .filter(car -> car.getTravelTime() <= duration)
//                .sorted(Comparator.comparingInt(Car::getPathLength))
//                .collect(Collectors.toList());
//
//        for (int t = 0; t <= duration; t++) {
//            for (Car car : sortedCars) {
//                Street street = car.getPath().get(0);
//                Schedule schedule = schedules[street.getEndIntersection()];
//                String streetName = street.getName();
//                Optional<IncomingStreet> optionalIncomingStreet = schedule.findIncomingStreetByName(streetName);
//                if (optionalIncomingStreet.isEmpty()) {
//                    IncomingStreet incomingStreet = new IncomingStreet(streetName, 1);
//                    schedule.addIncomingStreet(incomingStreet);
//                } else {
//                    IncomingStreet incomingStreet = optionalIncomingStreet.get();
//                    incomingStreet.increaseDuration(1);
//                }
//            }
//
//            for (Car car : carMovements.keySet()) {
//                CarMovement carMovement = carMovements.get(car);
//                if (carMovement.isArrived()) {
//                    Street street = carMovement.street;
//                    if (car.equals(carsByStreet.get(street).get(0))) {
//                        Schedule schedule = schedules[street.getEndIntersection()];
//                        schedule.
//                    }
//                }
//            }
//
//        }
        return null;
    }

    private static class CarMovement {
        private Street street;
        private int remaining;

        public CarMovement(Street street) {
            this(street, street.getLength());
        }

        public CarMovement(Street street, int remaining) {
            this.street = street;
            this.remaining = remaining;
        }

        public void move() {
            remaining = Math.max(0, remaining - 1);
        }

        public boolean isArrived() {
            return remaining == 0;
        }
    }
}
