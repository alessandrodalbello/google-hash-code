package it.perhashperaadhashtra.practice;

import java.util.Arrays;

public class PizzaInput implements InputData {

    private final int maxSlices;
    private final int pizzaTypes;
    private final int[] pizzaSlices;

    public PizzaInput(int maxSlices, int pizzaTypes, int[] pizzaSlices) {
        this.maxSlices = maxSlices;
        this.pizzaTypes = pizzaTypes;
        this.pizzaSlices = pizzaSlices;
    }

    public int getMaxSlices() {
        return maxSlices;
    }

    public int getPizzaTypes() {
        return pizzaTypes;
    }

    public int getPizzaSlices(int pizzaType) {
        return pizzaSlices[pizzaType];
    }

    public int getPizzaValue(int pizzaType) {
        return getPizzaSlices(pizzaType);
    }

    @Override
    public String toString() {
        return PizzaInput.class.getSimpleName() + "{" +
                "maxSlices=" + maxSlices +
                ", pizzaTypes=" + pizzaTypes +
                ", pizzaSlices=" + Arrays.toString(pizzaSlices) +
                "}";
    }

}
