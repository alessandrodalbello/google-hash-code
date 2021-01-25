package org.hashcode.practice2021;

import org.hashcode.HashCodeRunner;
import org.hashcode.Solver;
import org.hashcode.practice2021.io.PizzeriaInputReader;
import org.hashcode.practice2021.io.PizzeriaOutputWriter;
import org.hashcode.practice2021.model.PizzeriaInput;
import org.hashcode.practice2021.model.PizzeriaOutput;
import org.hashcode.practice2021.solvers.DummySolver;

public class HashCodePractice extends HashCodeRunner<PizzeriaInput, PizzeriaOutput> {

    protected HashCodePractice(char inputPrefix, Solver<PizzeriaInput, PizzeriaOutput> solver) {
        super(inputPrefix, PizzeriaInputReader.getInstance(), solver, PizzeriaOutputWriter.getInstance());
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
            Solver<PizzeriaInput, PizzeriaOutput> solver = new DummySolver();
            HashCodePractice hashCode = new HashCodePractice(inputPrefix, solver);
            hashCode.run();
        }
    }

}
