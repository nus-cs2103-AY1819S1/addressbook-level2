package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.*;

/**
 * Sorts the existing persons in the address book according to the alphabetical order of their first name.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the existing persons in the address book"
            + "according to the alphabetical order of their first name.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        List<ReadOnlyPerson> sortPersons = new ArrayList<>(allPersons);
        Collections.sort(sortPersons, new Comparator<ReadOnlyPerson>() {
            @Override
            public int compare(ReadOnlyPerson o1, ReadOnlyPerson o2) {
                return o1.getName().toString().compareTo(o2.getName().toString());
            }
        });
        return new CommandResult(getMessageForPersonListShownSummary(sortPersons), sortPersons);
    }
}
