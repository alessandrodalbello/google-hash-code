package org.hashcode.qualification2018.solvers;

import java.util.Optional;
import java.util.Set;

import org.hashcode.qualification2018.model.Ride;
import org.hashcode.qualification2018.model.Vehicle;

interface VehicleSelector {

    Optional<Vehicle> selectVehicle(Ride ride, Set<Vehicle> vehicles, int bonusPoints);

}
