package org.hashcode.qualification2020.solvers;

import java.util.Set;
import java.util.TreeMap;

import org.hashcode.qualification2020.model.Book;
import org.hashcode.qualification2020.model.Library;

public class MaximiseBooksScoreLibrarySelector implements LibrarySelector {

    @Override
    public Library selectLibrary(Set<Library> libraries, int leftDays) {
        TreeMap<Long, Library> libraryByScore = new TreeMap<>();
        for (Library library : libraries) {
            long score = library.getAvailableBooks().stream()
                    .limit(Math.min(leftDays * (long) library.getMaxScansPerDay(), library.getAvailableBooks().size()))
                    .mapToLong(Book::getScore)
                    .sum();
            libraryByScore.put(score, library);
        }
        return libraryByScore.lastEntry().getValue();
    }

}
