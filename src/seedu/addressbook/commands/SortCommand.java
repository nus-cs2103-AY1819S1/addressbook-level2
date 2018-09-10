package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.Comparator;
import java.util.List;

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays a list of all persons in the address book sorted alphabetically by name.\n"
            + "Does not modify the address book's original order.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().sortedImmutableListView();
        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
    }


}
