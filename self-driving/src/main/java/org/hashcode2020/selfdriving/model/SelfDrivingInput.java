package org.hashcode2020.selfdriving.model;

import java.util.List;

import org.hashcode2020.InputData;

public class SelfDrivingInput implements InputData {

    private final int row;
    private final int column;
    private final int vehicles;
    private final int numberOfRides;
    private final int bonus;
    private final int maxSteps;
    private final List<Ride> rides;

    public SelfDrivingInput(int row, int column, int vehicles, int numberOfRides, int bonus, int maxSteps, List<Ride> rides) {
        this.row = row;
        this.column = column;
        this.vehicles = vehicles;
        this.numberOfRides = numberOfRides;
        this.bonus = bonus;
        this.maxSteps = maxSteps;
        this.rides = List.copyOf(rides);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getVehicles() {
        return vehicles;
    }

    public int getNumberOfRides() {
        return numberOfRides;
    }

    public int getBonus() {
        return bonus;
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
                "row=" + row +
                ", column=" + column +
                ", vehicles=" + vehicles +
                ", numberOfRides=" + numberOfRides +
                ", bonus=" + bonus +
                ", steps=" + maxSteps +
                ", rides=" + rides +
                "}";
    }
}
