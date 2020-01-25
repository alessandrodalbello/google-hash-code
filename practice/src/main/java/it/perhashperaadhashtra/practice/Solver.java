package it.perhashperaadhashtra.practice;

public interface Solver<IN extends InputData, OUT extends OutputData> {

    OUT solve(IN inputData);

}
