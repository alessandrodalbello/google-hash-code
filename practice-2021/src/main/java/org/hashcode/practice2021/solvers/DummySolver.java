package org.hashcode.practice2021.solvers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hashcode.Solver;
import org.hashcode.practice2021.model.Delivery;
import org.hashcode.practice2021.model.Pizza;
import org.hashcode.practice2021.model.PizzeriaInput;
import org.hashcode.practice2021.model.PizzeriaOutput;

public class DummySolver implements Solver<PizzeriaInput, PizzeriaOutput> {

    @Override
    public PizzeriaOutput solve(PizzeriaInput inputData) {
        Pizza pizza1 = inputData.getPizzas().get(0);
        Pizza pizza2 = inputData.getPizzas().get(1);
        Set<String> ingredients = new HashSet<>(pizza1.getIngredients());
        ingredients.addAll(pizza2.getIngredients());
        Delivery delivery = new Delivery(2, ingredients.size(), List.of(pizza1.getId(), pizza2.getId()));
        return new PizzeriaOutput(List.of(delivery));
    }

}
