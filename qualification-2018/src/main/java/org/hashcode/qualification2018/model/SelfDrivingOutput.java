package org.hashcode.qualification2018.model;

import java.util.List;

import org.hashcode.OutputData;

public class SelfDrivingOutput implements OutputData {

    private final int bonusPoints;
    private final List<VehicleRides> vehiclesRides;

    public SelfDrivingOutput(int bonusPoints, List<VehicleRides> vehiclesRides) {
        this.bonusPoints = bonusPoints;
        this.vehiclesRides = List.copyOf(vehiclesRides);
    }

    @Override
    public long getSolutionScore() {
        return vehiclesRides.stream()
                .flatMap(vehicleRides -> vehicleRides.getRides().stream())
                .mapToLong(ride -> {
                    int score = 0;
                    if (ride.getFinishTime() < ride.getLatestFinishTime()) {
                        score += ride.getDistance();
                        if (ride.getStartTime() == ride.getEarliestStartTime()) {
                            score += bonusPoints;
                        }
                    }
                    return score;
                }).sum();
    }

    public List<VehicleRides> getVehiclesRides() {
        return vehiclesRides;
    }

    @Override
    public String toString() {
        return SelfDrivingOutput.class.getSimpleName() + "{" +
                "vehicleRides=" + vehiclesRides +
                "}";
    }

}
