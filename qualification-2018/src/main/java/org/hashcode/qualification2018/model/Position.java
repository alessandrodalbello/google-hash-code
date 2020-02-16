package org.hashcode.qualification2018.model;

import java.util.Objects;

public class Position {

    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Position other = (Position) obj;
        return Objects.equals(this.x, other.x) && Objects.equals(this.y, other.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x) + Objects.hashCode(y);
    }

    @Override
    public String toString() {
        return Position.class.getSimpleName() + "{" +
                "x=" + x +
                ", y=" + y +
                "}";
    }

}
