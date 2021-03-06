package org.hashcode.qualification2020.solvers;

import java.util.Comparator;
import java.util.Set;

import org.hashcode.qualification2020.model.Book;
import org.hashcode.qualification2020.model.Library;

public class MinimumSignUpTimeLibrarySelector implements LibrarySelector {

    @Override
    public Library selectLibrary(Set<Library> libraries, Set<Book> scannedBooks, int leftDays) {
        return libraries.stream()
                .min(Comparator.comparingInt(Library::getSignUpDays))
                .orElse(null);
    }

}
