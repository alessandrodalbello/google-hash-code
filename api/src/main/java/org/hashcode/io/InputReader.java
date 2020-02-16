package org.hashcode.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.hashcode.InputData;

public abstract class InputReader<IN extends InputData> {

    private static final String INPUT_FOLDER = "/input/";

    private final String[] inputFilenames;

    protected abstract IN readFile(Readable readable) throws IOException;

    protected InputReader(String[] inputFilenames) {
        this.inputFilenames = inputFilenames;
    }

    public IN readInputFile(char problemPrefix) throws IOException {
        int fileIndex = problemPrefix - 'a';
        String inputFilename = INPUT_FOLDER + inputFilenames[fileIndex];
        InputStream inputStream = getClass().getResourceAsStream(inputFilename);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        return readFile(inputStreamReader);
    }

}
