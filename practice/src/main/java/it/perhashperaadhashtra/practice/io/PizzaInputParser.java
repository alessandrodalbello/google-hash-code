package it.perhashperaadhashtra.practice.io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import com.google.common.io.Files;
import it.perhashperaadhashtra.practice.PizzaInput;

public class PizzaInputParser implements InputParser<PizzaInput> {

    @Override
    public PizzaInput parse(File inputFile) throws IOException {
        List<String> lines = Files.asCharSource(inputFile, StandardCharsets.UTF_8).readLines();
        String[] tokens = lines.get(0).trim().split("\\s");
        int maximumPizzaSlides = Integer.parseInt(tokens[0]);
        int numberOfPizzaTypes = Integer.parseInt(tokens[1]);
        int[] slicesPerPizzaTypes = Arrays.stream(lines.get(1).trim().split("\\s"))
                .mapToInt(Integer::parseInt)
                .toArray();
        return new PizzaInput(maximumPizzaSlides, numberOfPizzaTypes, slicesPerPizzaTypes);
    }

}
