package org.hashcode.practice2021.solvers.ingredients;

public class LowestWasteFrom3To2TeamsSolver extends BaseFrom3To2TeamsSolver {

    public LowestWasteFrom3To2TeamsSolver() {
        super(new LowestWastePizzaSelector());
    }
}
