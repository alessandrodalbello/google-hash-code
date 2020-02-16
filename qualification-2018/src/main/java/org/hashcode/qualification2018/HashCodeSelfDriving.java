package org.hashcode.qualification2018;

import org.hashcode.HashCodeRunner;
import org.hashcode.Solver;
import org.hashcode.qualification2018.io.SelfDrivingInputReader;
import org.hashcode.qualification2018.io.SelfDrivingOutputWriter;
import org.hashcode.qualification2018.model.SelfDrivingInput;
import org.hashcode.qualification2018.model.SelfDrivingOutput;
import org.hashcode.qualification2018.solvers.GreedySolver;

public class HashCodeSelfDriving extends HashCodeRunner<SelfDrivingInput, SelfDrivingOutput> {

    protected HashCodeSelfDriving(char inputPrefix, Solver<SelfDrivingInput, SelfDrivingOutput> solver) {
        super(inputPrefix, SelfDrivingInputReader.getInstance(), solver, SelfDrivingOutputWriter.getInstance());
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
            Solver<SelfDrivingInput, SelfDrivingOutput> solver = new GreedySolver();
            HashCodeSelfDriving hashCode = new HashCodeSelfDriving(inputPrefix, solver);
            hashCode.run();
        }
    }

}