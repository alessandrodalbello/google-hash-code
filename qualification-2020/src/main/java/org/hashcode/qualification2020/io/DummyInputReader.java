package org.hashcode.qualification2020.io;

import java.io.IOException;

import org.hashcode.InputData;
import org.hashcode.io.InputReader;

public class DummyInputReader extends InputReader<InputData> {

    private static String[] inputFilenames = new String[] {
            "a_example.in",
            "b_small.in",
            "c_medium.in",
            "d_quite_big.in",
            "e_also_big.in"
    };

    private static DummyInputReader instance = null;

    private DummyInputReader() {
        super(inputFilenames);
    }

    public static DummyInputReader getInstance() {
        if (instance == null) {
            instance = new DummyInputReader();
        }
        return instance;
    }

    @Override
    public InputData readFile(Readable readable) throws IOException {
        return new InputData() {};
    }

}
