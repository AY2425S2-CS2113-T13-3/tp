package seedu.duke.commands;

import seedu.duke.exception.LeBookException;
import seedu.duke.library.Library;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class ListShelfCommand extends Command{
    private final String shelfGenre;
    private final int shelfIndex;

    public ListShelfCommand(String genre, int index) {
        this.shelfGenre = genre;
        this.shelfIndex = index;
    }

    @Override
    public void execute(Library library, Ui ui, Storage storage, MemberManager membermanager) throws LeBookException {
        assert library != null : "BookManager should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        String response = library.listShelf(shelfGenre, shelfIndex);
        ui.printWithSeparator(response);
    }
}
