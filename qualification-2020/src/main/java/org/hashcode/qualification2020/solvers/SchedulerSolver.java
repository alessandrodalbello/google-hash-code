package org.hashcode.qualification2020.solvers;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.hashcode.Solver;
import org.hashcode.qualification2020.model.Book;
import org.hashcode.qualification2020.model.BooksInput;
import org.hashcode.qualification2020.model.BooksOutput;
import org.hashcode.qualification2020.model.Library;

public class SchedulerSolver implements Solver<BooksInput, BooksOutput> {

    private final LibrarySelector librarySelector;

    public SchedulerSolver(LibrarySelector librarySelector) {
        this.librarySelector = librarySelector;
    }

    @Override
    public BooksOutput solve(BooksInput inputData) {
        Scheduler scheduler = new Scheduler(inputData.getDays());
        Set<Library> remainingLibraries = new HashSet<>(inputData.getLibraries());

        Optional<Library> libraryToSignUp = librarySelector.selectLibrary(remainingLibraries);
        while (scheduler.hasNextDay()) {
            if (libraryToSignUp.isPresent()) {
                Library library = libraryToSignUp.get();
                boolean signedUp = scheduler.signUpLibrary(library);
                if (signedUp) {
                    remainingLibraries.remove(library);
                    libraryToSignUp = librarySelector.selectLibrary(remainingLibraries);
                }
            }
            scheduler.nextDay();
        }

        List<Library> signedUpLibraries = scheduler.getSignedUpLibraries();
        Map<Integer, List<Book>> scannedBooksByLibrary = scheduler.getScannedBooksByLibrary();
        for (Library library : signedUpLibraries) {
            List<Book> scannedLibraryBooks = scannedBooksByLibrary.get(library.getId());
            library.setScannedBooks(scannedLibraryBooks);
        }
        return new BooksOutput(signedUpLibraries);
    }

}
