package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.ArrayList;
import java.util.List;

/**
 * Shows the newest entry into the address book.
 */
public class NewestEntryCommand extends Command {

    public static final String COMMAND_WORD = "newest";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the newest entry into the address book. "
            + "Example: " + COMMAND_WORD;
    private static final String MESSAGE_SUCCESS = "The Newest entry is: %1$s. %2$s";


    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson lastPerson = addressBook.getLastPerson();
            final int indexOfPerson = addressBook.getCurrentSize();
            return new CommandResult(String.format(MESSAGE_SUCCESS, indexOfPerson, lastPerson));
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(Messages.MESSAGE_NO_ENTRY_IN_THE_LIST);
        }
    }
}
