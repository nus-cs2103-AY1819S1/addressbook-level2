package seedu.addressbook.commands;

/**
 * Sorts the address book by name alphabetically.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = "Sorts the address book by name alphabetically.\n"
        + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Address book has been sorted!";

    @Override
    public CommandResult execute() {
        addressBook.sort();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
