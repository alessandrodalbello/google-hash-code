package org.hashcode.qualification2017.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.common.io.CharStreams;
import org.hashcode.io.InputReader;
import org.hashcode.qualification2017.model.Endpoint;
import org.hashcode.qualification2017.model.Request;
import org.hashcode.qualification2017.model.Video;
import org.hashcode.qualification2017.model.VideosInput;

public class VideosInputReader extends InputReader<VideosInput> {

    private static final String[] inputFilenames = new String[]{
            "a_me_at_the_zoo.in",
            "b_videos_worth_spreading.in",
            "c_trending_today.in",
            "d_kittens.in",
            "e_example.in"
    };

    private static VideosInputReader instance = null;

    private VideosInputReader() {
        super(inputFilenames);
    }

    public static VideosInputReader getInstance() {
        if (instance == null) {
            instance = new VideosInputReader();
        }
        return instance;
    }

    @Override
    public VideosInput readFile(Readable readable) throws IOException {
        List<String> lines = CharStreams.readLines(readable);

        String[] firstLineTokens = lines.get(0).trim().split("\\s");
        int numberOfVideos = Integer.parseInt(firstLineTokens[0]);
        int numberOfEndpoints = Integer.parseInt(firstLineTokens[1]);
        int numberOfRequests = Integer.parseInt(firstLineTokens[2]);
        int numberOfCaches = Integer.parseInt(firstLineTokens[3]);
        int cacheSize = Integer.parseInt(firstLineTokens[4]);

        String[] videoSizes = lines.get(1).trim().split("\\s");
        final List<Video> videos = IntStream.range(0, numberOfVideos)
                .mapToObj(videoId -> new Video(videoId, Integer.parseInt(videoSizes[videoId])))
                .collect(Collectors.toList());

        int linePointer = 2;
        final List<Endpoint> endpoints = new ArrayList<>(numberOfEndpoints);
        for (int endpointId = 0; endpointId < numberOfEndpoints; endpointId++) {
            String[] firstLineEndpointTokens = lines.get(linePointer).trim().split("\\s");
            int dataCenterLatency = Integer.parseInt(firstLineEndpointTokens[0]);
            int connectedCaches = Integer.parseInt(firstLineEndpointTokens[1]);

            final Map<Integer, Integer> cachesLatency = new HashMap<>();
            for (int c = 1; c <= connectedCaches; c++) {
                String[] firstLineCacheTokens = lines.get(linePointer + c).trim().split("\\s");
                int cacheId = Integer.parseInt(firstLineCacheTokens[0]);
                int cacheLatency = Integer.parseInt(firstLineCacheTokens[1]);
                cachesLatency.put(cacheId, cacheLatency);
            }

            Endpoint endpoint = new Endpoint(endpointId, dataCenterLatency, cachesLatency);
            endpoints.add(endpoint);
            linePointer += 1 + connectedCaches;
        }

        final List<Request> requests = new ArrayList<>(numberOfRequests);
        for (int r = 1; r <= numberOfRequests; r++) {
            String[] lineRequestTokens = lines.get(linePointer).trim().split("\\s");
            int videoId = Integer.parseInt(lineRequestTokens[0]);
            int endpointId = Integer.parseInt(lineRequestTokens[1]);
            int numbers = Integer.parseInt(lineRequestTokens[2]);
            Request request = new Request(videoId, endpointId, numbers);
            requests.add(request);
            linePointer += 1;
        }
        return new VideosInput(numberOfVideos, numberOfEndpoints, numberOfRequests, numberOfCaches, cacheSize, videos, endpoints, requests);
    }

}
