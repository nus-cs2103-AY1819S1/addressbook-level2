package seedu.addressbook.commands;


import java.util.List;
import java.util.Collections;
import seedu.addressbook.data.person.ReadOnlyPerson;


/**
 * Lists all persons in the address book to the user sorted in alphabetical order.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book as a list with index numbers sorted in alphabetical order.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().sortedListView();
        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
    }
}