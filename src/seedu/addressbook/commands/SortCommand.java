package seedu.addressbook.commands;

import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;


/**
 * Shows all persons in the address book sorted in alphabetical order to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book in an alphabetical order.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
    	UniquePersonList allPersons = addressBook.getAllPersons();
    	allPersons.sort();
    	List<ReadOnlyPerson> sortedPersons = allPersons.immutableListView();
    	return new CommandResult(getMessageForPersonListShownSummary(sortedPersons), sortedPersons);
    }
}