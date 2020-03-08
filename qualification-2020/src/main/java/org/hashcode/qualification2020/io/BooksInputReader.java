package org.hashcode.qualification2020.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.google.common.io.CharStreams;
import org.hashcode.io.InputReader;
import org.hashcode.qualification2020.model.Book;
import org.hashcode.qualification2020.model.BooksInput;
import org.hashcode.qualification2020.model.Library;

public class BooksInputReader extends InputReader<BooksInput> {

    private static String[] inputFilenames = new String[] {
            "a_example.txt",
            "b_read_on.txt",
            "c_incunabula.txt",
            "d_tough_choices.txt",
            "e_so_many_books.txt",
            "f_libraries_of_the_world.txt"
    };

    private static BooksInputReader instance = null;

    private BooksInputReader() {
        super(inputFilenames);
    }

    public static BooksInputReader getInstance() {
        if (instance == null) {
            instance = new BooksInputReader();
        }
        return instance;
    }

    @Override
    public BooksInput readFile(Readable readable) throws IOException {
        List<String> lines = CharStreams.readLines(readable);

        String[] firstLineTokens = lines.get(0).trim().split("\\s");
        int numberOfBooks = Integer.parseInt(firstLineTokens[0]);
        int numberOfLibraries = Integer.parseInt(firstLineTokens[1]);
        int days = Integer.parseInt(firstLineTokens[2]);

        String[] secondLineTokens = lines.get(1).trim().split("\\s");
        List<Book> allBooks = IntStream.range(0, numberOfBooks)
                .mapToObj(bookId -> new Book(bookId, Integer.parseInt(secondLineTokens[bookId])))
                .collect(Collectors.toUnmodifiableList());

        List<Library> libraries = new ArrayList<>(numberOfLibraries);
        for (int libraryId = 0; libraryId < numberOfLibraries; libraryId++) {
            String[] firstLineLibraryTokens = lines.get(libraryId * 2 + 2).trim().split("\\s");
            int signUpDays = Integer.parseInt(firstLineLibraryTokens[1]);
            int maxScansPerDay = Integer.parseInt(firstLineLibraryTokens[2]);
            String[] secondLineLibraryTokens = lines.get(libraryId * 2 + 3).trim().split("\\s");
            Set<Book> availableBooks = Arrays.stream(secondLineLibraryTokens)
                    .mapToInt(Integer::parseInt)
                    .mapToObj(allBooks::get)
                    .collect(Collectors.toUnmodifiableSet());

            Library library = new Library(libraryId, signUpDays, maxScansPerDay, availableBooks);
            libraries.add(library);
        }
        return new BooksInput(days, allBooks, libraries);
    }

}
