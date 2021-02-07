package org.hashcode.qualification2017.model;

import java.util.List;

import org.hashcode.OutputData;

public class VideosOutput implements OutputData {

    private final List<Cache> caches;
    private final List<Endpoint> endpoints;
    private final List<Request> requests;

    public VideosOutput(List<Cache> caches, List<Endpoint> endpoints, List<Request> requests) {
        this.caches = caches;
        this.endpoints = endpoints;
        this.requests = requests;
    }

    @Override
    public long getSolutionScore() {
        long totalSavedTime = 0;
        for (Request request : requests) {
            Endpoint endpoint = endpoints.get(request.getEndpointId());
            int minLatency = caches.stream()
                    .filter(cache -> cache.getVideoIds().contains(request.getVideoId()))
                    .map(Cache::getId)
                    .filter(endpoint::isCacheConnected)
                    .mapToInt(endpoint::getCacheLatency)
                    .min().orElse(endpoint.getDataCenterLatency());
            int savedTime = endpoint.getDataCenterLatency() - minLatency;
            totalSavedTime += ((long) savedTime * request.getNumber());
        }
        long totalRequests = requests.stream()
                .mapToLong(Request::getNumber)
                .sum();
        return (long) Math.floor(totalSavedTime * 1000 / (double) totalRequests);
    }

    public List<Cache> getCaches() {
        return caches;
    }

    @Override
    public String toString() {
        return VideosOutput.class.getSimpleName() + "{" +
                "caches=" + caches +
                "}";
    }

}
