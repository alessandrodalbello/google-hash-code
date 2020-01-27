package it.perhashperaadhashtra.practice.io;

import java.io.File;
import java.io.IOException;

import it.perhashperaadhashtra.practice.InputData;

public interface InputReader<IN extends InputData> {

    IN readFile(File inputFile) throws IOException;

}
