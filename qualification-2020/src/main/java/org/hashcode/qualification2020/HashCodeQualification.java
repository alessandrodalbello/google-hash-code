package org.hashcode.qualification2020;

import org.hashcode.HashCodeRunner;
import org.hashcode.InputData;
import org.hashcode.OutputData;
import org.hashcode.Solver;
import org.hashcode.qualification2020.io.DummyInputReader;
import org.hashcode.qualification2020.io.DummyOutputWriter;
import org.hashcode.qualification2020.solvers.DummySolver;

public class HashCodeQualification extends HashCodeRunner<InputData, OutputData> {

    protected HashCodeQualification(char inputPrefix, Solver<InputData, OutputData> solver) {
        super(inputPrefix, DummyInputReader.getInstance(), solver, DummyOutputWriter.getInstance());
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
            Solver<InputData, OutputData> solver = new DummySolver();
            HashCodeQualification hashCode = new HashCodeQualification(inputPrefix, solver);
            hashCode.run();
        }
    }

}
