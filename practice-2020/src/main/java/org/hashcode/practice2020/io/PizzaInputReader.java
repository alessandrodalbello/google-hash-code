package org.hashcode.practice2020.io;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.common.io.CharStreams;
import org.hashcode.io.InputReader;
import org.hashcode.practice2020.model.Pizza;
import org.hashcode.practice2020.model.PizzaInput;

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
    public PizzaInput readFile(Readable readable) throws IOException {
        List<String> lines = CharStreams.readLines(readable);
        String[] tokens = lines.get(0).trim().split("\\s");
        int maxSlices = Integer.parseInt(tokens[0]);
        String[] slices = lines.get(1).trim().split("\\s");
        List<Pizza> pizzas = IntStream.range(0, slices.length)
                .mapToObj(type -> new Pizza(type, Integer.parseInt(slices[type])))
                .collect(Collectors.toUnmodifiableList());
        return new PizzaInput(maxSlices, pizzas);
    }

}
