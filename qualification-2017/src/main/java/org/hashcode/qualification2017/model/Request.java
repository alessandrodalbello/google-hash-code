package org.hashcode.qualification2017.model;

import java.util.Objects;

public class Request {

    private final int videoId;
    private final int endpointId;
    private final int number;

    public Request(int videoId, int endpointId, int number) {
        this.videoId = videoId;
        this.endpointId = endpointId;
        this.number = number;
    }

    public int getVideoId() {
        return videoId;
    }

    public int getEndpointId() {
        return endpointId;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Request other = (Request) obj;
        return Objects.equals(this.videoId, other.videoId)
                && Objects.equals(this.endpointId, other.endpointId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(videoId, endpointId);
    }

    @Override
    public String toString() {
        return Request.class.getSimpleName() + "{" +
                "videoId=" + videoId +
                ", endpointId=" + endpointId +
                ", number=" + number +
                "}";
    }

}
