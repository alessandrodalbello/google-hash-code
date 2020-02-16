package org.hashcode.practice2020.io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.io.CharSink;
import com.google.common.io.Files;
import org.hashcode.practice2020.model.Pizza;
import org.hashcode.practice2020.model.PizzaOutput;
import org.hashcode.io.OutputWriter;

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
        List<Pizza> pizzas = outputData.getPizzas();
        String pizzasNumb = String.valueOf(pizzas.size());
        String pizzaTypes = pizzas.stream()
                .map(pizza -> String.valueOf(pizza.getType()))
                .collect(Collectors.joining(" "));
        CharSink charSink = Files.asCharSink(outputFile, StandardCharsets.UTF_8);
        charSink.writeLines(List.of(pizzasNumb, pizzaTypes));
    }

}
