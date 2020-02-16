package org.hashcode2020.selfdriving.io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.io.CharSink;
import com.google.common.io.Files;
import org.hashcode2020.io.OutputWriter;
import org.hashcode2020.selfdriving.model.Ride;
import org.hashcode2020.selfdriving.model.SelfDrivingOutput;
import org.hashcode2020.selfdriving.model.Vehicle;

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
        List<Vehicle> vehicles = outputData.getVehicles();
        List<String> vehicleRidesOutput = vehicles.stream()
                .map(vehicleRide -> {
                    List<String> rides = vehicleRide.getRides().stream()
                            .map(Ride::getId)
                            .map(String::valueOf)
                            .collect(Collectors.toList());
                    return vehicleRide.getNumberOfRides() + " " + String.join(" ", rides);
                }).collect(Collectors.toList());
        CharSink charSink = Files.asCharSink(outputFile, StandardCharsets.UTF_8);
        charSink.writeLines(vehicleRidesOutput);
    }

}
