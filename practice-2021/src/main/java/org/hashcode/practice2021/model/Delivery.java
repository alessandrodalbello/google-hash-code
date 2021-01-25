package org.hashcode.practice2021.model;

import java.util.List;

public class Delivery {

    private final int teamSize;
    private final int distinctIngredients;
    private final List<Integer> pizzaIds;

    public Delivery(int teamSize, int distinctIngredients, List<Integer> pizzaIds) {
        this.teamSize = teamSize;
        this.distinctIngredients = distinctIngredients;
        this.pizzaIds = pizzaIds;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public int getDistinctIngredients() {
        return distinctIngredients;
    }

    public List<Integer> getPizzaIds() {
        return pizzaIds;
    }

    @Override
    public String toString() {
        return Delivery.class.getSimpleName() + "{" +
                "teamSize=" + teamSize +
                ", distinctIngredients=" + distinctIngredients +
                ", pizzaIds=" + pizzaIds.size() +
                "}";
    }

}
