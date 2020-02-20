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

    private List<Book> scannedBooks;

    public Library(int id, int signUpDays, int maxScansPerDay, Set<Book> availableBooks) {
        this.id = id;
        this.signUpDays = signUpDays;
        this.maxScansPerDay = maxScansPerDay;
        this.availableBooks = availableBooks.stream()
                .sorted(Comparator.comparingInt(Book::getScore).reversed())
                .collect(Collectors.toCollection(LinkedList::new));
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
            return Optional.of(availableBooks.remove(0));
        } else {
            return Optional.empty();
        }
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
