package org.hashcode.qualification2018.solvers;

import java.util.Collection;
import java.util.List;

import org.hashcode.qualification2018.model.Ride;

interface RidesSorter {

    List<Ride> sortByPriority(Collection<Ride> rides);

}
