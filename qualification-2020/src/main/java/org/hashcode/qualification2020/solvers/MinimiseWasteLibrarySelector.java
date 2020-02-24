package org.hashcode.qualification2020.solvers;

import java.util.Comparator;
import java.util.Set;

import org.hashcode.qualification2020.model.Library;

public class MinimiseWasteLibrarySelector implements LibrarySelector {

    @Override
    public Library selectLibrary(Set<Library> libraries, int leftDays) {
        return libraries.stream()
                .min(Comparator.comparingInt(library -> Math.abs(leftDays - library.getSignUpDays() -
                        library.getNumberOfAvailableBooks() / library.getMaxScansPerDay())))
                .orElse(null);
    }

}
