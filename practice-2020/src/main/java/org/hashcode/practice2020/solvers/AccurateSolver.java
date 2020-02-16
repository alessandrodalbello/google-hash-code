package org.hashcode.practice2020.solvers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hashcode.practice2020.model.Pizza;
import org.hashcode.practice2020.model.PizzaInput;
import org.hashcode.practice2020.model.PizzaOutput;
import org.hashcode.Solver;

public class AccurateSolver implements Solver<PizzaInput, PizzaOutput> {

    private final Map<KnapsackKey, Integer> knapsackValues;
    private final Map<Integer, Pizza> pizzasByValue;
    private Map<Integer, Pizza> pizzasByType;

    public AccurateSolver() {
        knapsackValues = new HashMap<>();
        pizzasByValue = new HashMap<>();
    }

    @Override
    public PizzaOutput solve(PizzaInput inputData) {
        pizzasByType = inputData.getPizzas();
        int numberOfPizzas = pizzasByType.size();
        int maxSlices = inputData.getMaxSlices();

        int maxSlicesToOrder = solveKnapsack(new KnapsackKey(numberOfPizzas, maxSlices));
        List<Pizza> pizzasToOrder = getPizzasToOrder(maxSlicesToOrder);
        return new PizzaOutput(pizzasToOrder);
    }

    private int solveKnapsack(KnapsackKey currentKey) {
        if (knapsackValues.containsKey(currentKey)) {
            return knapsackValues.get(currentKey);
        }
        int value;
        if (currentKey.numberOfItems == 0 || currentKey.weight == 0) {
            value = 0;
        } else {
            Pizza pizza = pizzasByType.get(currentKey.numberOfItems - 1);
            if (pizza.getSlices() > currentKey.weight) {
                value = solveKnapsack(currentKey.next());
            } else {
                int valueWithoutPizza = solveKnapsack(currentKey.next());
                int valueWithPizza = solveKnapsack(currentKey.next(pizza.getSlices())) + pizza.getValue();
                if (valueWithPizza > valueWithoutPizza) {
                    value = valueWithPizza;
                    pizzasByValue.put(value, pizza);
                } else {
                    value = valueWithoutPizza;
                }
            }
        }
        knapsackValues.put(currentKey, value);
        return value;
    }

    private List<Pizza> getPizzasToOrder(int maxSlicesToOrder) {
        final List<Pizza> pizzasToOrder = new LinkedList<>();
        int remainingSlices = maxSlicesToOrder;
        while (remainingSlices > 0) {
            Pizza pizza = pizzasByValue.get(remainingSlices);
            pizzasToOrder.add(0, pizza);
            remainingSlices -= pizza.getSlices();
        }
        return pizzasToOrder;
    }

    private static class KnapsackKey {

        private final int numberOfItems;
        private final int weight;

        private KnapsackKey(int numberOfItems, int weight) {
            this.numberOfItems = numberOfItems;
            this.weight = weight;
        }

        public KnapsackKey next() {
            return next(0);
        }

        public KnapsackKey next(int weightReduction) {
            return new KnapsackKey(this.numberOfItems - 1, this.weight - weightReduction);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            KnapsackKey other = (KnapsackKey) obj;
            return Objects.equals(this.numberOfItems, other.numberOfItems) &&
                    Objects.equals(this.weight, other.weight);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(numberOfItems) + Objects.hashCode(weight);
        }

        @Override
        public String toString() {
            return KnapsackKey.class.getSimpleName() + "{" +
                    "numberOfItems=" + numberOfItems +
                    ", weight=" + weight +
                    "}";
        }
    }

}
