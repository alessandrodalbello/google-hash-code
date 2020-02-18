package org.hashcode.qualification2018.solvers;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

import org.hashcode.qualification2018.model.Ride;
import org.hashcode.qualification2018.model.Vehicle;

class MaximiseBonusVehicleSelector implements VehicleSelector {

    @Override
    public Optional<Vehicle> selectVehicle(Ride ride, Set<Vehicle> vehicles, int bonusPoints) {
        Optional<Vehicle> optionalVehicle = vehicles.stream()
                .filter(vehicle -> ride.getEarliestStartTime() >=
                        vehicle.getCurrentFinishTime() + vehicle.transferTime(ride.getStartPosition()))
                .min(Comparator.comparingInt(vehicle -> ride.getEarliestStartTime() - vehicle.transferTime(ride.getStartPosition())));
        if (optionalVehicle.isEmpty()) {
            optionalVehicle = vehicles.stream()
                    .filter(vehicle -> ride.getLatestFinishTime() >
                            vehicle.getCurrentFinishTime() + vehicle.transferTime(ride.getStartPosition()) + ride.getDistance())
                    .min(Comparator.comparingInt(vehicle -> vehicle.transferTime(ride.getStartPosition())));
        }
        return optionalVehicle;
    }

}
