package org.hashcode.qualification2020.solvers;

import java.util.List;

import org.hashcode.Solver;
import org.hashcode.qualification2020.model.BooksInput;
import org.hashcode.qualification2020.model.BooksOutput;
import org.hashcode.qualification2020.model.Library;

public class DummySolver implements Solver<BooksInput, BooksOutput> {

    @Override
    public BooksOutput solve(BooksInput inputData) {
        Library library = inputData.getLibraries().get(0);
        library.scanBook(inputData.getAllBooks().get(0));
        return new BooksOutput(List.of(library));
    }

}
