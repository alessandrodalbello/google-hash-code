package org.hashcode2020.practice;

import org.hashcode2020.HashCodeRunner;
import org.hashcode2020.Solver;
import org.hashcode2020.practice.io.PizzaInputReader;
import org.hashcode2020.practice.io.PizzaOutputWriter;
import org.hashcode2020.practice.model.PizzaInput;
import org.hashcode2020.practice.model.PizzaOutput;
import org.hashcode2020.practice.solvers.GreedySolver;

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
            Solver<PizzaInput, PizzaOutput> solver = new GreedySolver();
            HashCodePractice hashCode = new HashCodePractice(inputPrefix, solver);
            hashCode.run();
        }
    }

}