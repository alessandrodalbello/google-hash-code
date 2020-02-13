package org.hashcode2020.practice.solvers;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.hashcode2020.Solver;
import org.hashcode2020.practice.model.Pizza;
import org.hashcode2020.practice.model.PizzaInput;
import org.hashcode2020.practice.model.PizzaOutput;

public class RepetitiveGreedySolver implements Solver<PizzaInput, PizzaOutput> {

    @Override
    public PizzaOutput solve(PizzaInput inputData) {
        final int maxSlices = inputData.getMaxSlices();
        List<Pizza> sortedPizzas = sortPizzasByValue(inputData.getPizzas().values());
        List<Pizza> pizzasToOrder = solve(maxSlices, sortedPizzas);
        return new PizzaOutput(pizzasToOrder);
    }

    private List<Pizza> sortPizzasByValue(Collection<Pizza> pizzas) {
        return pizzas.stream()
                .sorted(Comparator.comparingInt(Pizza::getValue).reversed())
                .collect(Collectors.toList());
    }

    private List<Pizza> solve(int maxSlices, List<Pizza> sortedPizzas) {
        List<Pizza> remainingPizzas = List.copyOf(sortedPizzas);
        List<Pizza> mostValuablePizzas = retrieveMostValuablePizzas(maxSlices, remainingPizzas);
        int mostValuableSlices = calculateValue(mostValuablePizzas);
        while (mostValuableSlices < maxSlices && !remainingPizzas.isEmpty()) {
            remainingPizzas = remainingPizzas.subList(1, remainingPizzas.size());
            List<Pizza> pizzasToOrder = retrieveMostValuablePizzas(maxSlices, remainingPizzas);
            int slicesToOrder = calculateValue(pizzasToOrder);
            if (slicesToOrder > mostValuableSlices) {
                mostValuableSlices = slicesToOrder;
                mostValuablePizzas = pizzasToOrder;
            }
        }
        return mostValuablePizzas;
    }

    private List<Pizza> retrieveMostValuablePizzas(int maxSlices, List<Pizza> sortedPizzas) {
        final List<Pizza> pizzasToOrder = new LinkedList<>();
        int remainingSlices = maxSlices;
        int numberOfPizzas = sortedPizzas.size();
        for (int i = 0; remainingSlices > 0 && i < numberOfPizzas; i++) {
            final Pizza pizza = sortedPizzas.get(i);
            if (pizza.getValue() <= remainingSlices) {
                pizzasToOrder.add(0, pizza);
                remainingSlices -= pizza.getSlices();
            }
        }
        return pizzasToOrder;
    }

    private int calculateValue(List<Pizza> pizzas) {
        return pizzas.stream()
                .reduce(0, (totalSlices, pizza) -> totalSlices += pizza.getSlices(), Integer::sum);
    }

}
