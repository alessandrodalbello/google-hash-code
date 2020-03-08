package org.hashcode.qualification2020.solvers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.hashcode.Solver;
import org.hashcode.qualification2020.model.Book;
import org.hashcode.qualification2020.model.BooksInput;
import org.hashcode.qualification2020.model.BooksOutput;
import org.hashcode.qualification2020.model.Library;

public class BooksSolver implements Solver<BooksInput, BooksOutput> {

    private final LibrarySelector librarySelector;

    public BooksSolver(LibrarySelector librarySelector) {
        this.librarySelector = librarySelector;
    }

    @Override
    public BooksOutput solve(BooksInput inputData) {
        final int maxDays = inputData.getDays();
        final Set<Library> remainingLibraries = new HashSet<>(inputData.getLibraries());
        final List<Library> signedUpLibraries = new ArrayList<>(remainingLibraries.size());
        final Set<Book> scannedBooks = new HashSet<>();
        final Map<Integer, List<Book>> scannedBooksByLibrary = new HashMap<>();

        int currentDay = 0;
        while (currentDay < maxDays) {
            final int leftDays = maxDays - currentDay;
            removeUnsignableLibraries(remainingLibraries, leftDays);

            Library libraryToSignUp = selectLibraryToSignUp(remainingLibraries, scannedBooks, leftDays);
            int simulatedDays = libraryToSignUp != null ? libraryToSignUp.getSignUpDays() : leftDays;
            simulateDays(simulatedDays, signedUpLibraries, scannedBooks, scannedBooksByLibrary);

            if (libraryToSignUp != null) {
                remainingLibraries.remove(libraryToSignUp);
                signedUpLibraries.add(libraryToSignUp);
            }
            currentDay += simulatedDays;
        }

        for (Library library : signedUpLibraries) {
            List<Book> scannedLibraryBooks = scannedBooksByLibrary.get(library.getId());
            library.setScannedBooks(scannedLibraryBooks);
        }
        return new BooksOutput(signedUpLibraries);
    }

    private void removeUnsignableLibraries(Set<Library> remainingLibraries, int leftDays) {
        List<Library> unsignableLibraries = remainingLibraries.stream()
                .filter(library -> library.getSignUpDays() >= leftDays)
                .collect(Collectors.toList());
        unsignableLibraries.forEach(remainingLibraries::remove);
    }

    private Library selectLibraryToSignUp(Set<Library> remainingLibraries, Set<Book> scannedBooks, int leftDays) {
        if (!remainingLibraries.isEmpty()) {
            return librarySelector.selectLibrary(remainingLibraries, scannedBooks, leftDays);
        }
        return null;
    }

    private void simulateDays(int days, List<Library> signedUpLibraries, Set<Book> scannedBooks,
            Map<Integer, List<Book>> scannedBooksByLibrary) {
        for (Library library : signedUpLibraries) {
            int numberOfBooksToScan = library.getMaxScansPerDay() * days;
            int numberOfBooksScanned = 0;
            boolean hasBooks = library.getNumberOfAvailableBooks() > 0;
            while (numberOfBooksScanned < numberOfBooksToScan && hasBooks) {
                Optional<Book> optBookToScan = library.getNextBookToScan();
                if (optBookToScan.isPresent()) {
                    Book bookToScan = optBookToScan.get();
                    if (!scannedBooks.contains(bookToScan)) {
                        scannedBooksByLibrary.computeIfAbsent(library.getId(), libraryId -> new LinkedList<>()).add(bookToScan);
                        scannedBooks.add(bookToScan);
                        numberOfBooksScanned += 1;
                    }
                } else {
                    hasBooks = false;
                }
            }
        }
    }

}
