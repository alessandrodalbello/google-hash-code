package org.hashcode.practice2021.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.io.CharStreams;
import org.hashcode.io.InputReader;
import org.hashcode.practice2021.model.Pizza;
import org.hashcode.practice2021.model.PizzeriaInput;

public class PizzeriaInputReader extends InputReader<PizzeriaInput> {

    private static final String[] inputFilenames = new String[]{
            "a_example.in",
            "b_little_bit_of_everything.in",
            "c_many_ingredients.in",
            "d_many_pizzas.in",
            "e_many_teams.in"
    };

    private static PizzeriaInputReader instance = null;

    private PizzeriaInputReader() {
        super(inputFilenames);
    }

    public static PizzeriaInputReader getInstance() {
        if (instance == null) {
            instance = new PizzeriaInputReader();
        }
        return instance;
    }

    @Override
    public PizzeriaInput readFile(Readable readable) throws IOException {
        List<String> lines = CharStreams.readLines(readable);

        String[] firstLineTokens = lines.get(0).trim().split("\\s");
        int numberOfPizzas = Integer.parseInt(firstLineTokens[0]);
        int numberOf2PersonsTeams = Integer.parseInt(firstLineTokens[1]);
        int numberOf3PersonsTeams = Integer.parseInt(firstLineTokens[2]);
        int numberOf4PersonsTeams = Integer.parseInt(firstLineTokens[3]);

        List<Pizza> pizzas = new ArrayList<>(numberOfPizzas);
        for (int pizzaId = 0; pizzaId < numberOfPizzas; pizzaId++) {
            String[] ingredientsTokens = lines.get(pizzaId + 1).trim().split("\\s");
            Set<String> ingredients = Arrays.stream(ingredientsTokens)
                    .skip(1L)
                    .collect(Collectors.toSet());
            Pizza pizza = new Pizza(pizzaId, ingredients);
            pizzas.add(pizza);
        }
        return new PizzeriaInput(numberOf2PersonsTeams, numberOf3PersonsTeams, numberOf4PersonsTeams, pizzas);
    }

}
