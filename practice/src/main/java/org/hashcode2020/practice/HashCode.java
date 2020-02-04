/*
 * Copyright (c) 2020 Triplebet Limited. All right reserved. Inchalla, Le Val, Alderney, GY9 3UL.
 * Company Registration Number: 1827.
 */

package org.hashcode2020.practice;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.hashcode2020.practice.io.PizzaInputReader;
import org.hashcode2020.practice.io.PizzaOutputWriter;

public class HashCode {

    private final char problemPrefix;
    private final Solver<PizzaInput, PizzaOutput> solver;

    public HashCode(char problemPrefix) {
        this.problemPrefix = problemPrefix;
        this.solver = new DummySolver();
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
        HashCode hashCode = new HashCode('a');
        try {
            hashCode.run();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

}
