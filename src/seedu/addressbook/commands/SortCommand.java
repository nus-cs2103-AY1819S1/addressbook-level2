package seedu.addressbook.commands;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.data.tag.Tag;

/**
 * Sort all persons in the address book to the user.
 */
public class SortCommand extends Command{
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": sort people in the address book.\n"
            + "Example: " + COMMAND_WORD;

    //public static final String MESSAGE_SUCCESS = "People sorted.";

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().sortedListView();
        return new CommandResult(getMessageForPersonSortShownSummary(allPersons), allPersons);
    }

}
