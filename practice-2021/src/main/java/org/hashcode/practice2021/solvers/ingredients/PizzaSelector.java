package org.hashcode.practice2021.solvers.ingredients;

import java.util.List;
import java.util.Set;

import org.hashcode.practice2021.model.Pizza;

interface PizzaSelector {

    void choosePizza(List<Pizza> pizzas, List<Integer> chosenPizzas, Set<String> chosenIngredients);

}
