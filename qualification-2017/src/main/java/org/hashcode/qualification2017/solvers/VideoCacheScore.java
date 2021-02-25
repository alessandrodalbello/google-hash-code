package org.hashcode.qualification2017.solvers;

import java.util.Objects;

class VideoCacheScore implements Comparable<VideoCacheScore> {
    private final int videoId;
    private final int cacheId;

    private long score;

    public VideoCacheScore(int videoId, int cacheId) {
        this.videoId = videoId;
        this.cacheId = cacheId;
        score = 0;
    }

    public int getVideoId() {
        return videoId;
    }

    public int getCacheId() {
        return cacheId;
    }

    public void addScore(long score) {
        this.score += score;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VideoCacheScore other = (VideoCacheScore) obj;
        return Objects.equals(this.videoId, other.videoId) && Objects.equals(this.cacheId, other.cacheId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(videoId, cacheId);
    }

    @Override
    public int compareTo(VideoCacheScore other) {
        return Long.compare(this.score, other.score) * -1;
    }
}
