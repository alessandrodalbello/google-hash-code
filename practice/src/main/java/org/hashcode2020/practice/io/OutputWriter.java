/*
 * Copyright (c) 2020 Triplebet Limited. All right reserved. Inchalla, Le Val, Alderney, GY9 3UL.
 * Company Registration Number: 1827.
 */

package org.hashcode2020.practice.io;

import java.io.File;
import java.io.IOException;

import org.hashcode2020.practice.OutputData;

abstract class OutputWriter<OUT extends OutputData> {

    private static final String SOLUTION_NAME_TEMPLATE = "%s_solution.out";

    protected abstract void writeFile(OUT outputData, File outputFile) throws IOException;

    public void writeSolution(OUT outputData, char problemPrefix) throws IOException {
        String outputFilename = String.format(SOLUTION_NAME_TEMPLATE, problemPrefix);
        File outputFile = new File(outputFilename);
        writeFile(outputData, outputFile);
    }

}
