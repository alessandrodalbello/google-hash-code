package org.hashcode.qualification2020.solvers;

import java.util.Optional;
import java.util.Set;

import org.hashcode.qualification2020.model.Library;

public interface LibrarySelector {

    Optional<Library> selectLibrary(Set<Library> libraries);

}
