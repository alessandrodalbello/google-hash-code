package it.perhashperaadhashtra.practice;

import java.io.IOException;

import it.perhashperaadhashtra.practice.io.PizzaInputReader;
import it.perhashperaadhashtra.practice.io.PizzaOutputWriter;

public class HashCodePractice {

    public static void main(String[] args) {
        final char problemPrefix = 'a';
        final Solver<PizzaInput, PizzaOutput> solver = new DummySolver();

        PizzaInput inputData = null;
        try {
            System.out.print("Loading input file...");
            inputData = PizzaInputReader.getInstance().readInputFile(problemPrefix);
            System.out.println("        OK");
        } catch (IOException ioe) {
            System.err.println(String.format("\n\nAn error occurred loading input file '%c'\n", problemPrefix));
            ioe.printStackTrace(System.err);
            System.exit(1);
        }

        //System.out.println(inputData);

        System.out.print("Solving problem...");
        PizzaOutput outputData = solver.solve(inputData);
        System.out.println("           OK");

        try {
            System.out.print("Writing solution...");
            PizzaOutputWriter.getInstance().writeSolution(outputData, problemPrefix);
            System.out.println("          OK");
        } catch (IOException ioe) {
            System.err.println("\n\nAn error occurred writing solution to file\n");
            ioe.printStackTrace(System.err);
            System.exit(2);
        }
    }

}
