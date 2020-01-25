package it.perhashperaadhashtra.practice;

import java.util.Arrays;

public class PizzaInput implements InputData {

    private final int maximumPizzaSlides;
    private final int numberOfPizzaTypes;
    private final int[] slicesPerPizzaTypes;

    public PizzaInput(int maximumPizzaSlides, int numberOfPizzaTypes, int[] slicesPerPizzaTypes) {
        this.maximumPizzaSlides = maximumPizzaSlides;
        this.numberOfPizzaTypes = numberOfPizzaTypes;
        this.slicesPerPizzaTypes = slicesPerPizzaTypes;
    }

    public int getMaximumPizzaSlides() {
        return maximumPizzaSlides;
    }

    public int getNumberOfPizzaTypes() {
        return numberOfPizzaTypes;
    }

    public int getPizzaSlices(int pizzaType) {
        return slicesPerPizzaTypes[pizzaType];
    }

    @Override
    public String toString() {
        return PizzaInput.class.getSimpleName() + "{" +
                "maximumPizzaSlides=" + maximumPizzaSlides +
                ", numberOfPizzaTypes=" + numberOfPizzaTypes +
                ", slicesPerPizzaTypes=" + Arrays.toString(slicesPerPizzaTypes) +
                '}';
    }

}
