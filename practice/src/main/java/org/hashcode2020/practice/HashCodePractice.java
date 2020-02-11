package org.hashcode2020.practice;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.hashcode2020.Solver;
import org.hashcode2020.practice.io.PizzaInputReader;
import org.hashcode2020.practice.io.PizzaOutputWriter;
import org.hashcode2020.practice.model.PizzaInput;
import org.hashcode2020.practice.model.PizzaOutput;
import org.hashcode2020.practice.solvers.AleSolver;

public class HashCodePractice {

    private final char problemPrefix;
    private final Solver<PizzaInput, PizzaOutput> solver;

    public HashCodePractice(char problemPrefix) {
        this.problemPrefix = problemPrefix;
        this.solver = new AleSolver();
    }

    public void run() throws RuntimeException {
        final long startTime = System.nanoTime();

        PizzaInput inputData;
        try {
            System.out.print("Loading input file...");
            inputData = PizzaInputReader.getInstance().readInputFile(problemPrefix);
            System.out.println("        OK");
        } catch (IOException ioe) {
            System.err.println(String.format("\n\nAn error occurred loading input file '%c'\n", problemPrefix));
            throw new RuntimeException(ioe);
        }

        System.out.print("Solving problem...");
        PizzaOutput outputData = solver.solve(inputData);
        System.out.println("           OK");

        try {
            System.out.print("Writing solution...");
            PizzaOutputWriter.getInstance().writeSolution(outputData, problemPrefix);
            System.out.println("          OK");
        } catch (IOException ioe) {
            System.err.println("\n\nAn error occurred writing solution to file\n");
            throw new RuntimeException(ioe);
        }

        final long endTime = System.nanoTime();
        System.out.println(String.format("\nFinal score of this solution is %d.", outputData.getSolutionScore()));
        System.out.println(String.format("Problem solved in %d ms.",
                TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS)));
    }

    public static void main(String[] args) {
        char problemPrefix;
        if (args.length == 1) {
            String argument = args[0].toLowerCase();
            if (argument.length() != 1 || argument.compareTo("a") < 0 || argument.compareTo("e") > 0) {
                throw new IllegalArgumentException("Invalid argument. Only 'a', 'b', 'c', 'd' or 'e' are expected.");
            }
            problemPrefix = argument.charAt(0);
        } else if (args.length == 0) {
            problemPrefix = 'a';
        } else {
            throw new IllegalArgumentException("Too many arguments. Expected at most one argument.");
        }

        HashCodePractice hashCode = new HashCodePractice(problemPrefix);
        try {
            hashCode.run();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

}
