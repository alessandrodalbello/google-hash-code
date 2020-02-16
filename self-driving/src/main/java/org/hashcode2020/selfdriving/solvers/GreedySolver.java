package org.hashcode2020.selfdriving.solvers;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hashcode2020.Solver;
import org.hashcode2020.selfdriving.model.Ride;
import org.hashcode2020.selfdriving.model.SelfDrivingInput;
import org.hashcode2020.selfdriving.model.SelfDrivingOutput;
import org.hashcode2020.selfdriving.model.Vehicle;

public class GreedySolver implements Solver<SelfDrivingInput, SelfDrivingOutput> {

    private Map<Integer, Vehicle> vehicles;

    @Override
    public SelfDrivingOutput solve(SelfDrivingInput inputData) {
        vehicles = new HashMap<>();
        for (int i = 0; i < inputData.getVehicles(); i++) {
            vehicles.put(i, new Vehicle(i));
        }

        List<Ride> priorityQueue = inputData.getRides().stream()
                .sorted(Comparator.comparingInt(Ride::getLatestFinishTime))
                .collect(Collectors.toList());
        for (Ride ride : priorityQueue) {
            Optional<Vehicle> optionalVehicle = chooseVehicle(ride);
            optionalVehicle.ifPresent(vehicle -> {
                int transferTime = vehicle.transferTime(ride.getStartPosition());
                int startTime = vehicle.getCurrentTime() + transferTime + vehicle.waitingTime(transferTime, ride.getEarliestStartTime());
                ride.setStartTime(startTime);
                vehicle.addRide(ride);
            });
        }
        return new SelfDrivingOutput(inputData.getBonus(), List.copyOf(vehicles.values()));
    }

    private Optional<Vehicle> chooseVehicle(Ride ride) {
        return vehicles.values().stream()
                .filter(vehicle -> ride.getLatestFinishTime() - ride.getDistance() - vehicle.transferTime(ride.getStartPosition()) >= vehicle.getCurrentTime())
                .findAny();
    }

}
