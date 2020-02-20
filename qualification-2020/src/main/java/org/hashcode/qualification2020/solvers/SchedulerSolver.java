package org.hashcode.qualification2020.solvers;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

        Library libraryToSignUp = null;
        while (scheduler.hasNextDay()) {
            if (scheduler.getDaysToSignUp() <= 1) {
                libraryToSignUp = null;
                while (!remainingLibraries.isEmpty() && Objects.isNull(libraryToSignUp)) {
                    libraryToSignUp = librarySelector.selectLibrary(remainingLibraries, scheduler.getCurrentDay(), inputData.getDays());
                    if (Objects.nonNull(libraryToSignUp) &&
                            scheduler.getCurrentDay() + libraryToSignUp.getSignUpDays() >= inputData.getDays()) {
                        remainingLibraries.remove(libraryToSignUp);
                        libraryToSignUp = null;
                    }
                }
            }
            if (Objects.nonNull(libraryToSignUp)) {
                boolean signedUp = scheduler.signUpLibrary(libraryToSignUp);
                if (signedUp) {
                    remainingLibraries.remove(libraryToSignUp);
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
