package org.hashcode.practice2021.model;

import java.util.List;

import org.hashcode.InputData;

public class PizzeriaInput implements InputData {

    private final int teams2;
    private final int teams3;
    private final int teams4;
    private final List<Pizza> pizzas;

    public PizzeriaInput(int teams2, int teams3, int teams4, List<Pizza> pizzas) {
        this.teams2 = teams2;
        this.teams3 = teams3;
        this.teams4 = teams4;
        this.pizzas = pizzas;
    }

    public int get2PersonsTeams() {
        return teams2;
    }

    public int get3PersonsTeams() {
        return teams3;
    }

    public int get4PersonsTeams() {
        return teams4;
    }

    public int getTotalPersons() {
        return 2 * teams2 + 3 * teams3 + 4 * teams4;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public int getTotalPizzas() {
        return pizzas.size();
    }

    @Override
    public String toString() {
        return PizzeriaInput.class.getSimpleName() + "{" +
                "teams2=" + teams2 +
                ", teams3=" + teams3 +
                ", teams4=" + teams4 +
                ", pizzas=" + pizzas +
                "}";
    }

}
