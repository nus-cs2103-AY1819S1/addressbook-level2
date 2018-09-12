package seedu.addressbook.commands;

/**
 * Shows number of persons in the address book.
 */

public class CountCommand extends Command {

    public static final String COMMAND_WORD = "count";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Counts the number of person/s in the address book.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "There are %1$d people in the address book.";

    @Override
    public CommandResult execute() {
        return new CommandResult(String.format(MESSAGE_SUCCESS, addressBook.getNumOfPersons()));
    }

}
