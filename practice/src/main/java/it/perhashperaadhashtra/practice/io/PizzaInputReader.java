package it.perhashperaadhashtra.practice.io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import com.google.common.io.CharSource;
import com.google.common.io.Files;
import it.perhashperaadhashtra.practice.PizzaInput;

public class PizzaInputReader extends InputReader<PizzaInput> {

    private static String[] inputFilenames = new String[] {
            "a_example.in",
            "b_small.in",
            "c_medium.in",
            "d_quite_big.in",
            "e_also_big.in"
    };

    private static PizzaInputReader instance = null;

    private PizzaInputReader() {
        super(inputFilenames);
    }

    public static PizzaInputReader getInstance() {
        if (instance == null) {
            instance = new PizzaInputReader();
        }
        return instance;
    }

    @Override
    public PizzaInput readFile(File inputFile) throws IOException {
        CharSource charSource = Files.asCharSource(inputFile, StandardCharsets.UTF_8);
        List<String> lines = charSource.readLines();
        String[] tokens = lines.get(0).trim().split("\\s");
        int maxSlices = Integer.parseInt(tokens[0]);
        int pizzaTypes = Integer.parseInt(tokens[1]);
        int[] pizzaSlices = Arrays.stream(lines.get(1).trim().split("\\s"))
                .mapToInt(Integer::parseInt)
                .toArray();
        return new PizzaInput(maxSlices, pizzaTypes, pizzaSlices);
    }

}
