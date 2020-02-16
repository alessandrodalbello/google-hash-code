package org.hashcode.practice2020.solvers;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.hashcode.practice2020.model.Pizza;
import org.hashcode.practice2020.model.PizzaInput;
import org.hashcode.practice2020.model.PizzaOutput;
import org.hashcode.Solver;

public class GreedySolver implements Solver<PizzaInput, PizzaOutput> {

    @Override
    public PizzaOutput solve(PizzaInput inputData) {
        List<Pizza> sortedPizzas = sortPizzasByValue(inputData.getPizzas().values());
        final List<Pizza> pizzasToOrder = retrieveMostValuablePizzas(inputData.getMaxSlices(), sortedPizzas);
        return new PizzaOutput(pizzasToOrder);
    }

    private List<Pizza> sortPizzasByValue(Collection<Pizza> pizzas) {
        return pizzas.stream()
                .sorted(Comparator.comparingInt(Pizza::getValue).reversed())
                .collect(Collectors.toList());
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

}
