package seedu.duke.book;

import seedu.duke.exception.BookNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import seedu.duke.member.Member;
import seedu.duke.member.MemberManager;

/**
 * Manages a collection of books by adding, deleting, listing, searching, and updating their status.
 */
public class BookManager {
    //private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String BORROW = "borrow";
    private static final String RETURN = "return";
  
    private final List<Book> books;

    /**
     * Constructs a new BookManager with the given books.
     *
     * @param books The initial list of books
     */
    public BookManager(List<Book> books) {
        this.books = books != null ? new ArrayList<>(books) : new ArrayList<>();
        // Assert that books is properly initialized
        //assert this.books != null : "Books list should never be null after initialization";
    }

    private boolean isAppropriateGenre(String genre) {
        return (genre.equals("romance")) || (genre.equals("adventure")) || (genre.equals("action") ||
                (genre.equals("horror")) || (genre.equals("mystery")) || (genre.equals("scifi")) ||
                (genre.equals("nonfiction")));
    }

    /**
     * Gets the list of all books.
     *
     * @return The list of books
     */
    public List<Book> getBooks() {
        // Assert that we're returning a non-null list
        //assert books != null : "Book list should never be null";
        return books;
    }

    /**
     * Adds a new book based on the provided details.
     *
     * @param title The title of the book to be added.
     * @param author The author of the book to be added.
     * @param genre The genre of the book to be added.
     *
     * @return A message confirming the book addition or an error message
     */
    public String addNewBookToCatalogue(String title, String author, String genre) {
        assert title != null : "Title cannot be null";
        assert author != null : "Author cannot be null";
        assert genre != null : "Genre cannot be null";

        if (title.isEmpty()) {
            return "Book title cannot be empty!";
        }

        if (author.isEmpty()) {
            return "Book author cannot be empty!";
        }

        if (genre.isEmpty()) {
            return "Book genre cannot be empty!";
        }

        if (!isAppropriateGenre(genre)) {
            return "This Library does not support this Genre!";
        }

        Book newBook = new Book(title, author);
        int oldSize = books.size();
        books.add(newBook);
        newBook.setBookID(bookID);

        // Assert that the book was successfully added
        assert books.size() == oldSize + 1 : "Book size should increase by 1 after adding";
        assert books.contains(newBook) : "New book should be in the collection";

        return "I've added: " + newBook + "\nNow you have " + books.size() + " books in the library.";
    }


    /**
     * Deletes a book from the books list.
     *
     * @param bookIndex Array containing the book index information [index]
     * @return A message indicating whether the deletion was successful or if there was an error
     */

    public String deleteBook(int bookIndex) {
        //assert index != null : "Book Index cannot be null";

        if (bookIndex < 0 || bookIndex >= books.size()) {
            return "There is no such book in the library!";
        }

        Book removedBook = books.get(bookIndex);
        int oldSize = books.size();
        books.remove(bookIndex);

        // Assert that the book was successfully removed
        assert books.size() == oldSize - 1 : "Book size should decrease by 1 after deletion";
        assert !books.contains(removedBook) : "Removed book should not be in the collection";

        return "Book deleted:\n  " + removedBook + "\nNow you have " + books.size() + " books in the library";
    }

    /**
     * Lists all books in the library.
     *
     * @return A formatted string of all books
     */
    public String listBooks() {
        // Assert that books is never null when listing
        //assert books != null : "Book list should never be null when listing";

        if (books.isEmpty()) {
            return "No books in the library yet.";
        } else {
            StringBuilder output = new StringBuilder("Here are the books in your library:\n");
            for (int i = 0; i < books.size(); i++) {
                assert books.get(i) != null : "Book at index " + i + " should not be null";
                output.append(i + 1).append(". ").append(books.get(i)).append("\n");
            }
            output.append("Total books: ").append(books.size());

            // Assert that the output message contains the expected elements
            assert output.toString().contains("Here are the books") : "List output should contain header";
            assert output.toString().contains("Total books: " + books.size()) :
                    "List output should contain total book count";

            return output.toString();
        }
    }

    /**
     * Updates the status of a book in the library based on the provided command.
     *
     * @param command   The operation to perform, either BORROW or RETURN.
     * @param bookIndex The index of the book in the library (0-based).
     * @return A message indicating the result of the operation, which can be:
     * @throws NumberFormatException If the book number provided cannot be parsed as an integer
     */
    public String updateBookStatus(String command, int bookIndex, String borrowerName, MemberManager memberManager) {
        //assert userInput != null : "Input should not be null";

        if (bookIndex < 0 || bookIndex >= books.size()) {
            return "There is no such book in the library!";
        }

        Book book = books.get(bookIndex);
        assert book != null : "Book object should not be null";

        Member borrower = memberManager.getMemberByName(borrowerName);

        switch (command) {
        case BORROW:
            if (book.isBorrowed()) {
                return "This book is already borrowed!";
            }
            book.setStatus(true);
            book.setReturnDueDate(LocalDate.now().plusWeeks(2));
            borrower.borrowBook(book);
            return borrowerName + " has borrowed: " + book.getTitle();
        case RETURN:
            if (!book.isBorrowed()) {
                return "This book is not borrowed!";
            }
            book.setStatus(false);
            book.setReturnDueDate(null);
            borrower.returnBook(book);
            return "Returned: " + book.getTitle();
        default:
            return "Invalid command!";
        }
    }

    /**
     * Lists all the books that have been borrowed in the library.
     *
     * @return A string representation of the borrowed books. If no books have been borrowed,
     */
    public String listBorrowedBooks() {
        if (books.isEmpty()) {
            return "No books have been borrowed yet.";
        } else {
            StringBuilder borrowedBooks = new StringBuilder("Here are the books that have been borrowed:\n");
            for (int i = 0; i < books.size(); i++) {
                Book book = books.get(i);
                assert book != null : "Book at index " + i + " should not be null";
                if (book.isBorrowed()) {
                    borrowedBooks.append(i + 1).append(". ").append(book).append("\n");
                }
            }
            return borrowedBooks.toString();
        }
    }

    public String listOverdueBooks() {
        StringBuilder output = new StringBuilder("List of Overdue Books:\n");
        boolean hasOverdue = false;

        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            boolean isOverdue = book.isOverdue();

            if (isOverdue) {
                output.append(i + 1)
                        .append(". ")
                        .append(book)
                        .append("\n");
                hasOverdue = true;
            }
        }


        return hasOverdue ? output.toString() : "No Overdue Books";
    }

    public String getBookID(int bookIndex) throws BookNotFoundException {
        try {
            Book book = books.get(bookIndex);
            return book.getBookID();
        } catch (IndexOutOfBoundsException e) {
            throw new BookNotFoundException("Book not found!");
        }
    }
}
