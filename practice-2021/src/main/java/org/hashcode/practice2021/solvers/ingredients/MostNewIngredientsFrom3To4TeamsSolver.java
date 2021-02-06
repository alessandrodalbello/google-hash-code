package org.hashcode.practice2021.solvers.ingredients;

public class MostNewIngredientsFrom3To4TeamsSolver extends BaseFrom3To4TeamsSolver {

    public MostNewIngredientsFrom3To4TeamsSolver() {
        super(new MostNewIngredientsPizzaSelector());
    }
}
