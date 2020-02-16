package org.hashcode.qualification2018.solvers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.hashcode.Solver;
import org.hashcode.qualification2018.model.Ride;
import org.hashcode.qualification2018.model.SelfDrivingInput;
import org.hashcode.qualification2018.model.SelfDrivingOutput;
import org.hashcode.qualification2018.model.Vehicle;

public class ByLatestFinishTimeAnyAvailableVehicleSolver implements Solver<SelfDrivingInput, SelfDrivingOutput> {

    private Map<Vehicle, List<Ride>> vehiclesRides;

    @Override
    public SelfDrivingOutput solve(SelfDrivingInput inputData) {
        vehiclesRides = initVehicles(inputData.getNumberOfVehicles());

        List<Ride> ridesPriorityQueue = inputData.getRides().stream()
                .sorted(Comparator.comparingInt(Ride::getLatestFinishTime))
                .collect(Collectors.toList());

        for (Ride ride : ridesPriorityQueue) {
            Optional<Vehicle> optionalVehicle = selectVehicle(ride);
            optionalVehicle.ifPresent(vehicle -> {
                vehicle.doRide(ride);
                ride.setStartTime(vehicle.getCurrentStartTime());
                vehiclesRides.get(vehicle).add(ride);
            });
        }
        return new SelfDrivingOutput(inputData.getBonusPoints(), vehiclesRides);
    }

    private Map<Vehicle, List<Ride>> initVehicles(int numberOfVehicles) {
        return IntStream.range(0, numberOfVehicles)
                .mapToObj(Vehicle::new)
                .collect(Collectors.toUnmodifiableMap(Function.identity(), vehicle -> new ArrayList<>()));
    }

    private Optional<Vehicle> selectVehicle(Ride ride) {
        return vehiclesRides.keySet().stream()
                .filter(vehicle -> ride.getLatestFinishTime() >
                        vehicle.getCurrentFinishTime() + vehicle.transferTime(ride.getStartPosition()) + ride.getDistance())
                .findAny();
    }

}
