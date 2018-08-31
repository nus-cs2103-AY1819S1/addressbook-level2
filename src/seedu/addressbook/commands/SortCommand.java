package seedu.addressbook.commands;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import seedu.addressbook.data.person.ReadOnlyPerson;


/**
 * Sort all persons in the address book.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Sorts all person in the address book and displays as a sorted list with index numbers.\n"
        + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableSortedListView();
        return new CommandResult(getMessageForAddressBookSortedSummary(allPersons), allPersons);
    }
}
