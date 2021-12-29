package org.hashcode.qualification2018.solvers;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

import org.hashcode.qualification2018.model.Ride;
import org.hashcode.qualification2018.model.Vehicle;

class ComplexVehicleSelector implements VehicleSelector {

    @Override
    public Optional<Vehicle> selectVehicle(Ride ride, Set<Vehicle> vehicles, int bonusPoints) {
        return vehicles.stream()
                .map(vehicle -> new VehicleRideScore(vehicle, ride, bonusPoints))
                .min(Comparator.comparingInt(VehicleRideScore::getScore).reversed()
                        .thenComparingInt(vehicleRideScore -> vehicleRideScore.getVehicle().getCurrentFinishTime())
                        .thenComparingInt(vehicleRideScore -> vehicleRideScore.getVehicle().transferTime(ride.getStartPosition()))
                ).map(VehicleRideScore::getVehicle);
    }

    private static class VehicleRideScore {

        private final Vehicle vehicle;
        private final int score;

        public VehicleRideScore(Vehicle vehicle, Ride ride, int bonusPoints) {
            this.vehicle = vehicle;

            boolean canCompleteRide = ride.getLatestFinishTime() >
                    vehicle.getCurrentFinishTime() + vehicle.transferTime(ride.getStartPosition()) + ride.getDistance();
            if (canCompleteRide) {
                boolean canGetBonus = ride.getEarliestStartTime() >=
                        vehicle.getCurrentFinishTime() + vehicle.transferTime(ride.getStartPosition());
                if (canGetBonus) {
                    score = ride.getDistance() + bonusPoints -
                            (ride.getEarliestStartTime() - vehicle.transferTime(ride.getStartPosition()) - vehicle.getCurrentFinishTime());
                } else {
                    score = ride.getDistance();
                }
            } else {
                score = 0;
            }
        }

        public Vehicle getVehicle() {
            return vehicle;
        }

        public int getScore() {
            return score;
        }

        @Override
        public String toString() {
            return VehicleRideScore.class.getSimpleName() + "{" +
                    "vehicle=" + vehicle +
                    ", score=" + score +
                    "}";
        }

    }

}
