package it.perhashperaadhashtra.practice;

import java.util.Arrays;

public class PizzaOutput implements OutputData {

    private final int pizzasNumb;
    private final int[] pizzas;

    public PizzaOutput(int pizzasNumb, int[] pizzas) {
        this.pizzasNumb = pizzasNumb;
        this.pizzas = pizzas;
    }

    public int getPizzasNumb() {
        return pizzasNumb;
    }

    public int[] getPizzas() {
        return pizzas;
    }

    @Override
    public String toString() {
        return PizzaOutput.class.getSimpleName() + "{" +
                "pizzasNumb=" + pizzasNumb +
                ", pizzas=" + Arrays.toString(pizzas) +
                "}";
    }

}
