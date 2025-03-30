package seedu.duke.shelving;

import seedu.duke.shelving.shelves.ActionShelves;
import seedu.duke.shelving.shelves.AdventureShelves;
import seedu.duke.shelving.shelves.HorrorShelves;
import seedu.duke.shelving.shelves.MysteryShelves;
import seedu.duke.shelving.shelves.NonFictionShelves;
import seedu.duke.shelving.shelves.RomanceShelves;
import seedu.duke.shelving.shelves.SciFiShelves;
import seedu.duke.exception.SectionFullException;


public class ShelvesManager {
    private static final String ROMANCE = "romance";
    private static final String ADVENTURE = "adventure";
    private static final String ACTION = "action";
    private static final String HORROR = "horror";
    private static final String MYSTERY = "mystery";
    private static final String NONFICTION = "nonfiction";
    private static final String SCIFI = "scifi";

    private static final String ROMANCE_ID = "R";
    private static final String ADVENTURE_ID = "AD";
    private static final String ACTION_ID = "AC";
    private static final String HORROR_ID = "H";
    private static final String MYSTERY_ID = "MY";
    private static final String NONFICTION_ID = "NF";
    private static final String SCIFI_ID = "SCIF";

    private final RomanceShelves romanceShelves;
    private final AdventureShelves adventureShelves;
    private final ActionShelves actionShelves;
    private final HorrorShelves horrorShelves;
    private final MysteryShelves mysteryShelves;
    private final NonFictionShelves nonFictionShelves;
    private final SciFiShelves sciFiShelves;


    //@@author WayneCh0y
    public ShelvesManager() {
        romanceShelves = new RomanceShelves();
        adventureShelves = new AdventureShelves();
        actionShelves = new ActionShelves();
        horrorShelves = new HorrorShelves();
        mysteryShelves = new MysteryShelves();
        nonFictionShelves = new NonFictionShelves();
        sciFiShelves = new SciFiShelves();
    }

    //@@author WayneCh0y
    public String listShelf(String shelfGenre, int shelfIndex) {
        try {
            switch (shelfGenre) {
            case ROMANCE:
                return romanceShelves.listShelf(shelfIndex);
            case ADVENTURE:
                return adventureShelves.listShelf(shelfIndex);
            case ACTION:
                return actionShelves.listShelf(shelfIndex);
            case HORROR:
                return horrorShelves.listShelf(shelfIndex);
            case MYSTERY:
                return mysteryShelves.listShelf(shelfIndex);
            case NONFICTION:
                return nonFictionShelves.listShelf(shelfIndex);
            case SCIFI:
                return sciFiShelves.listShelf(shelfIndex);
            default:
                return "";
            }

        } catch (SectionFullException e) {
            System.out.println(e);
        }
        return "";
    }

    //@@author WayneCh0y
    public String addBook(String title, String author, String genre) {
        try {
            switch (genre) {
            case ROMANCE:
                return romanceShelves.addBookToSection(title, author);
            case ADVENTURE:
                return adventureShelves.addBookToSection(title, author);
            case ACTION:
                return actionShelves.addBookToSection(title, author);
            case HORROR:
                return horrorShelves.addBookToSection(title, author);
            case MYSTERY:
                return mysteryShelves.addBookToSection(title, author);
            case NONFICTION:
                return nonFictionShelves.addBookToSection(title, author);
            case SCIFI:
                return sciFiShelves.addBookToSection(title, author);
            default:
                break;
            }
        } catch (SectionFullException e) {
            System.out.println(e);
        }
        return "Added";
    }


    //@@author Deanson Choo
    /**
     * 'Deletes' a book from the appropriate shelf using its unique book ID.
     * Book is replaced with a dummy book
     * <p>
     * The book ID is expected to follow the format {@code GENRE-ID-SLOT}, e.g., {@code R-0-1}.
     * This method extracts the shelf genre ID, shelf number, and slot number from the book ID,
     * and delegates the deletion to the corresponding shelf section.
     *
     * @param bookID The unique ID of the book to delete, in the format {@code GENRE-ID-SLOT}.
     */
    public void deleteBook(String bookID){
        String[] parts = bookID.split("-");
        String shelfID = parts[0];
        assert shelfID != null;
        int shelfNum = Integer.parseInt(parts[1]);
        assert shelfNum >= 0 && shelfNum < 5;
        int slotNum = Integer.parseInt(parts[2]);
        assert slotNum >= 0;
        switch (shelfID) {
        case ROMANCE_ID:
            romanceShelves.deleteBookFromSection(shelfNum, slotNum);
            break;
        case ADVENTURE_ID:
            adventureShelves.deleteBookFromSection(shelfNum, slotNum);
            break;
        case ACTION_ID:
            actionShelves.deleteBookFromSection(shelfNum, slotNum);
            break;
        case HORROR_ID:
            horrorShelves.deleteBookFromSection(shelfNum, slotNum);
            break;
        case MYSTERY_ID:
            mysteryShelves.deleteBookFromSection(shelfNum, slotNum);
            break;
        case NONFICTION_ID:
            nonFictionShelves.deleteBookFromSection(shelfNum, slotNum);
            break;
        case SCIFI_ID:
            sciFiShelves.deleteBookFromSection(shelfNum, slotNum);
            break;
        default:
            break;
        }
    }

    //@@author WayneCh0y
    public String getBookId(String genre) {
        switch (genre) {
        case ROMANCE:
            return romanceShelves.getBookID();
        case ADVENTURE:
            return adventureShelves.getBookID();
        case ACTION:
            return actionShelves.getBookID();
        case HORROR:
            return horrorShelves.getBookID();
        case MYSTERY:
            return mysteryShelves.getBookID();
        case NONFICTION:
            return nonFictionShelves.getBookID();
        case SCIFI:
            return sciFiShelves.getBookID();
        default:
            break;
        }
        return "No Book ID found!";
    }

}
