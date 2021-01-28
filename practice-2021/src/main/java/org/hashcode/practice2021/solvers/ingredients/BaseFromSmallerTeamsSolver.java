package org.hashcode.practice2021.solvers.ingredients;

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

abstract class BaseFromSmallerTeamsSolver implements Solver<PizzeriaInput, PizzeriaOutput> {

    private final PizzaSelector pizzaSelector;

    protected BaseFromSmallerTeamsSolver(PizzaSelector pizzaSelector) {
        this.pizzaSelector = pizzaSelector;
    }

    @Override
    public PizzeriaOutput solve(PizzeriaInput inputData) {
        List<Pizza> pizzas = inputData.getPizzas();
        pizzas.sort(Comparator.comparing(Pizza::getNumberOfIngredients).reversed());
        assert pizzas.get(0).getNumberOfIngredients() > pizzas.get(pizzas.size() - 1).getNumberOfIngredients();

        List<Delivery> deliveries = chooseDeliveries(pizzas,
                inputData.get4PersonsTeams(), inputData.get3PersonsTeams(), inputData.get2PersonsTeams());
        return new PizzeriaOutput(deliveries);
    }

    private List<Delivery> chooseDeliveries(List<Pizza> pizzas, int teams4, int teams3, int teams2) {
        List<Delivery> deliveries = new ArrayList<>();

        while ((pizzas.size() >= 2 && teams2 >= 1)
                || (pizzas.size() >= 3 && teams3 >= 1)
                || (pizzas.size() >= 4 && teams4 >= 1)) {
            int teamSize;
            if (teams2 >= 1) {
                teamSize = 2;
                teams2 -= 1;
            } else if (teams3 >= 1) {
                teamSize = 3;
                teams3 -= 1;
            } else {
                teamSize = 4;
                teams4 -= 1;
            }

            List<Integer> chosenPizzas = new ArrayList<>(4);
            Pizza firstPizza = pizzas.remove(0);
            chosenPizzas.add(firstPizza.getId());
            Set<String> chosenIngredients = new HashSet<>(firstPizza.getIngredients());

            for (int t = teamSize - 1; t >= 1; t--) {
                pizzaSelector.choosePizza(pizzas, chosenPizzas, chosenIngredients);
            }
            assert chosenPizzas.size() == teamSize;
            Delivery delivery = new Delivery(teamSize, chosenIngredients.size(), chosenPizzas);
            deliveries.add(delivery);
        }
        assert pizzas.size() < 2 || (teams4 + teams3 + teams2 == 0)
                || (pizzas.size() == 2 && teams2 == 0) || (pizzas.size() == 3 && teams2 == 0 && teams3 == 0);
        return deliveries;
    }
}
