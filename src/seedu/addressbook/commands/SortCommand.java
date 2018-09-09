package seedu.addressbook.commands;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
 * Adds a person to the address book.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort all the "
            + "persons in the address book in alphabetically ascending order."
            + "\n" + "Example: " + COMMAND_WORD;


    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    /*
    public SortCommand() throws IllegalValueException {
       // Done nothing now.
    }
    */

    @Override
    public CommandResult execute() {
            addressBook.sortPersons();
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().sortedListView();
        return new CommandResult(getMessageForPersonSortShownSummary(allPersons), allPersons);
    }

}
