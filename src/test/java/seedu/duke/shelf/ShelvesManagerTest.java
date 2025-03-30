package seedu.duke.shelf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.shelving.shelves.RomanceShelves;
import seedu.duke.book.Book;
import seedu.duke.shelving.shelves.Shelf;
import seedu.duke.shelving.ShelvesManager;
import seedu.duke.shelving.shelves.Shelves;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author Deanson Choo
public class ShelvesManagerTest {

    private ShelvesManager shelvesManager;

    @BeforeEach
    public void setup() throws Exception {
        shelvesManager = new ShelvesManager();

        RomanceShelves romanceShelves = new RomanceShelves();

        Shelf shelf0 = new Shelf(0, "R");
        List<Book> books = new ArrayList<>();
        books.add(new Book("Test Book 1", "Author A", false, LocalDate.of(2025, 6, 1), "AC-0-1"));
        books.add(new Book("Test Book 2", "Author B", false, LocalDate.of(2025, 6, 1), "AC-0-2"));

        Field shelfField = Shelf.class.getDeclaredField("shelfBooks");
        shelfField.setAccessible(true);
        shelfField.set(shelf0, books);

        Field countField = Shelf.class.getDeclaredField("booksCurrentlyOnShelf");
        countField.setAccessible(true);
        countField.set(shelf0, 2);

        Field shelvesField = Shelves.class.getDeclaredField("shelves");
        shelvesField.setAccessible(true);

        Shelf[] shelvesArray = (Shelf[]) shelvesField.get(romanceShelves);
        shelvesArray[0] = shelf0;

        Field romanceShelvesField = ShelvesManager.class.getDeclaredField("romanceShelves");
        romanceShelvesField.setAccessible(true);
        romanceShelvesField.set(shelvesManager, romanceShelves);
    }

    @Test
    public void testDeleteBook_deletesCorrectly() throws NoSuchFieldException, IllegalAccessException{
        shelvesManager.deleteBook("R-0-1");

        Field romanceShelvesField = ShelvesManager.class.getDeclaredField("romanceShelves");
        romanceShelvesField.setAccessible(true);
        RomanceShelves romanceShelves = (RomanceShelves) romanceShelvesField.get(shelvesManager);

        Field shelvesField = Shelves.class.getDeclaredField("shelves");
        shelvesField.setAccessible(true);
        Shelf[] shelvesArray = (Shelf[]) shelvesField.get(romanceShelves);

        Shelf romanceShelf = shelvesArray[0];


        List<Book> books = romanceShelf.getShelfBooks();


        Book book = books.get(1);


        assertEquals("duMmy", book.getTitle(), "The book should be replaced by a dummy.");
        assertEquals("duMmy", book.getAuthor(), "The book author should be replaced by a dummy.");
    }


}
