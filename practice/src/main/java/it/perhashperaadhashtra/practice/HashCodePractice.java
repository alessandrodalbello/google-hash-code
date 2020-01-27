package it.perhashperaadhashtra.practice;

import java.io.IOException;

import it.perhashperaadhashtra.practice.io.InputReader;

public class HashCodePractice {

    public static void main(String[] args) {
        final char inputPrefix = 'a';

        PizzaInput inputData = null;
        try {
            System.out.print("Loading input file...");
            inputData = InputReader.loadInputFile(inputPrefix);
            System.out.println("        OK");
        } catch (IOException ioe) {
            System.err.println(String.format("\nAn error occurred loading input file '%c'", inputPrefix));
            ioe.printStackTrace(System.err);
        }

        System.out.println(inputData);
    }

}
