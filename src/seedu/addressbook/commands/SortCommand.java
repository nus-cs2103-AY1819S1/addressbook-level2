package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.*;

/**
 * Lists all persons in the address book to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Display all persons sorted alphabetically in the address book as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        final List<ReadOnlyPerson> allPersons = new ArrayList<>();

        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            allPersons.add(person);
        }

        Collections.sort(allPersons, Comparator.comparing(o -> o.getName().toString()));

        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
    }
}