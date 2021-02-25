package org.hashcode.qualification2021.model;

public class IncomingStreet {

    private final String streetName;
    private final int greenDuration;

    public IncomingStreet(String streetName, int duration) {
        this.streetName = streetName;
        this.greenDuration = duration;
    }

    public String getStreetName() {
        return streetName;
    }

    public int getGreenDuration() {
        return greenDuration;
    }

    @Override
    public String toString() {
        return IncomingStreet.class.getSimpleName() + "{" +
                "streetName=" + streetName +
                ", greenDuration=" + greenDuration +
                "}";
    }

}
