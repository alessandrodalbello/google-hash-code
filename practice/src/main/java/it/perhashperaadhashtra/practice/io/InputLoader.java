package it.perhashperaadhashtra.practice.io;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import it.perhashperaadhashtra.practice.PizzaInput;

public class InputLoader {

    private static final String INPUT_FOLDER = "/input/";

    private static InputParser<PizzaInput> inputParser = new PizzaInputParser();
    private static String[] inputFilenames = new String[] {
            "a_example.in",
            "b_small.in",
            "c_medium.in",
            "d_quite_big.in",
            "e_also_big.in"
    };

    public static PizzaInput loadInputFile(char inputPrefix) throws IOException {
        int fileIndex = inputPrefix - 'a';
        String inputFilename = INPUT_FOLDER + inputFilenames[fileIndex];
        URL inputUrl = InputLoader.class.getResource(inputFilename);
        try {
            File inputFile = new File(inputUrl.toURI());
            return inputParser.parse(inputFile);
        } catch (URISyntaxException urise) {
            throw new IOException(urise);
        }
    }

}
