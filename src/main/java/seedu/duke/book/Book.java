package seedu.duke.book;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Book {
    private final String title;
    private final String author;
    private boolean isBorrowed;
    private LocalDate returnDueDate;
    private String bookID; //IDENTIFIER-ShelfNum-Index

    public Book(String title, String author, boolean isBorrowed, LocalDate returnDueDate) {
        assert title != null && !title.trim().isEmpty() : "Title cannot be empty";
        assert author != null && !author.trim().isEmpty() : "Author cannot be empty";

        this.title = title;
        this.author = author;
        this.isBorrowed = isBorrowed;
        this.returnDueDate = returnDueDate;
        bookID = "NIL";
    }

    public Book(String title, String author, boolean isBorrowed, LocalDate returnDueDate, String bookID) {
        assert title != null && !title.trim().isEmpty() : "Title cannot be empty";
        assert author != null && !author.trim().isEmpty() : "Author cannot be empty";

        this.title = title;
        this.author = author;
        this.isBorrowed = isBorrowed;
        this.returnDueDate = returnDueDate;
        this.bookID = bookID;
    }

    public Book(String title, String author) {
        this(title, author, false, null);
    }

    public void setStatus(boolean isBorrowed) {
        this.isBorrowed = isBorrowed;
    }

    public void setReturnDueDate(LocalDate date) {
        this.returnDueDate = date;
    }

    /**
     * Returns the string representation of the book.
     *
     * @return Formatted string with book details.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String status = isBorrowed ? "[X]" : "[ ]";
        String dueDateStr = (returnDueDate != null) ? "(Due: " + returnDueDate.format(formatter) + ")" : "";
        return status + " " + title + " (by " + author + ") " + dueDateStr;
    }

    /**
     * Edit Accordingly depending on how Wayne wants to format this
     */
    public String toFileFormat() {
        return title + " | " + author + " | " + (isBorrowed ? 1 : 0) +
                (returnDueDate != null ? " | " + returnDueDate : "") + " | " + bookID;
    }

    public boolean isOverdue() {
        return isBorrowed() && getReturnDueDate() != null && getReturnDueDate().isBefore(LocalDate.now());
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public LocalDate getReturnDueDate() {
        return returnDueDate;
    }

    public String getBookID() {
        return bookID;
    }

    // Setters
    public void setBookID(String bookID) {
        this.bookID = bookID;
    }
}
