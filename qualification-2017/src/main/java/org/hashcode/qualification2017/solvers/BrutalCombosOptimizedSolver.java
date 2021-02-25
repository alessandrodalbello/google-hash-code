package org.hashcode.qualification2017.solvers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

import org.hashcode.Solver;
import org.hashcode.qualification2017.model.Cache;
import org.hashcode.qualification2017.model.Endpoint;
import org.hashcode.qualification2017.model.Request;
import org.hashcode.qualification2017.model.Video;
import org.hashcode.qualification2017.model.VideosInput;
import org.hashcode.qualification2017.model.VideosOutput;

public class BrutalCombosOptimizedSolver implements Solver<VideosInput, VideosOutput> {

    @Override
    public VideosOutput solve(VideosInput inputData) {
        List<Video> videos = inputData.getVideos();
        List<Endpoint> endpoints = inputData.getEndpoints();
        List<Request> requests = inputData.getRequests();

        Map<Integer, List<Request>> videoRequestsMap = new HashMap<>();
        for (Request request : requests) {
            videoRequestsMap.putIfAbsent(request.getVideoId(), new ArrayList<>());
            videoRequestsMap.get(request.getVideoId()).add(request);
        }
        Map<Integer, Set<Integer>> videoEndpointsMap = videoRequestsMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().stream()
                        .map(Request::getEndpointId)
                        .collect(Collectors.toSet())
                ));

        List<Video> usefulVideos = videos.stream()
                .filter(video -> video.getSize() <= inputData.getCacheSize())
                .filter(video -> videoEndpointsMap.containsKey(video.getId()))
                .filter(video -> videoEndpointsMap.get(video.getId()).stream()
                        .map(endpointId -> endpoints.get(endpointId).getConnectedCachesNumber())
                        .anyMatch(connectedCaches -> connectedCaches > 0))
                .collect(Collectors.toList());

        int numberOfCaches = inputData.getNumberOfCaches();
        Cache[] caches = new Cache[numberOfCaches];
        for (int cacheId = 0; cacheId < numberOfCaches; cacheId++) {
            caches[cacheId] = new Cache(cacheId, inputData.getCacheSize());
        }

        Map<Integer, Set<Integer>> cacheEndpointsMap = new HashMap<>();
        for (Endpoint endpoint : endpoints) {
            List<Integer> connectedCaches = endpoint.getConnectedCaches();
            for (Integer cacheId : connectedCaches) {
                cacheEndpointsMap.putIfAbsent(cacheId, new HashSet<>());
                cacheEndpointsMap.get(cacheId).add(endpoint.getId());
            }
        }

        Map<VideoCacheScore, VideoCacheScore> videoCacheScores = new HashMap<>();
        for (Video usefulVideo : usefulVideos) {
            List<Request> videoRequests = videoRequestsMap.get(usefulVideo.getId());
            for (Request request : videoRequests) {
                Endpoint endpoint = endpoints.get(request.getEndpointId());
                for (Integer cacheId : endpoint.getConnectedCaches()) {
                    long score = (long) (endpoint.getDataCenterLatency() - endpoint.getCacheLatency(cacheId)) * request.getNumber();
                    VideoCacheScore videoCacheScore = new VideoCacheScore(usefulVideo.getId(), cacheId);
                    videoCacheScores.putIfAbsent(videoCacheScore, videoCacheScore);
                    videoCacheScores.get(videoCacheScore).addScore(score);
                }
            }
        }

        Map<Integer, Set<Integer>> endpointCachedVideos = new HashMap<>();
        PriorityQueue<VideoCacheScore> scoresHeap = new PriorityQueue<>(videoCacheScores.keySet());
        for (VideoCacheScore videoCacheScore : scoresHeap) {
            Cache cache = caches[videoCacheScore.getCacheId()];
            Video video = videos.get(videoCacheScore.getVideoId());
            if (cache.canAddVideo(video)) {
                Set<Integer> connectedEndpoints = cacheEndpointsMap.get(videoCacheScore.getCacheId());
                boolean alreadyCached = connectedEndpoints.stream()
                        .allMatch(endpointId -> endpointCachedVideos.containsKey(endpointId)
                                && endpointCachedVideos.get(endpointId).contains(videoCacheScore.getVideoId()));
                if (!alreadyCached && cache.addVideo(video)) {
                    for (Integer endpointId : connectedEndpoints) {
                        endpointCachedVideos.putIfAbsent(endpointId, new HashSet<>());
                        endpointCachedVideos.get(endpointId).add(videoCacheScore.getVideoId());
                    }
                }
            }
        }

        List<Cache> cachesList = Arrays.stream(caches)
                .filter(cache -> !cache.getVideoIds().isEmpty())
                .collect(Collectors.toList());
        return new VideosOutput(cachesList, endpoints, requests);
    }

}
