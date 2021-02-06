package org.hashcode.qualification2017.io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.io.CharSink;
import com.google.common.io.Files;
import org.hashcode.io.OutputWriter;
import org.hashcode.qualification2017.model.VideosOutput;

public class VideosOutputWriter extends OutputWriter<VideosOutput> {

    private static VideosOutputWriter instance = null;

    private VideosOutputWriter() {
        // not visible
    }

    public static VideosOutputWriter getInstance() {
        if (instance == null) {
            instance = new VideosOutputWriter();
        }
        return instance;
    }

    @Override
    protected void writeFile(VideosOutput outputData, File outputFile) throws IOException {
        int numberOfCaches = outputData.getCaches().size();
        List<String> cachesConfiguration = new ArrayList<>(numberOfCaches + 1);
        cachesConfiguration.add(String.valueOf(numberOfCaches));

        outputData.getCaches().forEach(cache -> {
            String videoIds = cache.getVideoIds().stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" "));
            String cacheConfig = cache.getId() + " " + videoIds;
            cachesConfiguration.add(cacheConfig);
        });

        CharSink charSink = Files.asCharSink(outputFile, StandardCharsets.UTF_8);
        charSink.writeLines(cachesConfiguration);
    }

}
