package org.hashcode.qualification2020.solvers;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.hashcode.qualification2020.model.Book;
import org.hashcode.qualification2020.model.Library;

public class MaximiseBooksValueRatioLibrarySelector implements LibrarySelector {

    @Override
    public Library selectLibrary(Set<Library> libraries, Set<Book> scannedBooks, int leftDays) {
        final Map<Integer, Double> libraryBooksValuesRatio = new HashMap<>();
        for (Library library : libraries) {
            double booksValue = library.getAvailableBooks().stream()
                    .mapToLong(Book::getScore)
                    .sum() / (double) library.getSignUpDays();
            libraryBooksValuesRatio.put(library.getId(), booksValue);
        }
        return libraries.stream()
                .max(Comparator.comparingDouble(library -> libraryBooksValuesRatio.get(library.getId())))
                .orElse(null);
    }

}
