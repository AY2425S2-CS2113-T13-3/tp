package seedu.duke.shelving.shelves;

import seedu.duke.book.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a single shelf in a library section, capable of storing up to {@value #MAX_BOOKS_ON_SHELF} books.
 * Each shelf is identified by a genre and index (e.g., "FIC-1" for Fiction Shelf 1) and manages its own collection
 * of {@link Book} objects.
 */
public class Shelf {

    private static final int MAX_BOOKS_ON_SHELF = 100;
    private final List<Book> shelfBooks = new ArrayList<>();

    private final int shelfIndex;
    private final String shelfGenre;

    private int booksCurrentlyOnShelf = 0;
    private boolean isShelfFull;

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
     * Marks this shelf as full (when {@code booksCurrentlyOnShelf == MAX_BOOKS_ON_SHELF}).
     */
    private void setShelfAsFull() {
        isShelfFull = true;
    }

    //@@author WayneCh0y
    /**
     * Generates a unique identifier for a book on this shelf.
     *
     * @param bookIndex The index of the book on the shelf.
     * @return A formatted identifier (e.g., "FIC-1-3" for the 4th book on Fiction Shelf 1).
     */
    private String getShelfIdentifier(int bookIndex) {
        return shelfGenre + "-" + shelfIndex + "-" + bookIndex;
    }


    //@@author WayneCh0y
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

    public String listShelf() {
        if (shelfBooks.isEmpty()) {
            return "No books on shelf";
        }
        return shelfBooks.stream()
                .map(Book::toString)
                .collect(Collectors.joining("\n"));
    }

    //@@author WayneCh0y
    public String addBookToShelf(String title, String author) {
        if (isShelfFull) {
            return "The shelf is full!";
        }

        //assert bookDetails != null : "Book details cannot be null";

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

        return "Added: " + title + " by: " + author + "\nNow you have " + booksCurrentlyOnShelf
                + " books on the Shelf: " + getShelfIdentifier(bookIndex);
    }

    //@@author Deanson Choo
    /**
     * Deletes a book from the shelf at the specified slot number.
     * <p>
     * This method replaces the book at the given slot with a placeholder book titled "duMmy"
     * and decreases the count of books currently on the shelf.
     */
    public void deleteBookFromShelf(int slotNum) {
        Book temp = new Book("duMmY", "duMmY");
        assert slotNum >= 0 && slotNum < booksCurrentlyOnShelf: "Invalid bookIndex!";
        shelfBooks.set(slotNum, temp);
        booksCurrentlyOnShelf -= 1;
    }

    public String getBookId() {
        int bookIndex = getSuitableIndex();
        return getShelfIdentifier(bookIndex);
    }

}
