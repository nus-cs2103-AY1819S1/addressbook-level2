package seedu.addressbook.commands;

import seedu.addressbook.data.history.ClearAllHistory;

import java.time.LocalTime;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = "Clears address book permanently.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";

    @Override
    public CommandResult execute() {
        ClearAllHistory historyToAdd = new ClearAllHistory(LocalTime.now(), addressBook.getAllPersons().getSize());
        addressBook.clear();
        addressBook.addHistory(historyToAdd);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
