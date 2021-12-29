package org.hashcode.qualification2021.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Schedule {

    private final int intersection;
    private final List<IncomingStreet> incomingStreets;

    public Schedule(int intersection) {
        this.intersection = intersection;
        this.incomingStreets = new ArrayList<>();
    }

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

    public void addIncomingStreet(IncomingStreet incomingStreet) {
        incomingStreets.add(incomingStreet);
    }

    public Optional<IncomingStreet> findIncomingStreetByName(String streetName) {
        return incomingStreets.stream()
                .filter(incomingStreet -> streetName.equals(incomingStreet.getStreetName()))
                .findFirst();
    }

    @Override
    public String toString() {
        return Schedule.class.getSimpleName() + "{" +
                "intersection=" + intersection +
                ", incomingStreets=" + incomingStreets +
                "}";
    }

}
