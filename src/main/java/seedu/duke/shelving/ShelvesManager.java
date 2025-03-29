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
    public String addBook(String bookDetails, String genre) {
        try {
            switch (genre) {
            case ROMANCE:
                return romanceShelves.addBookToSection(bookDetails);
            case ADVENTURE:
                return adventureShelves.addBookToSection(bookDetails);
            case ACTION:
                return actionShelves.addBookToSection(bookDetails);
            case HORROR:
                return horrorShelves.addBookToSection(bookDetails);
            case MYSTERY:
                return mysteryShelves.addBookToSection(bookDetails);
            case NONFICTION:
                return nonFictionShelves.addBookToSection(bookDetails);
            case SCIFI:
                return sciFiShelves.addBookToSection(bookDetails);
            default:
                return "";
            }
        } catch (SectionFullException e) {
            System.out.println(e);
        }
        return "Added";
    }

    public String deleteBook(String bookID) {
        String[] parts = bookID.split("-");
        String shelfID = parts[0];
        int shelfNum = Integer.parseInt(parts[1]);
        int slotNum = Integer.parseInt(parts[2]);
        switch (shelfID) {
        case ROMANCE_ID:
            return romanceShelves.deleteBookFromSection(shelfNum, slotNum) + shelfNum;
        case ADVENTURE_ID:
            return adventureShelves.deleteBookFromSection(shelfNum, slotNum) + shelfNum;
        case ACTION_ID:
            return actionShelves.deleteBookFromSection(shelfNum, slotNum) + shelfNum;
        case HORROR_ID:
            return horrorShelves.deleteBookFromSection(shelfNum, slotNum) + shelfNum;
        case MYSTERY_ID:
            return mysteryShelves.deleteBookFromSection(shelfNum, slotNum) + shelfNum;
        case NONFICTION_ID:
            return nonFictionShelves.deleteBookFromSection(shelfNum, slotNum) + shelfNum;
        case SCIFI_ID:
            return sciFiShelves.deleteBookFromSection(shelfNum, slotNum) + shelfNum;
        default:
            return "No such book";
        }
    }

}
