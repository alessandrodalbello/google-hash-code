package org.hashcode.qualification2018.solvers;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.hashcode.qualification2018.model.Ride;

class ComplexRidesSorter implements RidesSorter {

    @Override
    public List<Ride> sortByPriority(Collection<Ride> rides) {
        return rides.stream()
                .sorted(Comparator.comparingInt(ride -> ride.getEarliestStartTime() + ride.getDistance()))
                .sorted(Comparator.comparingInt(Ride::getEarliestStartTime)
                        .thenComparingInt(Ride::getLatestFinishTime)
                ).collect(Collectors.toList());
    }

}
