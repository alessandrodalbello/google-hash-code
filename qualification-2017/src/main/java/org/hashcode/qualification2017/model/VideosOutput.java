package org.hashcode.qualification2017.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hashcode.OutputData;

public class VideosOutput implements OutputData {

    private final Map<Integer, Set<Integer>> caches;
    private final Map<Integer, Endpoint> endpoints;
    private final List<Request> requests;

    public VideosOutput(Map<Integer, Set<Integer>> caches, Map<Integer, Endpoint> endpoints, List<Request> requests) {
        this.caches = caches;
        this.endpoints = endpoints;
        this.requests = requests;
    }

    @Override
    public long getSolutionScore() {
        long totalSavedTime = 0;
        for (Request request : requests) {
            Endpoint endpoint = endpoints.get(request.getEndpointId());
            int minLatency = caches.entrySet().stream()
                    .filter(entry -> entry.getValue().contains(request.getVideoId()))
                    .map(Map.Entry::getKey)
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

    public Map<Integer, Set<Integer>> getCaches() {
        return caches;
    }

    @Override
    public String toString() {
        return VideosOutput.class.getSimpleName() + "{" +
                "caches=" + caches +
                "}";
    }

}
