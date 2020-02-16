package org.hashcode.practice2020;

import org.hashcode.practice2020.io.PizzaInputReader;
import org.hashcode.practice2020.model.PizzaInput;
import org.hashcode.practice2020.model.PizzaOutput;
import org.hashcode.practice2020.solvers.MultiRepetitiveGreedySolver;
import org.hashcode.HashCodeRunner;
import org.hashcode.Solver;
import org.hashcode.practice2020.io.PizzaOutputWriter;

public class HashCodePractice extends HashCodeRunner<PizzaInput, PizzaOutput> {

    protected HashCodePractice(char inputPrefix, Solver<PizzaInput, PizzaOutput> solver) {
        super(inputPrefix, PizzaInputReader.getInstance(), solver, PizzaOutputWriter.getInstance());
    }

    public static void main(String[] arguments) {
        if (arguments.length == 0) {
            throw new IllegalArgumentException("No arguments provided. Expected at least one argument between 'a', 'b', 'c', 'd' or 'e'.");
        }

        for (String argument : arguments) {
            if (argument.length() != 1 || argument.compareToIgnoreCase("a") < 0 || argument.compareToIgnoreCase("e") > 0) {
                throw new IllegalArgumentException(String.format("Invalid argument '%s'. Only 'a', 'b', 'c', 'd' or 'e' are expected.", argument));
            }

            char inputPrefix = argument.charAt(0);
            Solver<PizzaInput, PizzaOutput> solver = new MultiRepetitiveGreedySolver();
            HashCodePractice hashCode = new HashCodePractice(inputPrefix, solver);
            hashCode.run();
        }
    }

}