package org.hashcode.practice2020.model;

import java.util.List;

import org.hashcode.OutputData;

public class PizzaOutput implements OutputData {

    private final List<Pizza> pizzas;

    public PizzaOutput(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    @Override
    public long getSolutionScore() {
        return pizzas.stream()
                .reduce(0, (totalSlices, pizza) -> totalSlices += pizza.getSlices(), Integer::sum);
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    @Override
    public String toString() {
        return PizzaOutput.class.getSimpleName() + "{" +
                "pizzas=" + pizzas +
                "}";
    }
}
