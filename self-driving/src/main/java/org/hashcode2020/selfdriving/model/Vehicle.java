package org.hashcode2020.selfdriving.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vehicle {

    private final int id;
    private final List<Ride> rides;

    private int currentTime;
    private Position currentPosition;

    public Vehicle(int id) {
        this.id = id;
        this.rides = new ArrayList<>();

        currentTime = 0;
        currentPosition = new Position(0, 0);
    }

    public int getId() {
        return id;
    }

    public int getNumberOfRides() {
        return rides.size();
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public List<Ride> getRides() {
        return List.copyOf(rides);
    }

    public void addRide(Ride ride) {
        rides.add(ride);
        int transferTime = transferTime(ride.getStartPosition());
        int waitingTime = waitingTime(transferTime, ride.getEarliestStartTime());
        currentTime += transferTime + waitingTime + ride.getDistance();
        currentPosition = ride.getFinishPosition();
    }

    public int transferTime(Position startingPosition) {
        return Math.abs(startingPosition.getX() - currentPosition.getX()) + Math.abs(startingPosition.getY() - currentPosition.getY());
    }

    public int waitingTime(int transferTime, int earliestStartingTime) {
        return (currentTime + transferTime) < earliestStartingTime ? earliestStartingTime - (currentTime + transferTime) : 0;
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
    public String toString() {
        return Vehicle.class.getSimpleName() + "{" +
                "id=" + id +
                ", rides=" + rides +
                "}";
    }

}
