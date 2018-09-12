package seedu.addressbook.commands;

import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;

/**
 *
 * Sorts contacts by name and lists in alphabetical (ascending) order
 *
 */

public class SortCommand extends Command{

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts all contacts in the address book by name in alphabetical (ascending) order.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        UniquePersonList allPersons = addressBook.getAllPersons();
        allPersons.sort();
        List<ReadOnlyPerson> sortedByName = allPersons.immutableListView();
        return new CommandResult(getMessageForPersonListShownSummary(sortedByName), sortedByName);
    }

}
