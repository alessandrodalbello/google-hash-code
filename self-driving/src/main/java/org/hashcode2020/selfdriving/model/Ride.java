package org.hashcode2020.selfdriving.model;

import java.util.Objects;

public class Ride {

    private final int id;
    private final Position startPosition;
    private final Position finishPosition;
    private final int distance;
    private final int earliestStartTime;
    private final int latestFinishTime;

    private int startTime;
    private int finishTime;

    public Ride(int id, Position startPosition, Position finishPosition, int earliestStartTime, int latestFinishTime) {
        this.id = id;
        this.startPosition = startPosition;
        this.finishPosition = finishPosition;
        this.distance = Math.abs(startPosition.getX() - finishPosition.getX()) + Math.abs(startPosition.getY() - finishPosition.getY());
        this.earliestStartTime = earliestStartTime;
        this.latestFinishTime = latestFinishTime;
    }

    public int getId() {
        return id;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getFinishPosition() {
        return finishPosition;
    }

    public int getDistance() {
        return distance;
    }

    public int getEarliestStartTime() {
        return earliestStartTime;
    }

    public int getLatestFinishTime() {
        return latestFinishTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
        this.finishTime = startTime + distance;
    }

    public int getFinishTime() {
        return finishTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Ride other = (Ride) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return Ride.class.getSimpleName() + "{" +
                "id=" + id +
                ", startPosition=" + startPosition +
                ", finishPosition=" + finishPosition +
                ", distance=" + distance +
                ", earliestStart=" + earliestStartTime +
                ", latestFinish=" + latestFinishTime +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                "}";
    }

}
