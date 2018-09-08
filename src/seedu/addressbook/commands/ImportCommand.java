package seedu.addressbook.commands;

import seedu.addressbook.parser.Parser;
import seedu.addressbook.Main;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


/**
 * Imports an address book.
 */
public class ImportCommand extends Command {
    public static final String COMMAND_WORD = "import";
    public static final String MESSAGE_USAGE = "Imports an address book.\n"
        + "Example: " + COMMAND_WORD
        + " import_addressbook.txt";

    public static final String MESSAGE_SUCCESS = "Added %d contacts.";

    private String filename;

    public ImportCommand(String filename) {
        this.filename = filename;
    }

    @Override
    public CommandResult execute() {
        try {
            Path pathToImportAB = Paths.get(this.filename);
            List<String> importedEntry = Files.readAllLines(pathToImportAB);

            int newEntriesCount = 0;
            Command addNewEntryCommand;
            for (String entry : importedEntry) {
                addNewEntryCommand = new Parser().parseCommand("add " + entry);
                Main.executeCommand(addNewEntryCommand);
                newEntriesCount++;
            }

            return new CommandResult(String.format(MESSAGE_SUCCESS, newEntriesCount));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Main.executeCommand(new HelpCommand());
    }

}
