package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;

import java.util.List;


/**
 * Sorts all persons in the address book by name and lists the results to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all persons in the address book as a list with index numbers, sorted by name.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        UniquePersonList allPersons = addressBook.getAllPersons();
        allPersons.sort();
        List<ReadOnlyPerson> allSorted = allPersons.immutableListView();
        return new CommandResult(getMessageForPersonListSortedShownSummary(allSorted), allSorted);
    }
}
