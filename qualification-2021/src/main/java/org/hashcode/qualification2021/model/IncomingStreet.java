package org.hashcode.qualification2021.model;

public class IncomingStreet {

    private final String streetName;

    private int duration;

    public IncomingStreet(String streetName, int duration) {
        this.streetName = streetName;
        this.duration = duration;
    }

    public String getStreetName() {
        return streetName;
    }

    public int getDuration() {
        return duration;
    }

    public void increaseDuration(int value) {
        duration += value;
    }

    @Override
    public String toString() {
        return IncomingStreet.class.getSimpleName() + "{" +
                "streetName=" + streetName +
                ", duration=" + duration +
                "}";
    }

}
