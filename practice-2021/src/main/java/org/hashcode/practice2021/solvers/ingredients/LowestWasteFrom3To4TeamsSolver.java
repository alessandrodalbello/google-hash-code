package org.hashcode.practice2021.solvers.ingredients;

public class LowestWasteFrom3To4TeamsSolver extends BaseFrom3To4TeamsSolver {

    public LowestWasteFrom3To4TeamsSolver() {
        super(new LowestWastePizzaSelector());
    }
}
