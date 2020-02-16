package org.hashcode;

public interface Solver<IN extends InputData, OUT extends OutputData> {

    OUT solve(IN inputData);

}
