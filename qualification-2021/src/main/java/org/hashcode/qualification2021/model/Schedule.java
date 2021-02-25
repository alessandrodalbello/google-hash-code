package org.hashcode.qualification2021.model;

import java.util.List;

public class Schedule {

    private final int intersection;
    private final List<IncomingStreet> incomingStreets;

    public Schedule(int intersection, List<IncomingStreet> incomingStreets) {
        this.intersection = intersection;
        this.incomingStreets = incomingStreets;
    }

    public int getIntersection() {
        return intersection;
    }

    public List<IncomingStreet> getIncomingStreets() {
        return incomingStreets;
    }

    @Override
    public String toString() {
        return Schedule.class.getSimpleName() + "{" +
                "intersection=" + intersection +
                ", incomingStreets=" + incomingStreets +
                "}";
    }

}
