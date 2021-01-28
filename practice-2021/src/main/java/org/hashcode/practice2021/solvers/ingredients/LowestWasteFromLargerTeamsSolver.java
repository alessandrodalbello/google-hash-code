package org.hashcode.practice2021.solvers.ingredients;

public class LowestWasteFromLargerTeamsSolver extends BaseFromLargerTeamsSolver {

    public LowestWasteFromLargerTeamsSolver() {
        super(new LowestWastePizzaSelector());
    }
}
