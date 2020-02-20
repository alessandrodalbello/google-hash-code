package org.hashcode.qualification2020.solvers;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.hashcode.qualification2020.model.Book;
import org.hashcode.qualification2020.model.Library;

public class MaximiseBooksScoreLibrarySelector implements LibrarySelector {

    @Override
    public Library selectLibrary(Set<Library> libraries, int currentDay, int maxDays) {
        return libraries.stream()
                .max(Comparator.comparingInt(library -> library.getAvailableBooks().size() / library.getMaxScansPerDay()))
                .orElse(null);
    }

}
