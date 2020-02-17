package org.hashcode.qualification2018.solvers;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

import org.hashcode.qualification2018.model.Ride;
import org.hashcode.qualification2018.model.Vehicle;

class CloserVehicleSelector implements VehicleSelector {

    @Override
    public Optional<Vehicle> selectVehicle(Ride ride, Set<Vehicle> vehicles) {
        return vehicles.stream()
                .filter(vehicle -> ride.getLatestFinishTime() >
                        vehicle.getCurrentFinishTime() + vehicle.transferTime(ride.getStartPosition()) + ride.getDistance())
                .min(Comparator.comparingInt(vehicle -> vehicle.getCurrentPosition().distanceFrom(ride.getStartPosition())));
    }

}
