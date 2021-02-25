package org.hashcode.qualification2017.model;

import java.util.Objects;

public class Video {

    private final int id;
    private final int size;

    public Video(int id, int size) {
        this.id = id;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Video other = (Video) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return Video.class.getSimpleName() + "{" +
                "id=" + id +
                ", size=" + size +
                "}";
    }

}
