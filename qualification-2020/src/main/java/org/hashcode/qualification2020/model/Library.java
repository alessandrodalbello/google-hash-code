package org.hashcode.qualification2020.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Library {

    private final int id;
    private final int signUpDays;
    private final int maxScansPerDay;
    private final Set<Book> availableBooks;
    private final List<Book> scannedBooks;

    public Library(int id, int signUpDays, int maxScansPerDay, Set<Book> availableBooks) {
        this.id = id;
        this.signUpDays = signUpDays;
        this.maxScansPerDay = maxScansPerDay;
        this.availableBooks = availableBooks;
        scannedBooks = new ArrayList<>(availableBooks.size());
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

    public Set<Book> getAvailableBooks() {
        return availableBooks;
    }

    public void scanBook(Book book) {
        scannedBooks.add(book);
    }

    public int getNumberOfScannedBooks() {
        return scannedBooks.size();
    }

    public List<Book> getScannedBooks() {
        return scannedBooks;
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
