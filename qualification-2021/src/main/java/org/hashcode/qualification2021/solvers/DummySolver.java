package org.hashcode.qualification2021.solvers;

import java.util.List;

import org.hashcode.Solver;
import org.hashcode.qualification2021.model.IncomingStreet;
import org.hashcode.qualification2021.model.Schedule;
import org.hashcode.qualification2021.model.TrafficInput;
import org.hashcode.qualification2021.model.TrafficOutput;

public class DummySolver implements Solver<TrafficInput, TrafficOutput> {

    @Override
    public TrafficOutput solve(TrafficInput inputData) {
        String streetName = inputData.getStreets().get(0).getName();
        IncomingStreet incomingStreet = new IncomingStreet(streetName, 1);
        Schedule schedule = new Schedule(0, List.of(incomingStreet));
        return new TrafficOutput(inputData.getDuration(), inputData.getBonusPoints(), inputData.getCars(), List.of(schedule));
    }

}
