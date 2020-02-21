package org.hashcode.qualification2020;

import org.hashcode.HashCodeRunner;
import org.hashcode.Solver;
import org.hashcode.qualification2020.io.BooksInputReader;
import org.hashcode.qualification2020.io.BooksOutputWriter;
import org.hashcode.qualification2020.model.BooksInput;
import org.hashcode.qualification2020.model.BooksOutput;
import org.hashcode.qualification2020.solvers.LibrarySelector;
import org.hashcode.qualification2020.solvers.MaximiseBooksScoreLibrarySelector;
import org.hashcode.qualification2020.solvers.MinimumSignUpTimeLibrarySelector;
import org.hashcode.qualification2020.solvers.SchedulerSolver;

public class HashCodeQualification extends HashCodeRunner<BooksInput, BooksOutput> {

    protected HashCodeQualification(char inputPrefix, Solver<BooksInput, BooksOutput> solver) {
        super(inputPrefix, BooksInputReader.getInstance(), solver, BooksOutputWriter.getInstance());
    }

    public static void main(String[] arguments) {
        if (arguments.length == 0) {
            throw new IllegalArgumentException("No arguments provided. Expected at least one argument between 'a', 'b', 'c', 'd' or 'e'.");
        }

        for (String argument : arguments) {
            if (argument.length() != 1 || argument.compareToIgnoreCase("a") < 0 || argument.compareToIgnoreCase("f") > 0) {
                throw new IllegalArgumentException(String.format("Invalid argument '%s'. Only 'a', 'b', 'c', 'd', 'e' or 'f' are expected.", argument));
            }

            char inputPrefix = argument.charAt(0);
            LibrarySelector librarySelector = new MaximiseBooksScoreLibrarySelector();
            Solver<BooksInput, BooksOutput> solver = new SchedulerSolver(librarySelector);
            HashCodeQualification hashCode = new HashCodeQualification(inputPrefix, solver);
            hashCode.run();
        }
    }

}
