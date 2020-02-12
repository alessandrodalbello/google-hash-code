package org.hashcode2020.practice;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.hashcode2020.Solver;
import org.hashcode2020.practice.io.PizzaInputReader;
import org.hashcode2020.practice.io.PizzaOutputWriter;
import org.hashcode2020.practice.model.PizzaInput;
import org.hashcode2020.practice.model.PizzaOutput;
import org.hashcode2020.practice.solvers.GreedySolver;

public class HashCodePractice {

    private final char inputPrefix;
    private final Solver<PizzaInput, PizzaOutput> solver;

    public HashCodePractice(char inputPrefix) {
        this.inputPrefix = inputPrefix;
        this.solver = new GreedySolver();
    }

    public void run() throws RuntimeException {
        final long startTime = System.nanoTime();
        System.out.println(String.format("### INPUT '%c' ###", inputPrefix));

        PizzaInput inputData = readInput();
        PizzaOutput outputData = solveProblem(inputData);
        writeOutput(outputData);

        System.out.println(String.format("\nFinal score of this solution is %d.", outputData.getSolutionScore()));
        System.out.println(String.format("Problem solved in %d ms.", elapsedFrom(startTime)));
        System.out.println("#################\n");
    }

    private PizzaInput readInput() {
        final long startTime = System.nanoTime();
        try {
            System.out.print("Loading input file...");
            PizzaInput inputData = PizzaInputReader.getInstance().readInputFile(inputPrefix);
            System.out.println(String.format("   \u2713 (%d ms.)", elapsedFrom(startTime)));
            return inputData;
        } catch (IOException ioe) {
            System.err.println(String.format("\n\nAn error occurred loading input file '%c'\n", inputPrefix));
            throw new RuntimeException(ioe);
        }
    }

    private PizzaOutput solveProblem(PizzaInput inputData) {
        final long startTime = System.nanoTime();
        System.out.print("Solving problem...");
        PizzaOutput outputData = solver.solve(inputData);
        System.out.println(String.format("      \u2713 (%d ms.)", elapsedFrom(startTime)));
        return outputData;
    }

    private void writeOutput(PizzaOutput outputData) {
        final long startTime = System.nanoTime();
        try {
            System.out.print("Writing solution...");
            PizzaOutputWriter.getInstance().writeSolution(outputData, inputPrefix);
            System.out.println(String.format("     \u2713 (%d ms.)", elapsedFrom(startTime)));
        } catch (IOException ioe) {
            System.err.println("\n\nAn error occurred writing solution to file\n");
            throw new RuntimeException(ioe);
        }
    }

    private long elapsedFrom(long startTime) {
        final long elapsed = System.nanoTime() - startTime;
        return TimeUnit.MILLISECONDS.convert(elapsed, TimeUnit.NANOSECONDS);
    }

    public static void main(String[] arguments) {
        for (String argument : arguments) {
            if (argument.length() != 1 || argument.compareToIgnoreCase("a") < 0 || argument.compareToIgnoreCase("e") > 0) {
                throw new IllegalArgumentException(String.format("Invalid argument '%s'. Only 'a', 'b', 'c', 'd' or 'e' are expected.", argument));
            }
        }
        if (arguments.length == 0) {
            throw new IllegalArgumentException("No arguments provided. Expected at least one argument between 'a', 'b', 'c', 'd' or 'e'.");
        }

        for (String inputPrefix : arguments) {
            try {
                HashCodePractice hashCode = new HashCodePractice(inputPrefix.charAt(0));
                hashCode.run();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
    }

}
