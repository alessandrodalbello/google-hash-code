package org.hashcode.qualification2021.model;

import java.util.List;

import org.hashcode.OutputData;

public class TrafficOutput implements OutputData {

    private final List<Schedule> schedules;

    public TrafficOutput(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    @Override
    public long getSolutionScore() {
        return 0L;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    @Override
    public String toString() {
        return TrafficOutput.class.getSimpleName() + "{" +
                "schedules=" + schedules +
                "}";
    }

}
