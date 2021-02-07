package org.hashcode.qualification2017;

import org.hashcode.HashCodeRunner;
import org.hashcode.Solver;
import org.hashcode.qualification2017.io.VideosInputReader;
import org.hashcode.qualification2017.io.VideosOutputWriter;
import org.hashcode.qualification2017.model.VideosInput;
import org.hashcode.qualification2017.model.VideosOutput;
import org.hashcode.qualification2017.solvers.NaiveVideoSortedSolver;

public class HashCodeQualification extends HashCodeRunner<VideosInput, VideosOutput> {

    protected HashCodeQualification(char inputPrefix, Solver<VideosInput, VideosOutput> solver) {
        super(inputPrefix, VideosInputReader.getInstance(), solver, VideosOutputWriter.getInstance());
    }

    public static void main(String[] arguments) {
        if (arguments.length == 0) {
            throw new IllegalArgumentException("No arguments provided. Expected at least one argument between 'a', 'b', 'c', 'd' or 'e'.");
        }

        for (String argument : arguments) {
            if (argument.length() != 1 || argument.compareToIgnoreCase("a") < 0 || argument.compareToIgnoreCase("e") > 0) {
                throw new IllegalArgumentException(String.format("Invalid argument '%s'. Only 'a', 'b', 'c', 'd', 'e' are expected.", argument));
            }

            char inputPrefix = argument.charAt(0);
            Solver<VideosInput, VideosOutput> solver = new NaiveVideoSortedSolver();
            HashCodeQualification hashCode = new HashCodeQualification(inputPrefix, solver);
            hashCode.run();
        }
    }

}
