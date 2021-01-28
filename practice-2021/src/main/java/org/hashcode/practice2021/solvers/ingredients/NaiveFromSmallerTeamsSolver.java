package org.hashcode.practice2021.solvers.ingredients;

import java.util.List;
import java.util.Set;

import org.hashcode.practice2021.model.Pizza;

public class NaiveFromSmallerTeamsSolver extends BaseFromSmallerTeamsSolver {

    @Override
    protected void choosePizza(List<Pizza> pizzas, List<Integer> chosenPizzas, Set<String> chosenIngredients) {
        Pizza pizza = pizzas.remove(0);
        chosenPizzas.add(pizza.getId());
        chosenIngredients.addAll(pizza.getIngredients());
    }
}
