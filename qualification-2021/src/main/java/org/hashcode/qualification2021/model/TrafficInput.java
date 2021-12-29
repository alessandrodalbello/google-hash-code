package org.hashcode.qualification2021.model;

import java.util.List;

import org.hashcode.InputData;

public class TrafficInput implements InputData {

    private final int duration;
    private final int numberOfIntersections;
    private final List<Street> streets;
    private final List<Car> cars;
    private final int bonusPoints;

    public TrafficInput(int duration, int numberOfIntersections, List<Street> streets, List<Car> cars, int bonusPoints) {
        this.duration = duration;
        this.numberOfIntersections = numberOfIntersections;
        this.streets = streets;
        this.cars = cars;
        this.bonusPoints = bonusPoints;
    }

    public int getDuration() {
        return duration;
    }

    public int getNumberOfIntersections() {
        return numberOfIntersections;
    }

    public List<Street> getStreets() {
        return streets;
    }

    public List<Car> getCars() {
        return cars;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }

    @Override
    public String toString() {
        return TrafficInput.class.getSimpleName() + "{" +
                "duration=" + duration +
                ", numberOfIntersections=" + numberOfIntersections +
                ", streets=" + streets +
                ", cars=" + cars +
                ", bonusPoints=" + bonusPoints +
                "}";
    }

}
