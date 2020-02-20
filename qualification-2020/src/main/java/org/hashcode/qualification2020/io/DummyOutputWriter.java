package org.hashcode.qualification2020.io;

import java.io.File;
import java.io.IOException;

import org.hashcode.OutputData;
import org.hashcode.io.OutputWriter;

public class DummyOutputWriter extends OutputWriter<OutputData> {

    private static DummyOutputWriter instance = null;

    private DummyOutputWriter() {
        // not visible
    }

    public static DummyOutputWriter getInstance() {
        if (instance == null) {
            instance = new DummyOutputWriter();
        }
        return instance;
    }

    @Override
    protected void writeFile(OutputData outputData, File outputFile) throws IOException {
    }

}
