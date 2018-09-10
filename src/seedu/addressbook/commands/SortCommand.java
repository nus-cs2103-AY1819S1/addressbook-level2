package seedu.addressbook.commands;

/**
 * Sort the address book.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_SUCCESS = "List sorted in lexical order.";

    @Override
    public CommandResult execute() {
        addressBook.sortAllPersons();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
