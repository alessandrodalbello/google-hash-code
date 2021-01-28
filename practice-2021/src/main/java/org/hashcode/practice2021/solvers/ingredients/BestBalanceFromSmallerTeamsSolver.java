package org.hashcode.practice2021.solvers.ingredients;

import java.util.List;
import java.util.Set;

import org.hashcode.practice2021.model.Pizza;

public class BestBalanceFromSmallerTeamsSolver extends BaseFromSmallerTeamsSolver {

    @Override
    protected void choosePizza(List<Pizza> pizzas, List<Integer> chosenPizzas, Set<String> chosenIngredients) {
        int chosenPizzaIndex = -1;
        int bestBalance = Integer.MIN_VALUE;
        for (int i = 0; i < pizzas.size(); i++) {
            Pizza pizza = pizzas.get(i);
            if (pizza.getNumberOfIngredients() <= bestBalance) {
                break;
            }

            int newIngredients = (int) pizza.getIngredients().stream()
                    .filter(ingredient -> !chosenIngredients.contains(ingredient))
                    .count();
            int waste = (int) pizza.getIngredients().stream()
                    .filter(chosenIngredients::contains)
                    .count();
            if ((newIngredients - waste) > bestBalance) {
                chosenPizzaIndex = i;
                bestBalance = newIngredients - waste;
            }
        }
        assert chosenPizzaIndex >= 0 && chosenPizzaIndex < pizzas.size();

        Pizza chosenPizza = pizzas.remove(chosenPizzaIndex);
        chosenPizzas.add(chosenPizza.getId());
        chosenIngredients.addAll(chosenPizza.getIngredients());
    }
}
