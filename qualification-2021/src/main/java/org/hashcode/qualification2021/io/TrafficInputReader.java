package org.hashcode.qualification2021.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.io.CharStreams;
import org.hashcode.io.InputReader;
import org.hashcode.qualification2021.model.Car;
import org.hashcode.qualification2021.model.Street;
import org.hashcode.qualification2021.model.TrafficInput;

public class TrafficInputReader extends InputReader<TrafficInput> {

    private final static String[] inputFilenames = new String[]{
            "a.txt",
            "b.txt",
            "c.txt",
            "d.txt",
            "e.txt",
            "f.txt"
    };

    private static TrafficInputReader instance = null;

    private TrafficInputReader() {
        super(inputFilenames);
    }

    public static TrafficInputReader getInstance() {
        if (instance == null) {
            instance = new TrafficInputReader();
        }
        return instance;
    }

    @Override
    public TrafficInput readFile(Readable readable) throws IOException {
        List<String> lines = CharStreams.readLines(readable);

        String[] firstLineTokens = lines.get(0).trim().split("\\s");
        int duration = Integer.parseInt(firstLineTokens[0]);
        int numberOfIntersections = Integer.parseInt(firstLineTokens[1]);
        int numberOfStreets = Integer.parseInt(firstLineTokens[2]);
        int numberOfCars = Integer.parseInt(firstLineTokens[3]);
        int bonusPoints = Integer.parseInt(firstLineTokens[4]);

        List<Street> streets = new ArrayList<>(numberOfStreets);
        for (int i = 0; i < numberOfStreets; i++) {
            String[] streetTokens = lines.get(i + 1).trim().split("\\s");
            int startIntersection = Integer.parseInt(streetTokens[0]);
            int endIntersection = Integer.parseInt(streetTokens[1]);
            String streetName = streetTokens[2];
            int length = Integer.parseInt(streetTokens[3]);

            Street street = new Street(streetName, startIntersection, endIntersection, length);
            streets.add(street);
        }

        Map<String, Street> streetsByName = streets.stream()
                .collect(Collectors.toMap(Street::getName, Function.identity()));
        List<Car> cars = new ArrayList<>(numberOfCars);
        for (int carId = 0; carId < numberOfCars; carId++) {
            String[] carTokens = lines.get(carId + 1 + numberOfStreets).trim().split("\\s");
            int pathLength = Integer.parseInt(carTokens[0]);
            List<String> streetNames = new ArrayList<>(pathLength);
            streetNames.addAll(Arrays.asList(carTokens).subList(1, pathLength + 1));
            List<Street> path = streetNames.stream()
                    .map(streetsByName::get)
                    .collect(Collectors.toList());
            Car car = new Car(carId, path);
            cars.add(car);
        }
        return new TrafficInput(duration, numberOfIntersections, streets, cars, bonusPoints);
    }

}
