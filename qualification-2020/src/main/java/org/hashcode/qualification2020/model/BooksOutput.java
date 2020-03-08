package org.hashcode.qualification2020.model;

import java.util.List;

import org.hashcode.OutputData;

public class BooksOutput implements OutputData {

    private final List<Library> libraries;

    public BooksOutput(List<Library> libraries) {
        this.libraries = libraries;
    }

    @Override
    public long getSolutionScore() {
        return libraries.stream()
                .flatMap(library -> library.getScannedBooks().stream())
                .mapToLong(Book::getScore).sum();
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    @Override
    public String toString() {
        return BooksOutput.class.getSimpleName() + "{" +
                "libraries=" + libraries +
                "}";
    }

}
