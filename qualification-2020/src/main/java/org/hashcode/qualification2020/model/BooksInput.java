package org.hashcode.qualification2020.model;

import java.util.List;

import org.hashcode.InputData;

public class BooksInput implements InputData {

    private final int days;
    private final List<Book> allBooks;
    private final List<Library> libraries;

    public BooksInput(int days, List<Book> allBooks, List<Library> libraries) {
        this.days = days;
        this.allBooks = allBooks;
        this.libraries = libraries;
    }

    public int getDays() {
        return days;
    }

    public List<Book> getAllBooks() {
        return allBooks;
    }

    public int getNumberOfBooks() {
        return allBooks.size();
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    @Override
    public String toString() {
        return BooksInput.class.getSimpleName() + "{" +
                "days=" + days +
                ", allBooks=" + allBooks +
                ", libraries=" + libraries +
                "}";
    }

}
