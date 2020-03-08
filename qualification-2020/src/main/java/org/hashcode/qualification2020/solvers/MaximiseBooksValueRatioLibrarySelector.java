package org.hashcode.qualification2020.solvers;

import java.util.Comparator;
import java.util.Set;

import org.hashcode.qualification2020.model.Book;
import org.hashcode.qualification2020.model.Library;

public class MaximiseBooksValueRatioLibrarySelector implements LibrarySelector {

    @Override
    public Library selectLibrary(Set<Library> libraries, Set<Book> scannedBooks, int leftDays) {
        return libraries.stream()
                .max(Comparator.comparingDouble(library -> library.getAvailableBooksScore() / (double) library.getSignUpDays()))
                .orElse(null);
    }

}
