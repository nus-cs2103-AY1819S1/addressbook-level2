package seedu.addressbook.commands;

import java.util.List;
import seedu.addressbook.data.person.ReadOnlyPerson;

/**
 * Sort all persons in the address book in alphabetical order.
 */
public class SortCommand extends Command{
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts all persons in the address book in alphabetical order.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SORTED = "Address book has been sorted "
            + "in alphabetical order";

    @Override
    public CommandResult execute() {
        addressBook.sort();
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        return new CommandResult(MESSAGE_SORTED, allPersons);
    }
}
