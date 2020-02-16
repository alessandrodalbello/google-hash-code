package org.hashcode.qualification2018.solvers;

import org.hashcode.Solver;
import org.hashcode.qualification2018.model.SelfDrivingInput;
import org.hashcode.qualification2018.model.SelfDrivingOutput;

public class FleetSchedulerSolverFactory {

    public static Solver<SelfDrivingInput, SelfDrivingOutput> byLatestFinishTimeAnyVehicleWhichCompletes() {
        return new FleetSchedulerSolver(new ByLatestFinishTimeRidesSorter(), new AnyWhichCompletesVehicleSelector());
    }

}
