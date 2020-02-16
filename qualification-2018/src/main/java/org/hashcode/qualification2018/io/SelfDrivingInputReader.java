package org.hashcode.qualification2018.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.io.CharStreams;
import org.hashcode.qualification2018.model.Position;
import org.hashcode.qualification2018.model.Ride;
import org.hashcode.qualification2018.model.SelfDrivingInput;
import org.hashcode2020.io.InputReader;

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
        int rows = Integer.parseInt(problemTokens[0]);
        int columns = Integer.parseInt(problemTokens[1]);
        int vehicles = Integer.parseInt(problemTokens[2]);
        int numberOfRides = Integer.parseInt(problemTokens[3]);
        int bonus = Integer.parseInt(problemTokens[4]);
        int steps = Integer.parseInt(problemTokens[5]);

        List<Ride> rides = new ArrayList<>(numberOfRides);
        for (int i = 1; i < lines.size(); i++) {
            String[] rideTokens = lines.get(i).trim().split("\\s");
            int xStart = Integer.parseInt(rideTokens[0]);
            int yStart = Integer.parseInt(rideTokens[1]);
            Position start = new Position(xStart, yStart);
            int xFinish = Integer.parseInt(rideTokens[2]);
            int yFinish = Integer.parseInt(rideTokens[3]);
            Position finish = new Position(xFinish, yFinish);
            int earliestStart = Integer.parseInt(rideTokens[4]);
            int latestFinish = Integer.parseInt(rideTokens[5]);
            Ride ride = new Ride(i - 1, start, finish, earliestStart, latestFinish);
            rides.add(ride);
        }
        return new SelfDrivingInput(rows, columns, vehicles, numberOfRides, bonus, steps, rides);
    }

}
