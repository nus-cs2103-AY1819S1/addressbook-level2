package seedu.addressbook.commands;

/**
 * Imports an address book.
 */
public class ImportCommand extends Command {
    public static final String COMMAND_WORD = "import";
    public static final String MESSAGE_USAGE = "Imports an address book.\n"
        + "Example: " + COMMAND_WORD
        + " import_addressbook.txt";

    public static final String MESSAGE_SUCCESS = "Added %d contacts.";

    @Override
    public CommandResult execute() {
        return null;
    }

}
