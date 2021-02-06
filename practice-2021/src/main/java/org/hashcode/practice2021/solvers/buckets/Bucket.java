package org.hashcode.practice2021.solvers.buckets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hashcode.practice2021.model.Pizza;

class Bucket {
    private final List<Pizza> pizzas;
    private final Set<String> ingredients;

    public Bucket(Pizza pizza) {
        pizzas = new ArrayList<>(4);
        ingredients = new HashSet<>();
        addPizza(pizza);
    }

    public int size() {
        return pizzas.size();
    }

    public int ingredients() {
        return ingredients.size();
    }

    public List<Integer> pizzaIds() {
        return pizzas.stream()
                .map(Pizza::getId)
                .collect(Collectors.toList());
    }

    public int calculateWaste(Pizza pizza) {
        return (int) pizza.getIngredients().stream()
                .filter(ingredients::contains)
                .count();
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
        ingredients.addAll(pizza.getIngredients());
    }

    public Pizza getPizza(int index) {
        return pizzas.get(index);
    }
}
