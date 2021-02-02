package org.hashcode.practice2021.solvers.ingredients;

public class MostNewIngredientsFrom3To2TeamsSolver extends BaseFrom3To2TeamsSolver {

    public MostNewIngredientsFrom3To2TeamsSolver() {
        super(new MostNewIngredientsPizzaSelector());
    }
}
