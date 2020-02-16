package org.hashcode.qualification2018.io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.io.CharSink;
import com.google.common.io.Files;
import org.hashcode.io.OutputWriter;
import org.hashcode.qualification2018.model.Ride;
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
        List<List<Ride>> vehiclesRides = outputData.getVehiclesRides();
        List<String> outputVehiclesRides = new ArrayList<>(vehiclesRides.size());
        for (List<Ride> rides : vehiclesRides) {
            List<String> outputRides = rides.stream()
                    .map(ride -> String.valueOf(ride.getId()))
                    .collect(Collectors.toList());
            String outputVehicleRides = rides.size() + " " + String.join(" ", outputRides);
            outputVehiclesRides.add(outputVehicleRides);
        }
        CharSink charSink = Files.asCharSink(outputFile, StandardCharsets.UTF_8);
        charSink.writeLines(outputVehiclesRides);
    }

}
