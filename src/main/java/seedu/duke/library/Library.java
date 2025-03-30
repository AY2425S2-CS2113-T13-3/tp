package seedu.duke.library;

import seedu.duke.shelving.ShelvesManager;
import seedu.duke.book.Book;
import seedu.duke.book.BookManager;

import seedu.duke.exception.BookNotFoundException;

import seedu.duke.member.MemberManager;


import java.util.List;

public class Library {
    private static Library theOneLibrary;

    private final BookManager catalogueManager;
    private final ShelvesManager shelvesManager;

    private Library(List<Book> allBooks) {
        catalogueManager = new BookManager(allBooks);
        shelvesManager = new ShelvesManager();
    }

    public static Library getTheOneLibrary(List<Book> allBooks) {
        if (theOneLibrary == null) {
            theOneLibrary = new Library(allBooks);
        }
        return theOneLibrary;
    }

    public String listBooks() {
        return catalogueManager.listBooks();
    }

    //@@author WayneCh0y
    public String listShelf(String shelfGenre, int shelfIndex) {
        return shelvesManager.listShelf(shelfGenre, shelfIndex);
    }

    public String listBorrowedBooks() {
        return catalogueManager.listBorrowedBooks();
    }

    //@@author Deanson-Choo
    public String addNewBookToCatalogue(String title, String author, String genre) {
        return catalogueManager.addNewBookToCatalogue(title, author, genre);
    }

    //@@author WayneCh0y
    public String addNewBookToShelf(String title, String author, String genre) {
        return shelvesManager.addBook(title, author, genre);
    }

    //@@author Deanson Choo

    /**
     * Deletes a book from the catalogue and the corresponding shelf based on its index in the catalogue.
     * <p>
     * This method first retrieves the book ID using the given index, then removes it from both the
     * catalogue and the shelves. The book in the shelf is replaced with a dummy book.
     *
     * @param bookIndex The index of the book to delete in the catalogue.
     * @return A confirmation message if deletion is successful, or an error message if the book is not found.
     */
    public String deleteBook(int bookIndex){
        try {
            String bookID = catalogueManager.getBookID(bookIndex); //throw BookNotFound
            String response1 = catalogueManager.deleteBook(bookIndex);
            assert bookID != null; //that means it was fetchable
            shelvesManager.deleteBook(bookID);
            return response1;
        } catch (BookNotFoundException e) {
            return e.getMessage();
        }
    }

    public String updateBookStatus(String userInput, int bookIndex, String borrowerName, MemberManager memberManager) {
        //update Book in catelogue
        //update book in shelf
        return null;
    }

    //@@author Deanson Choo
    /**
     * Returns a list of all books currently in the catalogue.
     *
     * @return A list of {@link Book} objects in the catalogue.
     */
    public List<Book> getBooks() {
        return catalogueManager.getBooks();
    }
}
