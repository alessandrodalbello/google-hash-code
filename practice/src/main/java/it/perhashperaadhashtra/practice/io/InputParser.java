package it.perhashperaadhashtra.practice.io;

import java.io.File;
import java.io.IOException;

import it.perhashperaadhashtra.practice.InputData;

public interface InputParser<IN extends InputData> {

    IN parse(File inputFile) throws IOException;

}
