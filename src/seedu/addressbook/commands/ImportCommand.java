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

    public static final String MESSAGE_SUCCESS = "Import of %s Successful.\n"
        + "Added %d contacts.";

    public static final String MESSAGE_IMPORT_FAILURE = "Import unsuccessful" +
        ".\n";

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
            StringBuilder resultSB = new StringBuilder();
            for (String entry : importedEntry) {
                addNewEntryCommand = new Parser().parseCommand("add " + entry);
                CommandResult results = Main.executeCommand(addNewEntryCommand);
                if (!results.feedbackToUser.equals(AddCommand.MESSAGE_DUPLICATE_PERSON)){
                    newEntriesCount++;
                }
                resultSB.append(results.feedbackToUser);
                resultSB.append("\n");
            }
            resultSB.append(String.format(MESSAGE_SUCCESS, this.filename,
                newEntriesCount));

            return new CommandResult(resultSB.toString());
        } catch (IOException e) {
            return new CommandResult(MESSAGE_IMPORT_FAILURE);
        }
    }

}
