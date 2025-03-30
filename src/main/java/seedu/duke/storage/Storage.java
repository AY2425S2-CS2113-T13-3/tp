package seedu.duke.storage;

import seedu.duke.book.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;

public class Storage {
    private static final String SPLIT_REGEX = "\\|";
    private static final String DIRECTORY_NAME = "data";

    private static final int BOOK_TITLE_INDEX = 0;
    private static final int BOOK_AUTHOR_INDEX = 1;
    private static final int BOOK_STATUS_INDEX = 2;
    private static final int BOOK_DUE_DATE_INDEX = 3;

    private static Storage instance; // Singleton instance
    private final String filePath;   // Instance variable (not static)

    private Storage(String path) {
        this.filePath = path;
    }

    public static Storage getInstance(String path) {
        if (instance == null) {
            instance = new Storage(path);
        }
        return instance;
    }

    public List<Book> loadFileContents() {
        assert filePath != null : "File path must be initialized before loading";

        try {
            List<Book> bookList = new ArrayList<>();

            File file = new File(filePath);
            if (!file.exists()) {
                return bookList;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String details = scanner.nextLine();
                Book book = getBookFromLoad(details);
                bookList.add(book);
            }

            scanner.close();
            return bookList;
        } catch (IOException e) {
            System.out.print("ERROR:" + e.getMessage());
        }

        return new ArrayList<>();
    }

    private static Book getBookFromLoad(String details) {
        String[] specifiers = details.split(SPLIT_REGEX);

        String bookTitle = specifiers[BOOK_TITLE_INDEX].trim();
        String bookAuthor = specifiers[BOOK_AUTHOR_INDEX].trim();
        String bookStatus = specifiers[BOOK_STATUS_INDEX].trim();

        boolean isBorrowed;
        isBorrowed = bookStatus.equals("1");

        LocalDate returnDueDate = null;
        if (specifiers.length > BOOK_DUE_DATE_INDEX) {
            String dueDateStr = specifiers[BOOK_DUE_DATE_INDEX].trim();
            if (!dueDateStr.isEmpty()) {
                returnDueDate = LocalDate.parse(dueDateStr);
            }
        }

        Book book = new Book(bookTitle, bookAuthor, isBorrowed, returnDueDate);
        return book;
    }

    public void writeToFile(List<Book> bookList) {
        assert bookList != null : "Book list cannot be null";

        File directory = new File(DIRECTORY_NAME);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) { // Overwrites file
            for (Book book : bookList) {
                writer.write(book.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.print("ERROR:" + e.getMessage());
        }
    }
}
