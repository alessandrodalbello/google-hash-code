package org.hashcode.qualification2021.model;

import java.util.List;

import org.hashcode.OutputData;

public class TrafficOutput implements OutputData {

    private final int duration;
    private final int bonusPoints;
    private final List<Car> cars;
    private final List<Schedule> schedules;

    public TrafficOutput(int duration, int bonusPoints, List<Car> cars, List<Schedule> schedules) {
        this.duration = duration;
        this.bonusPoints = bonusPoints;
        this.schedules = schedules;
        this.cars = cars;
    }

    @Override
    public long getSolutionScore() {
        return cars.stream()
                .filter(car -> car.getTravelTime() <= duration)
                .mapToLong(car -> bonusPoints + duration - car.getTravelTime())
                .sum();
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    @Override
    public String toString() {
        return TrafficOutput.class.getSimpleName() + "{" +
                "duration=" + duration +
                ", bonusPoints=" + bonusPoints +
                ", cars=" + cars +
                ", schedules=" + schedules +
                "}";
    }

}
