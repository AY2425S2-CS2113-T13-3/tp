package seedu.duke.Shelving.Shelves;

import seedu.duke.book.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single shelf in a library section, capable of storing up to {@value #MAX_BOOKS_ON_SHELF} books.
 * Each shelf is identified by a genre and index (e.g., "FIC-1" for Fiction Shelf 1) and manages its own collection
 * of {@link Book} objects.
 */
public class Shelf {
    private List<Book> shelfBooks = new ArrayList<>();
    private final int shelfIndex;
    private final String shelfGenre;

    private int booksCurrentlyOnShelf = 0;
    private static final int MAX_BOOKS_ON_SHELF = 100;

    private boolean isShelfFull;

    /**
     * Marks this shelf as full (when {@code booksCurrentlyOnShelf == MAX_BOOKS_ON_SHELF}).
     */
    private void setShelfAsFull() {
        isShelfFull = true;
    }

    /**
     * Generates a unique identifier for a book on this shelf.
     *
     * @param bookIndex The index of the book on the shelf.
     * @return A formatted identifier (e.g., "FIC-1-3" for the 4th book on Fiction Shelf 1).
     */
    private String getShelfIdentifier(int bookIndex) {
        return shelfGenre + "-" + shelfIndex + "-" + bookIndex;
    }

    /**
     * Constructs a new empty shelf with the given index and genre.
     *
     * @param shelfIndex The positional index of this shelf.
     * @param shelfGenre The genre/category identifier (e.g., "FIC").
     */
    public Shelf(int shelfIndex, String shelfGenre) {
        this.shelfIndex = shelfIndex;
        this.shelfGenre = shelfGenre;
    }

    /**
     * Finds the next suitable index for adding a book, either by locating a "duMmY" placeholder
     * or using the current book count.
     *
     * @return The index for the new book.
     */
    private int getSuitableIndex() {
        int index = 0;
        for (Book book : shelfBooks) {
            if (book.getTitle().equals("duMmY")) {
                return index;
            }
            index++;
        }
        return booksCurrentlyOnShelf;
    }

    /**
     * Checks if this shelf is full.
     *
     * @return {@code true} if the shelf has reached {@value #MAX_BOOKS_ON_SHELF} books, {@code false} otherwise.
     */
    public boolean isFull() {
        return isShelfFull;
    }

    /**
     * Gets the current number of books on this shelf.
     *
     * @return The count of books (0–{@value #MAX_BOOKS_ON_SHELF}).
     */
    public int getBooksCurrentlyOnShelf() {
        return booksCurrentlyOnShelf;
    }

    /**
     * Gets the list of books on this shelf.
     *
     * @return An unmodifiable {@link List} of {@link Book} objects.
     */
    public List<Book> getShelfBooks() {
        return shelfBooks;
    }

    public void listShelf() {
        // Implement this Wayne
    }

    public String addBookToShelf(String bookDetails) {
        if (isShelfFull) {
            return "The shelf is full!";
        }

        assert bookDetails != null : "Book details cannot be null";

        String[] parts = bookDetails.split(" / ", 2);

        if (parts.length < 2) {
            return "Invalid book format! It should be 'TITLE / AUTHOR'.";
        }

        String title = parts[0].trim();
        String author = parts[1].trim();

        if (title.isEmpty()) {
            return "Book title cannot be empty!";
        }

        if (author.isEmpty()) {
            return "Book author cannot be empty!";
        }

        Book newBook = new Book(title, author);
        int bookIndex = getSuitableIndex();
        String identifier = getShelfIdentifier(bookIndex);
        newBook.setBookID(identifier);
        this.shelfBooks.add(newBook);
        booksCurrentlyOnShelf += 1;

        if (booksCurrentlyOnShelf == MAX_BOOKS_ON_SHELF) {
            setShelfAsFull();
        }

        return "Added: " + title + " by: " + author + "\nNow you have " + booksCurrentlyOnShelf + " books in the Shelf.";
        // TODO: make it such the it also states which shelf it has been added on (Wayne)
    }

    public String deleteBookFromShelf(int slotNum) {
        return "hello fix this deanson";//shelfBookManager.deleteBook(slotNum);
    }

}
