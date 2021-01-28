package org.hashcode.practice2021.solvers.ingredients;

public class LowestWasteFromSmallerTeamsSolver extends BaseFromSmallerTeamsSolver {

    public LowestWasteFromSmallerTeamsSolver() {
        super(new LowestWastePizzaSelector());
    }
}
