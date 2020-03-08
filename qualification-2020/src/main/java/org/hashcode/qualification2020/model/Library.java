package org.hashcode.qualification2020.model;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Library {

    private final int id;
    private final int signUpDays;
    private final int maxScansPerDay;
    private final List<Book> availableBooks;

    private long availableBooksScore;
    private List<Book> scannedBooks;

    public Library(int id, int signUpDays, int maxScansPerDay, Set<Book> availableBooks) {
        this.id = id;
        this.signUpDays = signUpDays;
        this.maxScansPerDay = maxScansPerDay;
        this.availableBooks = availableBooks.stream()
                .sorted(Comparator.comparingInt(Book::getScore).reversed())
                .collect(Collectors.toCollection(LinkedList::new));
        availableBooksScore = availableBooks.stream()
                .mapToLong(Book::getScore)
                .sum();
    }

    public int getId() {
        return id;
    }

    public int getSignUpDays() {
        return signUpDays;
    }

    public int getMaxScansPerDay() {
        return maxScansPerDay;
    }

    public int getNumberOfAvailableBooks() {
        return availableBooks.size();
    }

    public List<Book> getAvailableBooks() {
        return availableBooks;
    }

    public Optional<Book> getNextBookToScan() {
        if (!availableBooks.isEmpty()) {
            Book bookToScan = availableBooks.remove(0);
            availableBooksScore -= bookToScan.getScore();
            return Optional.of(bookToScan);
        }
        return Optional.empty();
    }

    public long getAvailableBooksScore() {
        return availableBooksScore;
    }

    public int getNumberOfScannedBooks() {
        return Objects.nonNull(scannedBooks) ? scannedBooks.size() : 0;
    }

    public List<Book> getScannedBooks() {
        return Objects.nonNull(scannedBooks) ? scannedBooks : List.of();
    }

    public void setScannedBooks(List<Book> scannedBooks) {
        this.scannedBooks = scannedBooks;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Library other = (Library) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return Library.class.getSimpleName() + "{" +
                "id=" + id +
                ", signUpDays=" + signUpDays +
                ", maxScansPerDay=" + maxScansPerDay +
                ", availableBooks=" + availableBooks +
                ", scannedBooks=" + scannedBooks +
                "}";
    }

}
