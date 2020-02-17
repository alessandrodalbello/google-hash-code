package org.hashcode.qualification2018.solvers;

import org.hashcode.Solver;
import org.hashcode.qualification2018.model.SelfDrivingInput;
import org.hashcode.qualification2018.model.SelfDrivingOutput;

public class FleetSchedulerSolverFactory {

    /*
     * By latest finish time
     */
    public static Solver<SelfDrivingInput, SelfDrivingOutput> byLatestFinishTimeAnyVehicle() {
        return new FleetSchedulerSolver(new ByLatestFinishTimeRidesSorter(), new AnyVehicleSelector());
    }

    public static Solver<SelfDrivingInput, SelfDrivingOutput> byLatestFinishTimeCloserVehicle() {
        return new FleetSchedulerSolver(new ByLatestFinishTimeRidesSorter(), new CloserVehicleSelector());
    }

    public static Solver<SelfDrivingInput, SelfDrivingOutput> byLatestFinishTimeMinimumWaitingVehicle() {
        return new FleetSchedulerSolver(new ByLatestFinishTimeRidesSorter(), new MinimumWaitingVehicleSelector());
    }

    public static Solver<SelfDrivingInput, SelfDrivingOutput> byLatestFinishTimeMaximiseBonusVehicle() {
        return new FleetSchedulerSolver(new ByLatestFinishTimeRidesSorter(), new MaximiseBonusVehicleSelector());
    }

    /*
     * By earliest finish time
     */
    public static Solver<SelfDrivingInput, SelfDrivingOutput> byEarliestFinishTimeAnyVehicle() {
        return new FleetSchedulerSolver(new ByEarliestFinishTimeRidesSorter(), new AnyVehicleSelector());
    }

    public static Solver<SelfDrivingInput, SelfDrivingOutput> byEarliestFinishTimeCloserVehicle() {
        return new FleetSchedulerSolver(new ByEarliestFinishTimeRidesSorter(), new CloserVehicleSelector());
    }

    public static Solver<SelfDrivingInput, SelfDrivingOutput> byEarliestFinishTimeMinimumWaitingVehicle() {
        return new FleetSchedulerSolver(new ByEarliestFinishTimeRidesSorter(), new MinimumWaitingVehicleSelector());
    }

    public static Solver<SelfDrivingInput, SelfDrivingOutput> byEarliestFinishTimeMaximiseBonusVehicle() {
        return new FleetSchedulerSolver(new ByEarliestFinishTimeRidesSorter(), new MaximiseBonusVehicleSelector());
    }

    /*
     * By distance
     */
    public static Solver<SelfDrivingInput, SelfDrivingOutput> byDistanceAnyVehicle() {
        return new FleetSchedulerSolver(new ByDistanceRidesSorter(), new AnyVehicleSelector());
    }

    public static Solver<SelfDrivingInput, SelfDrivingOutput> byDistanceCloserVehicle() {
        return new FleetSchedulerSolver(new ByDistanceRidesSorter(), new CloserVehicleSelector());
    }

    public static Solver<SelfDrivingInput, SelfDrivingOutput> byDistanceMinimumWaitingVehicle() {
        return new FleetSchedulerSolver(new ByDistanceRidesSorter(), new MinimumWaitingVehicleSelector());
    }

    public static Solver<SelfDrivingInput, SelfDrivingOutput> byDistanceMaximiseBonusVehicle() {
        return new FleetSchedulerSolver(new ByDistanceRidesSorter(), new MaximiseBonusVehicleSelector());
    }

}
