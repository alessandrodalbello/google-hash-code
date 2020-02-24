package org.hashcode.qualification2020.solvers;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.hashcode.qualification2020.model.Book;
import org.hashcode.qualification2020.model.Library;

public class MaximiseAvailableBooksValueRatioLibrarySelector implements LibrarySelector {

    @Override
    public Library selectLibrary(Set<Library> libraries, Set<Book> scannedBooks, int leftDays) {
        final Map<Integer, Long> libraryBooksValuesRatio = new HashMap<>();
        for (Library library : libraries) {
            long booksValue = library.getAvailableBooks().stream()
                    .filter(book -> !scannedBooks.contains(book))
                    .mapToLong(Book::getScore)
                    .sum() / library.getSignUpDays();
            libraryBooksValuesRatio.put(library.getId(), booksValue);
        }
        return libraries.stream()
                .max(Comparator.comparingLong(library -> libraryBooksValuesRatio.get(library.getId())))
                .orElse(null);
    }

}
