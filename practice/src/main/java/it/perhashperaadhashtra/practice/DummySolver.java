package it.perhashperaadhashtra.practice;

import java.util.List;

public class DummySolver implements Solver<PizzaInput, PizzaOutput> {

    @Override
    public PizzaOutput solve(PizzaInput inputData) {
        Pizza pizza0 = new Pizza(0, 2);
        Pizza pizza2 = new Pizza(2, 6);
        Pizza pizza3 = new Pizza(3, 8);
        return new PizzaOutput(List.of(pizza0, pizza2, pizza3));
    }

}
