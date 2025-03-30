package seedu.duke.commands;

import seedu.duke.library.Library;
import seedu.duke.member.MemberManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class AddCommand extends Command {
    private final String title;
    private final String author;
    private final String genre;

    public AddCommand(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    @Override
    public void execute(Library library, Ui ui, Storage storage, MemberManager memberManager) {
        assert library != null : "Library should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        assert title != null : "Title cannot be null";
        assert author != null : "Author cannot be null";
        String responseForCatalogue = library.addNewBookToCatalogue(title, author, genre);

        ui.printWithSeparator(responseForCatalogue);
        String responseForShelf = library.addNewBookToShelf(title, author, genre);
        ui.printWithSeparator(responseForShelf);
        storage.writeToFile(library.getBooks());
    }
}
