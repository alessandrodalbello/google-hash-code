package org.hashcode.qualification2017.solvers;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.hashcode.Solver;
import org.hashcode.qualification2017.model.Endpoint;
import org.hashcode.qualification2017.model.Video;
import org.hashcode.qualification2017.model.VideosInput;
import org.hashcode.qualification2017.model.VideosOutput;

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


        return new VideosOutput(Map.of(), endpointsMap, inputData.getRequests());
    }

    private Map<Integer, Endpoint> toEndpointsMap(List<Endpoint> endpoints) {
        return endpoints.stream().collect(Collectors.toMap(Endpoint::getId, Function.identity()));
    }

}
