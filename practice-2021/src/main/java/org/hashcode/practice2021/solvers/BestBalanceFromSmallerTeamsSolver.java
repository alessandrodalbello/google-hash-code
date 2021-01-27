package org.hashcode.practice2021.solvers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hashcode.Solver;
import org.hashcode.practice2021.model.Delivery;
import org.hashcode.practice2021.model.Pizza;
import org.hashcode.practice2021.model.PizzeriaInput;
import org.hashcode.practice2021.model.PizzeriaOutput;

public class BestBalanceFromSmallerTeamsSolver implements Solver<PizzeriaInput, PizzeriaOutput> {

    @Override
    public PizzeriaOutput solve(PizzeriaInput inputData) {
        List<Pizza> pizzas = inputData.getPizzas();
        pizzas.sort(Comparator.comparing(Pizza::getNumberOfIngredients).reversed());
        assert pizzas.get(0).getNumberOfIngredients() > pizzas.get(pizzas.size() - 1).getNumberOfIngredients();

        List<Delivery> deliveries = chooseDeliveries(pizzas,
                inputData.get4PersonsTeams(), inputData.get3PersonsTeams(), inputData.get2PersonsTeams());
        return new PizzeriaOutput(deliveries);
    }

    private List<Delivery> chooseDeliveries(List<Pizza> pizzas, int a, int b, int c) {
        List<Delivery> deliveries = new ArrayList<>();

        while ((pizzas.size() >= 2 && c >= 1)
                || (pizzas.size() >= 3 && b >= 1)
                || (pizzas.size() >= 4 && a >= 1)) {
            int teamSize;
            if (c >= 1) {
                teamSize = 2;
                c -= 1;
            } else if (b >= 1) {
                teamSize = 3;
                b -= 1;
            } else {
                teamSize = 4;
                a -= 1;
            }

            List<Integer> chosenPizzas = new ArrayList<>(4);
            Set<String> chosenIngredients = new HashSet<>();
            Pizza firstPizza = pizzas.remove(0);
            chosenPizzas.add(firstPizza.getId());
            chosenIngredients.addAll(firstPizza.getIngredients());

            for (int t = teamSize - 1; t >= 1; t--) {
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
            assert chosenPizzas.size() == teamSize;
            Delivery delivery = new Delivery(teamSize, chosenIngredients.size(), chosenPizzas);
            deliveries.add(delivery);
        }
        assert pizzas.size() < 2 || (a + b + c == 0)
                || (pizzas.size() == 2 && c == 0) || (pizzas.size() == 3 && c == 0 && b == 0);
        return deliveries;
    }
}
