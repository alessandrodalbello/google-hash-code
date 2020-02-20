package org.hashcode.qualification2020.solvers;

import java.util.Set;

import org.hashcode.qualification2020.model.Library;

public interface LibrarySelector {

    Library selectLibrary(Set<Library> libraries);

}
