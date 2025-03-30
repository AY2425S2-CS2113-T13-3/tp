package seedu.duke.shelf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import seedu.duke.shelving.shelves.Shelf;
import seedu.duke.shelving.shelves.Shelves;
import seedu.duke.book.Book;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@@author Deanson Choo
public class ShelvesTest {

    private Shelves shelves;

    @BeforeEach
    public void setup() throws NoSuchFieldException, IllegalAccessException {
        shelves = new Shelves("R");

        Shelf shelf0 = new Shelf(0, "R");
        List<Book> books = new ArrayList<>();
        books.add(new Book("Book 1", "Author A", false, LocalDate.of(2025, 4, 1)));
        books.add(new Book("Book 2", "Author B", false, LocalDate.of(2025, 5, 1)));

        Field shelfField = Shelf.class.getDeclaredField("shelfBooks");
        shelfField.setAccessible(true);
        Field booksNumField = Shelf.class.getDeclaredField("booksCurrentlyOnShelf");
        booksNumField.setAccessible(true);
        Field shelvesField = Shelves.class.getDeclaredField("shelves");
        shelvesField.setAccessible(true);

        shelfField.set(shelf0, books);
        booksNumField.set(shelf0, 2);

        Shelf[] shelvesArray = (Shelf[]) shelvesField.get(shelves);
        shelvesArray[0] = shelf0;
    }

    @Test
    public void testDeleteBookFromSection() throws NoSuchFieldException, IllegalAccessException {
        shelves.deleteBookFromSection(0, 1); // Delete "Book 2"
        Field shelvesField = Shelves.class.getDeclaredField("shelves");
        shelvesField.setAccessible(true);
        Shelf[] shelvesArray = (Shelf[]) shelvesField.get(shelves);
        List<Book> books = shelvesArray[0].getShelfBooks();
        int booksNum = shelvesArray[0].getBooksCurrentlyOnShelf();
        Book book = books.get(1);
        assertEquals("duMmy", book.getTitle());
        assertEquals("duMmy", book.getAuthor());
        assertEquals(1, booksNum);
    }
    @Test
    public void test2DeleteBookFromSection() throws NoSuchFieldException, IllegalAccessException {
        shelves.deleteBookFromSection(0, 0); // Delete "Book 1"
        Field shelvesField = Shelves.class.getDeclaredField("shelves");
        shelvesField.setAccessible(true);
        Shelf[] shelvesArray = (Shelf[]) shelvesField.get(shelves);
        List<Book> books = shelvesArray[0].getShelfBooks();
        int booksNum = shelvesArray[0].getBooksCurrentlyOnShelf();
        Book book = books.get(0);
        assertEquals("duMmy", book.getTitle());
        assertEquals("duMmy", book.getAuthor());
        assertEquals(1, booksNum);
    }

    @Test
    public void testDeleteBookInvalidShelfNum() {
        try {
            shelves.deleteBookFromSection(5,0);
            fail();
        } catch (AssertionError e) {
            assertEquals("Invalid Shelf Number!", e.getMessage());
        }
    }

    @Test
    public void testDeleteBookInvalidSlotNum() {
        try {
            shelves.deleteBookFromSection(0,-1);
            fail();
        } catch (AssertionError e) {
            assertEquals("Invalid bookIndex!", e.getMessage());
        }
    }
}
