package org.hashcode;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.hashcode.io.InputReader;
import org.hashcode.io.OutputWriter;

public abstract class HashCodeRunner<IN extends InputData, OUT extends OutputData> implements Runnable {

    private final char inputPrefix;
    private final InputReader<IN> inputReader;
    private final Solver<IN, OUT> solver;
    private final OutputWriter<OUT> outputWriter;

    protected HashCodeRunner(char inputPrefix, InputReader<IN> inputReader,
             Solver<IN, OUT> solver, OutputWriter<OUT> outputWriter) {
        this.inputPrefix = inputPrefix;
        this.inputReader = inputReader;
        this.solver = solver;
        this.outputWriter = outputWriter;
    }

    @Override
    public void run() throws RuntimeException {
        final long startTime = System.nanoTime();
        System.out.println(String.format("### INPUT '%c' ###", inputPrefix));

        IN inputData = readInput();
        OUT outputData = solveProblem(inputData);
        writeOutput(outputData);

        System.out.println(String.format("\nFinal score of this solution is %d.", outputData.getSolutionScore()));
        System.out.println(String.format("Problem solved in %d ms.", elapsedFrom(startTime)));
        System.out.println("#################\n");
    }

    private IN readInput() {
        final long startTime = System.nanoTime();
        try {
            System.out.print("Loading input file...");
            IN inputData = inputReader.readInputFile(inputPrefix);
            System.out.println(String.format("   \u2713 (%d ms.)", elapsedFrom(startTime)));
            return inputData;
        } catch (IOException ioe) {
            System.err.println(String.format("\n\nAn error occurred loading input file '%c'\n", inputPrefix));
            throw new RuntimeException(ioe);
        }
    }

    private OUT solveProblem(IN inputData) {
        final long startTime = System.nanoTime();
        System.out.print("Solving problem...");
        OUT outputData = solver.solve(inputData);
        System.out.println(String.format("      \u2713 (%d ms.)", elapsedFrom(startTime)));
        return outputData;
    }

    private void writeOutput(OUT outputData) {
        final long startTime = System.nanoTime();
        try {
            System.out.print("Writing solution...");
            outputWriter.writeSolution(outputData, inputPrefix);
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

}
