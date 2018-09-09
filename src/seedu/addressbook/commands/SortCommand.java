package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Sorts all persons in the address book, using name, to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts all persons in the address book by name.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        List<Person> allPersons = addressBook.getAllPersons().mutableListView();
        Comparator<Person> byName = Comparator.comparing(Person::getName);
        Collections.sort(allPersons, byName);
        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
    }
}
