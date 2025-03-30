package seedu.duke.commands;

import seedu.duke.library.Library;
import seedu.duke.member.MemberManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class DeleteCommand extends Command {

    private final int bookIndex;

    public DeleteCommand(int index) {
        bookIndex = index;
    }

    @Override
    public void execute(Library library, Ui ui, Storage storage, MemberManager memberManager) {
        assert library != null : "BookManager should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        String response = library.deleteBook(bookIndex);
        ui.printWithSeparator(response);
        storage.writeToFile(library.getBooks());
    }
}
