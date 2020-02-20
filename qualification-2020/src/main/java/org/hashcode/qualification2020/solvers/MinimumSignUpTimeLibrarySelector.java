package org.hashcode.qualification2020.solvers;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

import org.hashcode.qualification2020.model.Library;

public class MinimumSignUpTimeLibrarySelector implements LibrarySelector {

    @Override
    public Optional<Library> selectLibrary(Set<Library> libraries) {
        return libraries.stream()
                .min(Comparator.comparingInt(Library::getSignUpDays));
    }

}
