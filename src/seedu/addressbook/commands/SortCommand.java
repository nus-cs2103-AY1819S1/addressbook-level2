package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.UniquePersonList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Sorts all persons in the address book to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts all persons in the address book alphabetically according to their name.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Address book has been sorted!";
    public static final String MESSAGE_UNEXPECTED_EVENT = "Something is wrong! Please restart the application.";

    @Override
    public CommandResult execute() {
        // clone list of persons in the current address book
        Iterator<Person> iteratorOfPersons = addressBook.getAllPersons().iterator();
        List<Person> sortedAllPersons = new ArrayList<>();
        while (iteratorOfPersons.hasNext()) {
            sortedAllPersons.add(iteratorOfPersons.next());
        }

        // sort the clone
        Collections.sort(sortedAllPersons);

        // clear address book
        addressBook.clear();

        // add each person in sorted clone to the cleared address book
        for (Person person : sortedAllPersons) {
            try {
                addressBook.addPerson(person);
            } catch (UniquePersonList.DuplicatePersonException dpe) {
                return new CommandResult(MESSAGE_UNEXPECTED_EVENT);
            }
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
