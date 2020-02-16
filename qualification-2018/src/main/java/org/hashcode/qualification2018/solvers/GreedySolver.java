package org.hashcode.qualification2018.solvers;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hashcode.Solver;
import org.hashcode.qualification2018.model.Ride;
import org.hashcode.qualification2018.model.SelfDrivingInput;
import org.hashcode.qualification2018.model.SelfDrivingOutput;
import org.hashcode.qualification2018.model.VehicleRides;

public class GreedySolver implements Solver<SelfDrivingInput, SelfDrivingOutput> {

    private Map<Integer, VehicleRides> vehiclesRides;

    @Override
    public SelfDrivingOutput solve(SelfDrivingInput inputData) {
        vehiclesRides = new HashMap<>();
        for (int i = 0; i < inputData.getNumberOfVehicles(); i++) {
            vehiclesRides.put(i, new VehicleRides(i));
        }

        List<Ride> ridesPriorityQueue = inputData.getRides().stream()
                .sorted(Comparator.comparingInt(Ride::getLatestFinishTime))
                .collect(Collectors.toList());
        for (Ride ride : ridesPriorityQueue) {
            Optional<VehicleRides> optionalVehicleRides = chooseVehicle(ride);
            optionalVehicleRides.ifPresent(vehicleRides -> {
                int transferTime = vehicleRides.transferTime(ride.getStartPosition());
                int startTime = vehicleRides.getCurrentTime() + transferTime + vehicleRides.waitingTime(transferTime, ride.getEarliestStartTime());
                ride.setStartTime(startTime);
                vehicleRides.addRide(ride);
            });
        }
        return new SelfDrivingOutput(inputData.getBonusPoints(), List.copyOf(vehiclesRides.values()));
    }

    private Optional<VehicleRides> chooseVehicle(Ride ride) {
        return vehiclesRides.values().stream()
                .filter(vehicleRides -> ride.getLatestFinishTime() - ride.getDistance() -
                        vehicleRides.transferTime(ride.getStartPosition()) >= vehicleRides.getCurrentTime())
                .findAny();
    }

}
