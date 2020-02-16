package org.hashcode.practice2020.solvers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hashcode.practice2020.model.Pizza;
import org.hashcode.practice2020.model.PizzaInput;
import org.hashcode.practice2020.model.PizzaOutput;
import org.hashcode.Solver;

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
