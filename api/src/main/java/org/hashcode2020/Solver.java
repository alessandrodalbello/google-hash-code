package org.hashcode2020;

public interface Solver<IN extends InputData, OUT extends OutputData> {

    OUT solve(IN inputData);

}
