package org.hashcode.practice2021.solvers.ingredients;

import java.util.List;
import java.util.Set;

import org.hashcode.practice2021.model.Pizza;

public class LowestWasteFromLargerTeamsSolver extends BaseFromLargerTeamsSolver {

    @Override
    protected void choosePizza(List<Pizza> pizzas, List<Integer> chosenPizzas, Set<String> chosenIngredients) {
        int chosenPizzaIndex = -1;
        int lowestWaste = -1;
        for (int i = 0; lowestWaste != 0 && i < pizzas.size(); i++) {
            Pizza pizza = pizzas.get(i);
            int waste = (int) pizza.getIngredients().stream()
                    .filter(chosenIngredients::contains)
                    .count();
            if (lowestWaste < 0 || waste < lowestWaste) {
                chosenPizzaIndex = i;
                lowestWaste = waste;
            }
        }
        assert chosenPizzaIndex >= 0 && chosenPizzaIndex < pizzas.size();

        Pizza chosenPizza = pizzas.remove(chosenPizzaIndex);
        chosenPizzas.add(chosenPizza.getId());
        chosenIngredients.addAll(chosenPizza.getIngredients());
    }
}
