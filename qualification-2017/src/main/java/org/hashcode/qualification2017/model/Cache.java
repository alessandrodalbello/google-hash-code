package org.hashcode.qualification2017.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Cache {

    private final int id;
    private final Set<Integer> videoIds;
    private int remainingSize;

    public Cache(int id, int cacheSize) {
        this.id = id;
        this.remainingSize = cacheSize;
        this.videoIds = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public Set<Integer> getVideoIds() {
        return videoIds;
    }

    public boolean canAddVideo(Video video) {
        return remainingSize >= video.getSize();
    }

    public void addVideo(Video video) {
        boolean isVideoAdded = videoIds.add(video.getId());
        if (isVideoAdded) {
            remainingSize -= video.getSize();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Cache other = (Cache) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return Cache.class.getSimpleName() + "{" +
                "id=" + id +
                ", videoIds=" + videoIds +
                "}";
    }

}
