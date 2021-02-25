package org.hashcode.qualification2021;

import org.hashcode.HashCodeRunner;
import org.hashcode.Solver;
import org.hashcode.qualification2021.io.TrafficInputReader;
import org.hashcode.qualification2021.io.TrafficOutputWriter;
import org.hashcode.qualification2021.model.TrafficInput;
import org.hashcode.qualification2021.model.TrafficOutput;
import org.hashcode.qualification2021.solvers.DummySolver;

public class HashCodeQualification extends HashCodeRunner<TrafficInput, TrafficOutput> {

    protected HashCodeQualification(char inputPrefix, Solver<TrafficInput, TrafficOutput> solver) {
        super(inputPrefix, TrafficInputReader.getInstance(), solver, TrafficOutputWriter.getInstance());
    }

    public static void main(String[] arguments) {
        if (arguments.length == 0) {
            throw new IllegalArgumentException("No arguments provided. Expected at least one argument between 'a', 'b', 'c', 'd', 'e' or 'f'.");
        }

        for (String argument : arguments) {
            if (argument.length() != 1 || argument.compareToIgnoreCase("a") < 0 || argument.compareToIgnoreCase("f") > 0) {
                throw new IllegalArgumentException(String.format("Invalid argument '%s'. Only 'a', 'b', 'c', 'd', 'e' or 'f' are expected.", argument));
            }

            char inputPrefix = argument.charAt(0);
            Solver<TrafficInput, TrafficOutput> solver = new DummySolver();
            HashCodeQualification hashCode = new HashCodeQualification(inputPrefix, solver);
            hashCode.run();
        }
    }

}
