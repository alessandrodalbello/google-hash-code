package it.perhashperaadhashtra.practice;

public class DummySolver implements Solver<PizzaInput, PizzaOutput> {

    @Override
    public PizzaOutput solve(PizzaInput inputData) {
        return new PizzaOutput(3, new int[] { 0, 2, 3 });
    }

}
