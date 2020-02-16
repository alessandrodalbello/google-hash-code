package org.hashcode.qualification2018.model;

import java.util.Objects;

public class Vehicle implements Comparable<Vehicle> {

    private final int id;

    private int currentStartTime;
    private int currentFinishTime;
    private Position currentPosition;

    public Vehicle(int id) {
        this.id = id;

        currentFinishTime = 0;
        currentPosition = new Position(0, 0);
    }

    public int getId() {
        return id;
    }

    public int getCurrentStartTime() {
        return currentStartTime;
    }

    public int getCurrentFinishTime() {
        return currentFinishTime;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void doRide(Ride ride) {
        int transferTime = transferTime(ride.getStartPosition());
        int waitingTime = waitingTime(transferTime + currentFinishTime, ride.getEarliestStartTime());
        currentStartTime = currentFinishTime + transferTime + waitingTime;
        currentFinishTime = currentStartTime + ride.getDistance();
        currentPosition = ride.getFinishPosition();
    }

    public int transferTime(Position rideStartPosition) {
        return rideStartPosition.distanceFrom(currentPosition);
    }

    public int waitingTime(int startPositionArrivalTime, int rideEarliestStartTime) {
        return Math.max(rideEarliestStartTime - startPositionArrivalTime, 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vehicle other = (Vehicle) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Vehicle vehicle) {
        return this.id - vehicle.id;
    }

    @Override
    public String toString() {
        return Vehicle.class.getSimpleName() + "{" +
                "id=" + id +
                ", currentStartTime=" + currentStartTime +
                ", currentFinishTime=" + currentFinishTime +
                ", currentPosition=" + currentPosition +
                "}";
    }

}
