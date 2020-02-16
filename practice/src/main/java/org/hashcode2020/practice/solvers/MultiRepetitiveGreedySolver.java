package org.hashcode2020.practice.solvers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hashcode2020.Solver;
import org.hashcode2020.practice.model.Pizza;
import org.hashcode2020.practice.model.PizzaInput;
import org.hashcode2020.practice.model.PizzaOutput;

public class MultiRepetitiveGreedySolver implements Solver<PizzaInput, PizzaOutput> {

    private final RepetitiveGreedySolver repetitiveGreedySolver;

    public MultiRepetitiveGreedySolver() {
        repetitiveGreedySolver = new RepetitiveGreedySolver();
    }

    @Override
    public PizzaOutput solve(PizzaInput inputData) {
        final int totalMaxSlices = inputData.getMaxSlices();
        Map<Integer, Pizza> pizzaByType = new HashMap<>(inputData.getPizzas());

        PizzaOutput bestSolutionFound = repetitiveGreedySolver.solve(inputData);
        boolean isBetterSolutionPossible = bestSolutionFound.getSolutionScore() < totalMaxSlices;
        int pizzaTypeToIgnore = pizzaByType.size() - 2;
        while (isBetterSolutionPossible && pizzaTypeToIgnore >= 0) {
            List<Pizza> pizzas = new LinkedList<>(pizzaByType.values());
            pizzas.remove(pizzaTypeToIgnore);
            PizzaOutput repetitiveGreedySolution = repetitiveGreedySolver.solve(new PizzaInput(totalMaxSlices, pizzas));
            if (bestSolutionFound.getSolutionScore() < repetitiveGreedySolution.getSolutionScore()) {
                bestSolutionFound = repetitiveGreedySolution;
                isBetterSolutionPossible = bestSolutionFound.getSolutionScore() < totalMaxSlices;
            }
            pizzaTypeToIgnore -= 1;
        }
        return bestSolutionFound;
    }

}
