package org.hashcode.practice2020.model;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.hashcode.InputData;

public class PizzaInput implements InputData {

    private final int maxSlices;
    private final Map<Integer, Pizza> pizzas;

    public PizzaInput(int maxSlices, List<Pizza> pizzas) {
        this.maxSlices = maxSlices;
        this.pizzas = pizzas.stream()
                .collect(Collectors.toUnmodifiableMap(Pizza::getType, Function.identity()));
    }

    public int getMaxSlices() {
        return maxSlices;
    }

    public Map<Integer, Pizza> getPizzas() {
        return pizzas;
    }

    @Override
    public String toString() {
        return PizzaInput.class.getSimpleName() + "{" +
                "maxSlices=" + maxSlices +
                ", pizzas=" + pizzas +
                "}";
    }

}
