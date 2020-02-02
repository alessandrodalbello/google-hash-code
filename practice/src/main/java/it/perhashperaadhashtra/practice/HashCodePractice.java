package it.perhashperaadhashtra.practice;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import it.perhashperaadhashtra.practice.io.PizzaInputReader;
import it.perhashperaadhashtra.practice.io.PizzaOutputWriter;

public class HashCodePractice {

    private final char problemPrefix;
    private final Solver<PizzaInput, PizzaOutput> solver;

    public HashCodePractice(char problemPrefix) {
        this.problemPrefix = problemPrefix;
        this.solver = new DummySolver();
    }

    public void solve() throws RuntimeException {
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
        HashCodePractice hashCodePractice = new HashCodePractice('a');
        try {
            hashCodePractice.solve();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

}
