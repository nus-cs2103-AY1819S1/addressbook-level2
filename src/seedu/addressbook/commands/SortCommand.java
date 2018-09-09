package seedu.addressbook.commands;

/**
 * Adds a person to the address book.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the address book by name "
            + "in alphabetical order.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Address book has been sorted!";

    @Override
    public CommandResult execute() {
        // Sort the address book
        addressBook.sort();

        // Return the command result
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
