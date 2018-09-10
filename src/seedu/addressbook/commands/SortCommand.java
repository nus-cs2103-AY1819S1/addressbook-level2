package seedu.addressbook.commands;

/**
 * Sort the address book.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sort all persons in the address book in lexical order.\n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "List sorted in lexical order.";

    @Override
    public CommandResult execute() {
        addressBook.sortAllPersons();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
