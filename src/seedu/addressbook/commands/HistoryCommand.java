package seedu.addressbook.commands;

import seedu.addressbook.data.history.History;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;

public class HistoryCommand extends Command {

    public static final String COMMAND_WORD = "history";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all the histories in the address book as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;

}
