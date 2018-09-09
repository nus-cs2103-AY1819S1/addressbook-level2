package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;

import java.util.*;

/**
 * Sorts the people in the address book by name
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the people in the address book by name. ";

    public static final String MESSAGE_SUCCESS = "Sorted names have been displayed!";

    @Override
    public CommandResult execute() {
        UniquePersonList persons = addressBook.getAllPersons();
        ArrayList<ReadOnlyPerson> sortedPersonList = persons.sort();
        return new CommandResult(MESSAGE_SUCCESS, sortedPersonList);
    }
}
