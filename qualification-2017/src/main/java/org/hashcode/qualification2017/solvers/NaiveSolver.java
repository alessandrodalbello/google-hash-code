package org.hashcode.qualification2017.solvers;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.hashcode.Solver;
import org.hashcode.qualification2017.model.*;

public class NaiveSolver implements Solver<VideosInput, VideosOutput> {

    @Override
    public VideosOutput solve(VideosInput inputData) {
        Map<Integer, Endpoint> endpointsMap = toEndpointsMap(inputData.getEndpoints());
        List<Video> usefulVideos = inputData.getVideos().stream()
                .filter(video -> video.getSize() <= inputData.getCacheSize())
                .filter(video -> inputData.getRequests().stream()
                        .filter(request -> request.getVideoId() == video.getId())
                        .anyMatch(request -> endpointsMap.get(request.getEndpointId()).getConnectedCachesNumber() > 0))
                .collect(Collectors.toList());

        /*
            per ogni video trovo le cache utili (dove ha senso usarli)
            computo punteggio mettendo il video in cache
            calcolo la classifica
         */

        List<Endpoint> allEndpoints = inputData.getEndpoints();
        List<Request> requests = inputData.getRequests();

        Map<Integer, List<Integer>> videosToCache = new HashMap<>();

        for (Request request: requests) {
            Endpoint endpoint = allEndpoints.get(request.getEndpointId());
            Map<Integer, Integer> cachesLatencyMap = endpoint.getCachesLatencyMap();
            List<Integer> caches;

            if (videosToCache.containsKey(request.getVideoId())) {
                caches = videosToCache.get(request.getVideoId());
            } else {
                caches = new ArrayList<>();
            }

            caches.addAll(cachesLatencyMap.keySet());
            videosToCache.put(request.getVideoId(), caches);
        }

        // TODO
        Set<Collocation> collocationSet = new TreeSet<>();

        for (Request request: requests) {
            List<Integer> cachesList = videosToCache.get(request.getVideoId());
            for (Integer cacheId : cachesList) {
                // TODO calcolo il risultato per quella cache
            }
        }

        return new VideosOutput(Map.of(), endpointsMap, inputData.getRequests());
    }

    private Map<Integer, Endpoint> toEndpointsMap(List<Endpoint> endpoints) {
        return endpoints.stream().collect(Collectors.toMap(Endpoint::getId, Function.identity()));
    }

}

class Collocation implements Comparable<Collocation> {
    int cacheId;
    int videoId;
    int score;

    /*
    // TODO
    public int calculateScore() {
        // for each endpoint associati alla cache
        // prendi il numeo di richeiste da quell'endpoint di quel video
    }
     */

    @Override
    public int compareTo(Collocation o) {
        if (score == o.score) { return 0; }
        return this.score < o.score ? -1 : 1;
    }
}
