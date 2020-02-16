package org.hashcode.qualification2018.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VehicleRides {

    private final int id;
    private final List<Ride> rides;

    private int currentTime;
    private Position currentPosition;

    public VehicleRides(int id) {
        this.id = id;
        this.rides = new ArrayList<>();

        currentTime = 0;
        currentPosition = new Position(0, 0);
    }

    public int getId() {
        return id;
    }

    public List<Ride> getRides() {
        return List.copyOf(rides);
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void addRide(Ride ride) {
        rides.add(ride);

        int transferTime = transferTime(ride.getStartPosition());
        int waitingTime = waitingTime(transferTime, ride.getEarliestStartTime());
        currentTime += transferTime + waitingTime + ride.getDistance();
        currentPosition = ride.getFinishPosition();
    }

    public int transferTime(Position rideStartPosition) {
        return rideStartPosition.distanceFrom(currentPosition);
    }

    public int waitingTime(int transferTime, int rideEarliestStartingTime) {
        return (currentTime + transferTime) < rideEarliestStartingTime ? rideEarliestStartingTime - (currentTime + transferTime) : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VehicleRides other = (VehicleRides) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return VehicleRides.class.getSimpleName() + "{" +
                "id=" + id +
                ", rides=" + rides +
                "}";
    }

}
