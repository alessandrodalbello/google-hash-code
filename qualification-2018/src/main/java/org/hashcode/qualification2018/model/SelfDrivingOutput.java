package org.hashcode.qualification2018.model;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hashcode.OutputData;

public class SelfDrivingOutput implements OutputData {

    private final int bonusPoints;
    private final Map<Vehicle, List<Ride>> vehiclesRides;

    public SelfDrivingOutput(int bonusPoints, Map<Vehicle, List<Ride>> vehiclesRides) {
        this.bonusPoints = bonusPoints;
        this.vehiclesRides = Map.copyOf(new TreeMap<>(vehiclesRides));
    }

    @Override
    public long getSolutionScore() {
        return vehiclesRides.values().stream()
                .flatMap(Collection::stream)
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

    public List<List<Ride>> getVehiclesRides() {
        return List.copyOf(vehiclesRides.values());
    }

    @Override
    public String toString() {
        return SelfDrivingOutput.class.getSimpleName() + "{" +
                "bonusPoints=" + bonusPoints +
                ", vehicleRides=" + vehiclesRides +
                "}";
    }

}
