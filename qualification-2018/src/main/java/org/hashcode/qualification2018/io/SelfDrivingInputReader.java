package org.hashcode.qualification2018.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.io.CharStreams;
import org.hashcode.io.InputReader;
import org.hashcode.qualification2018.model.Position;
import org.hashcode.qualification2018.model.Ride;
import org.hashcode.qualification2018.model.SelfDrivingInput;

public class SelfDrivingInputReader extends InputReader<SelfDrivingInput> {

    private static String[] inputFilenames = new String[] {
            "a_example.in",
            "b_should_be_easy.in",
            "c_no_hurry.in",
            "d_metropolis.in",
            "e_high_bonus.in"
    };

    private static SelfDrivingInputReader instance = null;

    private SelfDrivingInputReader() {
        super(inputFilenames);
    }

    public static SelfDrivingInputReader getInstance() {
        if (instance == null) {
            instance = new SelfDrivingInputReader();
        }
        return instance;
    }

    @Override
    public SelfDrivingInput readFile(Readable readable) throws IOException {
        List<String> lines = CharStreams.readLines(readable);
        String[] problemTokens = lines.get(0).trim().split("\\s");
        int mapRows = Integer.parseInt(problemTokens[0]);
        int mapColumns = Integer.parseInt(problemTokens[1]);
        int numberOfVehicles = Integer.parseInt(problemTokens[2]);
        int numberOfRides = Integer.parseInt(problemTokens[3]);
        int bonusPoints = Integer.parseInt(problemTokens[4]);
        int maxSteps = Integer.parseInt(problemTokens[5]);

        List<Ride> rides = new ArrayList<>(numberOfRides);
        for (int i = 1; i < lines.size(); i++) {
            String[] rideTokens = lines.get(i).trim().split("\\s");
            Position startPosition = new Position(Integer.parseInt(rideTokens[0]), Integer.parseInt(rideTokens[1]));
            Position finishPosition = new Position(Integer.parseInt(rideTokens[2]), Integer.parseInt(rideTokens[3]));
            int earliestStartTime = Integer.parseInt(rideTokens[4]);
            int latestFinishTime = Integer.parseInt(rideTokens[5]);
            Ride ride = new Ride(i - 1, startPosition, finishPosition, earliestStartTime, latestFinishTime);
            rides.add(ride);
        }
        return new SelfDrivingInput(mapRows, mapColumns, numberOfVehicles, numberOfRides, bonusPoints, maxSteps, rides);
    }

}
