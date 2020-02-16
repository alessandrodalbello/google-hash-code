package org.hashcode2020.selfdriving;

import org.hashcode2020.HashCodeRunner;
import org.hashcode2020.Solver;
import org.hashcode2020.selfdriving.io.SelfDrivingInputReader;
import org.hashcode2020.selfdriving.io.SelfDrivingOutputWriter;
import org.hashcode2020.selfdriving.model.SelfDrivingInput;
import org.hashcode2020.selfdriving.model.SelfDrivingOutput;
import org.hashcode2020.selfdriving.solvers.GreedySolver;

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