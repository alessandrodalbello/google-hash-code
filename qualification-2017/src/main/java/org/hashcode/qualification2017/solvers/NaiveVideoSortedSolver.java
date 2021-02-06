package org.hashcode.qualification2017.solvers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.hashcode.Solver;
import org.hashcode.qualification2017.model.Cache;
import org.hashcode.qualification2017.model.Endpoint;
import org.hashcode.qualification2017.model.Request;
import org.hashcode.qualification2017.model.Video;
import org.hashcode.qualification2017.model.VideosInput;
import org.hashcode.qualification2017.model.VideosOutput;

public class NaiveVideoSortedSolver implements Solver<VideosInput, VideosOutput> {

    @Override
    public VideosOutput solve(VideosInput inputData) {
        Map<Integer, Endpoint> endpointsMap = toEndpointsMap(inputData.getEndpoints());
        Map<Integer, Set<Integer>> videoEndpointsMap = inputData.getVideos().stream()
                .collect(Collectors.toMap(Video::getId, video -> inputData.getRequests().stream()
                        .filter(request -> request.getVideoId() == video.getId())
                        .map(Request::getEndpointId)
                        .collect(Collectors.toSet())
                ));
        List<Video> usefulVideos = inputData.getVideos().stream()
                .filter(video -> videoEndpointsMap.get(video.getId()).stream()
                        .map(endpointId -> endpointsMap.get(endpointId).getConnectedCachesNumber())
                        .anyMatch(connectedCaches -> connectedCaches > 0))
                .filter(video -> video.getSize() <= inputData.getCacheSize())
                .collect(Collectors.toList());

        Map<Integer, Cache> caches = new HashMap<>();
        for (Endpoint endpoint : inputData.getEndpoints()) {
            TreeSet<EndpointCache> sortedCaches = new TreeSet<>();
            Map<Integer, Integer> cachesLatency = endpoint.getCachesLatency();
            for (Map.Entry<Integer, Integer> latencyEntry : cachesLatency.entrySet()) {
                sortedCaches.add(new EndpointCache(latencyEntry.getKey(), latencyEntry.getValue()));
            }

            List<Request> requestsOfEndpoint = inputData.getRequests().stream()
                    .filter(request -> request.getEndpointId() == endpoint.getId())
                    .collect(Collectors.toList());

            Map<Integer, Integer> requestsByVideo = new HashMap<>();
            for (Request request : requestsOfEndpoint) {
                requestsByVideo.put(request.getVideoId(), request.getNumber());
            }
            usefulVideos.sort((video1, video2) -> {
                int reqVideo1 = requestsByVideo.getOrDefault(video1.getId(), 0);
                int reqVideo2 = requestsByVideo.getOrDefault(video2.getId(), 0);
                return Integer.compare(reqVideo1, reqVideo2) * -1;
            });

            for (Video usefulVideo : usefulVideos) {
                boolean isConnectedToEndpoint = videoEndpointsMap.get(usefulVideo.getId()).contains(endpoint.getId());
                if (!isConnectedToEndpoint) continue;

                boolean isStored = false;
                Iterator<EndpointCache> sortedCachesIterator = sortedCaches.iterator();
                while (!isStored && sortedCachesIterator.hasNext()) {
                    EndpointCache endpointCache = sortedCachesIterator.next();
                    if (!caches.containsKey(endpointCache.getId())) {
                        caches.put(endpointCache.getId(), new Cache(endpointCache.getId(), inputData.getCacheSize()));
                    }
                    Cache cache = caches.get(endpointCache.getId());
                    if (cache.canAddVideo(usefulVideo)) {
                        cache.addVideo(usefulVideo);
                        isStored = true;
                    }
                }
            }
        }
        List<Cache> cachesList = new ArrayList<>(caches.values());
        return new VideosOutput(cachesList, endpointsMap, inputData.getRequests());
    }

    private Map<Integer, Endpoint> toEndpointsMap(List<Endpoint> endpoints) {
        return endpoints.stream().collect(Collectors.toMap(Endpoint::getId, Function.identity()));
    }

}
