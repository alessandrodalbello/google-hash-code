package org.hashcode.qualification2017.model;

import java.util.List;

import org.hashcode.InputData;

public class VideosInput implements InputData {

    private final int numberOfVideos;
    private final int numberOfEndpoints;
    private final int numberOfRequests;
    private final int numberOfCaches;
    private final int cacheSize;
    private final List<Video> videos;
    private final List<Endpoint> endpoints;
    private final List<Request> requests;

    public VideosInput(int numberOfVideos, int numberOfEndpoints, int numberOfRequests, int numberOfCaches, int cacheSize,
                       List<Video> videos, List<Endpoint> endpoints, List<Request> requests) {
        this.numberOfVideos = numberOfVideos;
        this.numberOfEndpoints = numberOfEndpoints;
        this.numberOfRequests = numberOfRequests;
        this.numberOfCaches = numberOfCaches;
        this.cacheSize = cacheSize;
        this.videos = videos;
        this.endpoints = endpoints;
        this.requests = requests;
    }

    public int getNumberOfVideos() {
        return numberOfVideos;
    }

    public int getNumberOfEndpoints() {
        return numberOfEndpoints;
    }

    public int getNumberOfRequests() {
        return numberOfRequests;
    }

    public int getNumberOfCaches() {
        return numberOfCaches;
    }

    public int getCacheSize() {
        return cacheSize;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public List<Endpoint> getEndpoints() {
        return endpoints;
    }

    public List<Request> getRequests() {
        return requests;
    }

    @Override
    public String toString() {
        return VideosInput.class.getSimpleName() + "{" +
                "numberOfVideos=" + numberOfVideos +
                ", numberOfEndpoints=" + numberOfEndpoints +
                ", numberOfRequests=" + numberOfRequests +
                ", caches=" + numberOfCaches +
                ", cachesMaxSize=" + cacheSize +
                ", videos=" + videos +
                ", endpoints=" + endpoints +
                ", requests=" + requests +
                "}";
    }

}
