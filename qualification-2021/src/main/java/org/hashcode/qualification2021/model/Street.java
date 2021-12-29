package org.hashcode.qualification2021.model;

import java.util.Objects;

public class Street {

    private final String name;
    private final int startIntersection;
    private final int endIntersection;
    private final int length;

    public Street(String name, int startIntersection, int endIntersection, int length) {
        this.name = name;
        this.startIntersection = startIntersection;
        this.endIntersection = endIntersection;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public int getStartIntersection() {
        return startIntersection;
    }

    public int getEndIntersection() {
        return endIntersection;
    }

    public int getLength() {
        return length;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Street other = (Street) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return Street.class.getSimpleName() + "{" +
                "name=" + name +
                ", startIntersection=" + startIntersection +
                ", endIntersection=" + endIntersection +
                ", length=" + length +
                "}";
    }

}
