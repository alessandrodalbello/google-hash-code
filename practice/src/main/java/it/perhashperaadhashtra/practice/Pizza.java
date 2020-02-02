package it.perhashperaadhashtra.practice;

import java.util.Objects;

public class Pizza {

    private final int type;
    private final int slices;

    public Pizza(int type, int slices) {
        this.type = type;
        this.slices = slices;
    }

    public int getType() {
        return type;
    }

    public int getSlices() {
        return slices;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Pizza other = (Pizza) obj;
        return Objects.equals(this.type, other.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return Pizza.class.getSimpleName() + "{" +
                "type=" + type +
                ", slices=" + slices +
                "}";
    }

}
