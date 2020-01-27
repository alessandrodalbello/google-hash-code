package it.perhashperaadhashtra.practice.io;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import it.perhashperaadhashtra.practice.InputData;

public abstract class AbstractInputReader<IN extends InputData> implements InputReader<IN> {

    private static final String INPUT_FOLDER = "/input/";

    private final String[] inputFilenames;

    AbstractInputReader(String[] inputFilenames) {
        this.inputFilenames = inputFilenames;
    }

    public IN readInputFile(char inputPrefix) throws IOException {
        int fileIndex = inputPrefix - 'a';
        String inputFilename = INPUT_FOLDER + inputFilenames[fileIndex];
        URL inputFileUrl = AbstractInputReader.class.getResource(inputFilename);
        try {
            File inputFile = new File(inputFileUrl.toURI());
            return readFile(inputFile);
        } catch (URISyntaxException urise) {
            throw new IOException(urise);
        }
    }

}
