package org.hashcode2020.practice.solvers;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hashcode2020.Solver;
import org.hashcode2020.practice.model.Pizza;
import org.hashcode2020.practice.model.PizzaInput;
import org.hashcode2020.practice.model.PizzaOutput;

public class RepetitiveGreedySolver implements Solver<PizzaInput, PizzaOutput> {

    private final GreedySolver greedySolver;

    public RepetitiveGreedySolver() {
        greedySolver = new GreedySolver();
    }

    @Override
    public PizzaOutput solve(PizzaInput inputData) {
        final int totalMaxSlices = inputData.getMaxSlices();
        Map<Integer, Pizza> remainingPizzas = new HashMap<>(inputData.getPizzas());

        PizzaOutput bestSolutionFound = new PizzaOutput(List.of());
        boolean isBetterSolutionPossible;
        do {
            List<Pizza> pizzas = List.copyOf(remainingPizzas.values());
            PizzaOutput greedySolution = greedySolver.solve(new PizzaInput(totalMaxSlices, pizzas));
            if (bestSolutionFound.getSolutionScore() < greedySolution.getSolutionScore()) {
                bestSolutionFound = greedySolution;
            }

            int pizzaTypeToRemove = remainingPizzas.size() - 1;
            if (remainingPizzas.containsKey(pizzaTypeToRemove)) {
                remainingPizzas.remove(pizzaTypeToRemove);
            } else {
                remainingPizzas.remove(pizzaTypeToRemove - 1);
            }
            isBetterSolutionPossible = isBetterSolutionPossible(totalMaxSlices, bestSolutionFound, remainingPizzas.values());
        } while (isBetterSolutionPossible);
        return bestSolutionFound;
    }

    private boolean isBetterSolutionPossible(int maxSlices, PizzaOutput bestSolutionFound, Collection<Pizza> pizzas) {
        return bestSolutionFound.getSolutionScore() < maxSlices && totalSlices(pizzas) > bestSolutionFound.getSolutionScore();
    }

    private int totalSlices(Collection<Pizza> pizzas) {
        return pizzas.stream()
                .reduce(0, (totalSlices, pizza) -> totalSlices += pizza.getSlices(), Integer::sum);
    }

}
