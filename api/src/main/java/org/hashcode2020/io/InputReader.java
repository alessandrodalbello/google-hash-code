package org.hashcode2020.io;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.hashcode2020.InputData;

public abstract class InputReader<IN extends InputData> {

    private static final String INPUT_FOLDER = "/input/";

    private final String[] inputFilenames;

    protected abstract IN readFile(File inputFile) throws IOException;

    protected InputReader(String[] inputFilenames) {
        this.inputFilenames = inputFilenames;
    }

    public IN readInputFile(char problemPrefix) throws IOException {
        int fileIndex = problemPrefix - 'a';
        String inputFilename = INPUT_FOLDER + inputFilenames[fileIndex];
        URL inputFileUrl = getClass().getResource(inputFilename);
        try {
            File inputFile = new File(inputFileUrl.toURI());
            return readFile(inputFile);
        } catch (URISyntaxException urise) {
            throw new IOException(urise);
        }
    }

}
