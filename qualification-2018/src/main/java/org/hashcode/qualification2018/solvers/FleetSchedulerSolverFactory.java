package org.hashcode.qualification2018.solvers;

import org.hashcode.Solver;
import org.hashcode.qualification2018.model.SelfDrivingInput;
import org.hashcode.qualification2018.model.SelfDrivingOutput;

public class FleetSchedulerSolverFactory {

    public static Solver<SelfDrivingInput, SelfDrivingOutput> byLatestFinishTimeAnyVehicleWhichCompletes() {
        return new FleetSchedulerSolver(new ByLatestFinishTimeRidesSorter(), new AnyWhichCompletesVehicleSelector());
    }

    public static Solver<SelfDrivingInput, SelfDrivingOutput> byLatestFinishTimeCloserVehicleWhichCompletes() {
        return new FleetSchedulerSolver(new ByLatestFinishTimeRidesSorter(), new CloserWhichCompletesVehicleSelector());
    }

    public static Solver<SelfDrivingInput, SelfDrivingOutput> byEarliestFinishTimeAnyVehicleWhichCompletes() {
        return new FleetSchedulerSolver(new ByEarliestFinishTimeRidesSorter(), new AnyWhichCompletesVehicleSelector());
    }

    public static Solver<SelfDrivingInput, SelfDrivingOutput> byEarliestFinishTimeCloserVehicleWhichCompletes() {
        return new FleetSchedulerSolver(new ByEarliestFinishTimeRidesSorter(), new CloserWhichCompletesVehicleSelector());
    }

}
