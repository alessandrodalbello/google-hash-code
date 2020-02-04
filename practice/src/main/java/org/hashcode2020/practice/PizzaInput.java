/*
 * Copyright (c) 2020 Triplebet Limited. All right reserved. Inchalla, Le Val, Alderney, GY9 3UL.
 * Company Registration Number: 1827.
 */

package org.hashcode2020.practice;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public Pizza getPizzaByType(int pizzaType) {
        return pizzas.get(pizzaType);
    }

    @Override
    public String toString() {
        return PizzaInput.class.getSimpleName() + "{" +
                "maxSlices=" + maxSlices +
                ", pizzas=" + pizzas +
                "}";
    }

}
