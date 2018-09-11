package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;

import java.util.Comparator;
import java.util.List;


/**
 * Lists all persons in the address book to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts all persons in the address book and displays it.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() throws UniquePersonList.DuplicatePersonException {
        UniquePersonList allPersons = addressBook.getAllPersons();
        List<Person> allPersonsList = allPersons.getInternalList();

        //Sorts the Person List based on the names
        allPersonsList.sort(Comparator.comparing(o -> o.getName().toString()));

        UniquePersonList allSortedPersons = new UniquePersonList(allPersonsList);
        List<ReadOnlyPerson> readOnlySortedPersons = allSortedPersons.immutableListView();
        return new CommandResult(
                getMessageForPersonListShownSummary(readOnlySortedPersons), readOnlySortedPersons);
    }
}