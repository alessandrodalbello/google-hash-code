package org.hashcode.qualification2020.io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.io.CharSink;
import com.google.common.io.Files;
import org.hashcode.io.OutputWriter;
import org.hashcode.qualification2020.model.BooksOutput;
import org.hashcode.qualification2020.model.Library;

public class BooksOutputWriter extends OutputWriter<BooksOutput> {

    private static BooksOutputWriter instance = null;

    private BooksOutputWriter() {
        // not visible
    }

    public static BooksOutputWriter getInstance() {
        if (instance == null) {
            instance = new BooksOutputWriter();
        }
        return instance;
    }

    @Override
    protected void writeFile(BooksOutput outputData, File outputFile) throws IOException {
        List<Library> libraries = outputData.getLibraries().stream()
                .filter(library -> library.getNumberOfScannedBooks() > 0)
                .collect(Collectors.toList());
        List<String> submission = new ArrayList<>(libraries.size() * 2 + 1);
        String numberOfLibraries = String.valueOf(libraries.size());
        submission.add(numberOfLibraries);
        for (Library library : libraries) {
            String libraryDescription = library.getId() + " " + library.getNumberOfScannedBooks();
            String scannedBooksIds = library.getScannedBooks().stream()
                    .map(book -> String.valueOf(book.getId()))
                    .collect(Collectors.joining(" "));

            submission.add(libraryDescription);
            submission.add(scannedBooksIds);
        }

        CharSink charSink = Files.asCharSink(outputFile, StandardCharsets.UTF_8);
        charSink.writeLines(submission);
    }

}
