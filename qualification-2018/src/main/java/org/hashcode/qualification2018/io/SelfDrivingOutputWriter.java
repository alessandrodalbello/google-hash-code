package org.hashcode.qualification2018.io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.io.CharSink;
import com.google.common.io.Files;
import org.hashcode.io.OutputWriter;
import org.hashcode.qualification2018.model.SelfDrivingOutput;

public class SelfDrivingOutputWriter extends OutputWriter<SelfDrivingOutput> {

    private static SelfDrivingOutputWriter instance = null;

    private SelfDrivingOutputWriter() {
        // not visible
    }

    public static SelfDrivingOutputWriter getInstance() {
        if (instance == null) {
            instance = new SelfDrivingOutputWriter();
        }
        return instance;
    }

    @Override
    protected void writeFile(SelfDrivingOutput outputData, File outputFile) throws IOException {
        List<String> vehicleRidesOutput = outputData.getVehiclesRides().stream()
                .map(vehicleRides -> {
                    List<String> rides = vehicleRides.getRides().stream()
                            .map(ride -> String.valueOf(ride.getId()))
                            .collect(Collectors.toList());
                    return rides.size() + " " + String.join(" ", rides);
                }).collect(Collectors.toList());
        CharSink charSink = Files.asCharSink(outputFile, StandardCharsets.UTF_8);
        charSink.writeLines(vehicleRidesOutput);
    }

}
