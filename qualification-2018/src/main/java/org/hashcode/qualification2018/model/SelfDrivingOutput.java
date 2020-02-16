package org.hashcode.qualification2018.model;

import java.util.List;

import org.hashcode2020.OutputData;

public class SelfDrivingOutput implements OutputData {

    private final int bonus;
    private final List<Vehicle> vehicles;

    public SelfDrivingOutput(int bonus, List<Vehicle> vehicles) {
        this.bonus = bonus;
        this.vehicles = List.copyOf(vehicles);
    }

    @Override
    public long getSolutionScore() {
        return vehicles.stream()
                .flatMap(vehicleRide -> vehicleRide.getRides().stream())
                .mapToLong(ride -> {
                    int score = 0;
                    if (ride.getFinishTime() < ride.getLatestFinishTime()) {
                        score += ride.getDistance();
                        if (ride.getStartTime() == ride.getEarliestStartTime()) {
                            score += bonus;
                        }
                    }
                    return score;
                }).sum();
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    @Override
    public String toString() {
        return SelfDrivingOutput.class.getSimpleName() + "{" +
                "bonus=" + bonus +
                ", vehicleRides=" + vehicles +
                "}";
    }

}
