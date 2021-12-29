package org.hashcode.qualification2018.model;

import java.util.List;

import org.hashcode.InputData;

public class SelfDrivingInput implements InputData {

    private final int mapRows;
    private final int mapColumns;
    private final int numberOfVehicles;
    private final int numberOfRides;
    private final int bonusPoints;
    private final int maxSteps;
    private final List<Ride> rides;

    public SelfDrivingInput(int mapRows, int mapColumns, int numberOfVehicles, int numberOfRides, int bonusPoints,
            int maxSteps, List<Ride> rides) {
        this.mapRows = mapRows;
        this.mapColumns = mapColumns;
        this.numberOfVehicles = numberOfVehicles;
        this.numberOfRides = numberOfRides;
        this.bonusPoints = bonusPoints;
        this.maxSteps = maxSteps;
        this.rides = List.copyOf(rides);
    }

    public int getMapRows() {
        return mapRows;
    }

    public int getMapColumns() {
        return mapColumns;
    }

    public int getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public int getNumberOfRides() {
        return numberOfRides;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }

    public int getMaxSteps() {
        return maxSteps;
    }

    public List<Ride> getRides() {
        return rides;
    }

    @Override
    public String toString() {
        return SelfDrivingInput.class.getSimpleName() + "{" +
                "mapRows=" + mapRows +
                ", mapColumns=" + mapColumns +
                ", numberOfVehicles=" + numberOfVehicles +
                ", numberOfRides=" + numberOfRides +
                ", bonusPoints=" + bonusPoints +
                ", maxSteps=" + maxSteps +
                ", rides=" + rides +
                "}";
    }

}
