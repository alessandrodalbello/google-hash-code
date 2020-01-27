package it.perhashperaadhashtra.practice.io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.io.CharSink;
import com.google.common.io.Files;
import it.perhashperaadhashtra.practice.PizzaOutput;

public class PizzaOutputWriter extends OutputWriter<PizzaOutput> {

    private static PizzaOutputWriter instance = null;

    private PizzaOutputWriter() {
        // not visible
    }

    public static PizzaOutputWriter getInstance() {
        if (instance == null) {
            instance = new PizzaOutputWriter();
        }
        return instance;
    }

    @Override
    protected void writeFile(PizzaOutput outputData, File outputFile) throws IOException {
        String pizzasNumb = String.valueOf(outputData.getPizzasNumb());
        String pizzas = Arrays.stream(outputData.getPizzas())
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));
        CharSink charSink = Files.asCharSink(outputFile, StandardCharsets.UTF_8);
        charSink.writeLines(List.of(pizzasNumb, pizzas));
    }

}
