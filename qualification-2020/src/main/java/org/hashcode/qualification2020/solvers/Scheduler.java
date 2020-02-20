package org.hashcode.qualification2020.solvers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.hashcode.qualification2020.model.Book;
import org.hashcode.qualification2020.model.Library;

public class Scheduler {

    private final int maxDays;
    private final List<Library> signedUpLibraries;
    private final Set<Book> scannedBooks;
    private final Map<Integer, List<Book>> scannedBooksByLibrary;

    private int currentDay;
    private int daysToSignUp;
    private Library signingUpLibrary;

    public Scheduler(int maxDays) {
        this.maxDays = maxDays;
        signedUpLibraries = new ArrayList<>();
        scannedBooks = new HashSet<>();
        scannedBooksByLibrary = new HashMap<>();

        currentDay = 0;
        daysToSignUp = 0;
        signingUpLibrary = null;
    }

    public List<Library> getSignedUpLibraries() {
        return signedUpLibraries;
    }

    public Set<Book> getScannedBooks() {
        return scannedBooks;
    }

    public Map<Integer, List<Book>> getScannedBooksByLibrary() {
        return scannedBooksByLibrary;
    }

    public boolean signUpLibrary(Library library) {
        if (daysToSignUp == 0 && (currentDay + library.getSignUpDays()) < maxDays) {
            daysToSignUp = library.getSignUpDays();
            signingUpLibrary = library;
            return true;
        }
        return false;
    }

    public int getDaysToSignUp() {
        return daysToSignUp;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public boolean hasNextDay() {
        return currentDay < maxDays;
    }

    public void nextDay() {
        for (Library library : signedUpLibraries) {
            int numberOfBooksToScan = library.getMaxScansPerDay();
            int numberOfBooksScanned = 0;
            boolean hasBooks = library.getNumberOfAvailableBooks() > 0;
            while (numberOfBooksScanned < numberOfBooksToScan && hasBooks) {
                Optional<Book> optBookToScan = library.getNextBookToScan();
                if (optBookToScan.isPresent()) {
                    Book bookToScan = optBookToScan.get();
                    if (!scannedBooks.contains(bookToScan)) {
                        scannedBooksByLibrary.computeIfAbsent(library.getId(), libraryId -> new ArrayList<>()).add(bookToScan);
                        scannedBooks.add(bookToScan);
                        numberOfBooksScanned += 1;
                    }
                } else {
                    hasBooks = false;
                }
            }
        }
        daysToSignUp -= 1;
        if (daysToSignUp == 0) {
            signedUpLibraries.add(signingUpLibrary);
            signingUpLibrary = null;
        }
        currentDay += 1;
    }

}
