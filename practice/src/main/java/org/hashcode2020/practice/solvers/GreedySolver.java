package org.hashcode2020.practice.solvers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.hashcode2020.Solver;
import org.hashcode2020.practice.model.Pizza;
import org.hashcode2020.practice.model.PizzaInput;
import org.hashcode2020.practice.model.PizzaOutput;

public class GreedySolver implements Solver<PizzaInput, PizzaOutput> {

    @Override
    public PizzaOutput solve(PizzaInput pizzaInput) {
        int remainingSlices = pizzaInput.getMaxSlices();
        List<Pizza> pizzas = pizzaInput.getPizzas().values().stream()
                .sorted(Comparator.comparingInt(Pizza::getSlices).reversed())
                .collect(Collectors.toList());
        int numberOfPizzas = pizzas.size();

        final List<Pizza> pizzasToOrder = new ArrayList<>();
        int index = 0;
        while (index < numberOfPizzas && remainingSlices > 0) {
            Pizza pizza = pizzas.get(index);
            if (pizza.getSlices() <= remainingSlices) {
                pizzasToOrder.add(pizza);
                remainingSlices -= pizza.getSlices();
            }
            index += 1;
        }
        return new PizzaOutput(pizzasToOrder);
    }

}
