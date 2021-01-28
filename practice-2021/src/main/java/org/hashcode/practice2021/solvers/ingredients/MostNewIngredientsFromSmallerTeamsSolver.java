package org.hashcode.practice2021.solvers.ingredients;

import java.util.List;
import java.util.Set;

import org.hashcode.practice2021.model.Pizza;

public class MostNewIngredientsFromSmallerTeamsSolver extends BaseFromSmallerTeamsSolver {

    @Override
    protected void choosePizza(List<Pizza> pizzas, List<Integer> chosenPizzas, Set<String> chosenIngredients) {
        int chosenPizzaIndex = -1;
        int mostNewIngredients = -1;
        for (int i = 0; i < pizzas.size(); i++) {
            Pizza pizza = pizzas.get(i);
            if (pizza.getNumberOfIngredients() <= mostNewIngredients) {
                break;
            }

            int newIngredients = (int) pizza.getIngredients().stream()
                    .filter(ingredient -> !chosenIngredients.contains(ingredient))
                    .count();
            if (newIngredients > mostNewIngredients) {
                chosenPizzaIndex = i;
                mostNewIngredients = newIngredients;
            }
        }
        assert chosenPizzaIndex >= 0 && chosenPizzaIndex < pizzas.size();

        Pizza chosenPizza = pizzas.remove(chosenPizzaIndex);
        chosenPizzas.add(chosenPizza.getId());
        chosenIngredients.addAll(chosenPizza.getIngredients());
    }
}
