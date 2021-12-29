package org.hashcode.qualification2021.model;

import java.util.List;
import java.util.Objects;

public class Car {

    private final int id;
    private final List<Street> path;
    private final int pathLength;

    private int travelTime;

    public Car(int id, List<Street> path) {
        this.id = id;
        this.path = path;
        this.pathLength = path.stream()
                .mapToInt(Street::getLength)
                .sum();

        travelTime = 0;
    }

    public int getId() {
        return id;
    }

    public List<Street> getPath() {
        return path;
    }

    public int getPathLength() {
        return pathLength;
    }

    public void travel(int time) {
        travelTime += time;
    }

    public int getTravelTime() {
        return travelTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Car other = (Car) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return Car.class.getSimpleName() + "{" +
                "id=" + id +
                ", path=" + path +
                ", travelTime=" + travelTime +
                "}";
    }

}
