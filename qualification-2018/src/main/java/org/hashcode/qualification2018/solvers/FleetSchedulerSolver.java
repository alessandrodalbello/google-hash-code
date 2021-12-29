package org.hashcode.qualification2018.solvers;

import java.util.ArrayList;
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

class FleetSchedulerSolver implements Solver<SelfDrivingInput, SelfDrivingOutput> {

    private final RidesSorter ridesSorter;
    private final VehicleSelector vehicleSelector;

    private Map<Vehicle, List<Ride>> vehiclesRides;

    public FleetSchedulerSolver(RidesSorter ridesSorter, VehicleSelector vehicleSelector) {
        this.ridesSorter = ridesSorter;
        this.vehicleSelector = vehicleSelector;
    }

    @Override
    public SelfDrivingOutput solve(SelfDrivingInput inputData) {
        vehiclesRides = initVehicles(inputData.getNumberOfVehicles());

        List<Ride> ridesByPriority = ridesSorter.sortByPriority(inputData.getRides());
        for (Ride ride : ridesByPriority) {
            Optional<Vehicle> optionalVehicle = vehicleSelector.selectVehicle(ride, vehiclesRides.keySet(), inputData.getBonusPoints());
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

}
