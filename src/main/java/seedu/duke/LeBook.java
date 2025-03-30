package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.exception.LeBookException;
import seedu.duke.library.Library;
import seedu.duke.ui.Ui;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.member.MemberManager;

/**
 * Lebook Class represents the main chatbot system
 * Initializes storage, UI, task handling, and command parsing
 */

public class LeBook {
    private final Library library;
    private final Storage storage;
    private final Ui ui;
    private final MemberManager memberManager;

    public LeBook(String filePath) {
        assert filePath != null && !filePath.trim().isEmpty() : "File path cannot be null or empty";

        ui = Ui.getUiInstance();
        storage = Storage.getInstance(filePath);
        memberManager = new MemberManager();
        library = Library.getTheOneLibrary(storage.loadFileContents());

        // Assertions to check if critical components are initialized
        assert ui != null : "UI instance should not be null";
        assert storage != null : "Storage instance should not be null";
        assert library != null : "BookManager instance should not be null";
    }

    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                assert userInput != null : "User input should not be null";

                Command command = Parser.parse(userInput);
                assert command != null : "Parser should return a valid Command object";

                command.execute(library, ui, storage, memberManager);
                isExit = command.isExit();
            } catch (LeBookException e) {
                ui.printError(e.getMessage());
            }
        }
        ui.printExitMessage();
    }

    public static void main(String[] args) {
        assert args != null : "Program arguments should not be null";
        new LeBook("data/LeBook_data.txt").run();
    }
}

