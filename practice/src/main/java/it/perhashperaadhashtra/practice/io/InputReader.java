package it.perhashperaadhashtra.practice.io;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import it.perhashperaadhashtra.practice.InputData;

abstract class InputReader<IN extends InputData> {

    private static final String INPUT_FOLDER = "/input/";

    private final String[] inputFilenames;

    protected abstract IN readFile(File inputFile) throws IOException;

    InputReader(String[] inputFilenames) {
        this.inputFilenames = inputFilenames;
    }

    public IN readInputFile(char problemPrefix) throws IOException {
        int fileIndex = problemPrefix - 'a';
        String inputFilename = INPUT_FOLDER + inputFilenames[fileIndex];
        URL inputFileUrl = InputReader.class.getResource(inputFilename);
        try {
            File inputFile = new File(inputFileUrl.toURI());
            return readFile(inputFile);
        } catch (URISyntaxException urise) {
            throw new IOException(urise);
        }
    }

}
