package org.hashcode.qualification2021.io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.google.common.io.CharSink;
import com.google.common.io.Files;
import org.hashcode.io.OutputWriter;
import org.hashcode.qualification2021.model.IncomingStreet;
import org.hashcode.qualification2021.model.Schedule;
import org.hashcode.qualification2021.model.TrafficOutput;

public class TrafficOutputWriter extends OutputWriter<TrafficOutput> {

    private static TrafficOutputWriter instance = null;

    private TrafficOutputWriter() {
        // not visible
    }

    public static TrafficOutputWriter getInstance() {
        if (instance == null) {
            instance = new TrafficOutputWriter();
        }
        return instance;
    }

    @Override
    protected void writeFile(TrafficOutput outputData, File outputFile) throws IOException {
        List<String> submission = new ArrayList<>();
        List<Schedule> schedules = outputData.getSchedules();
        String numberOfSchedules = String.valueOf(schedules.size());
        submission.add(numberOfSchedules);
        for (Schedule schedule : schedules) {
            submission.add(String.valueOf(schedule.getIntersection()));
            submission.add(String.valueOf(schedule.getIncomingStreets().size()));
            for (IncomingStreet incomingStreet : schedule.getIncomingStreets()) {
                String scheduleDescription = incomingStreet.getStreetName() + " " + incomingStreet.getDuration();
                submission.add(scheduleDescription);
            }
        }

        CharSink charSink = Files.asCharSink(outputFile, StandardCharsets.UTF_8);
        charSink.writeLines(submission);
    }

}
