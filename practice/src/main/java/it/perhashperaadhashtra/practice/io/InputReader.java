package it.perhashperaadhashtra.practice.io;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import com.google.common.io.Files;
import it.perhashperaadhashtra.practice.PizzaInput;

public class InputReader {

    private static final String INPUT_FOLDER = "/input/";

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
        URL inputUrl = InputReader.class.getResource(inputFilename);
        try {
            File inputFile = new File(inputUrl.toURI());
            return InputReader.parse(inputFile);
        } catch (URISyntaxException urise) {
            throw new IOException(urise);
        }
    }

    private static PizzaInput parse(File inputFile) throws IOException {
        List<String> lines = Files.asCharSource(inputFile, StandardCharsets.UTF_8).readLines();
        String[] tokens = lines.get(0).trim().split("\\s");
        int maximumPizzaSlides = Integer.parseInt(tokens[0]);
        int numberOfPizzaTypes = Integer.parseInt(tokens[1]);
        int[] slicesPerPizzaTypes = Arrays.stream(lines.get(1).trim().split("\\s"))
                .mapToInt(Integer::parseInt)
                .toArray();
        return new PizzaInput(maximumPizzaSlides, numberOfPizzaTypes, slicesPerPizzaTypes);
    }

}
