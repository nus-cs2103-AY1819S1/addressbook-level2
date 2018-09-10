package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;

/**
 * Lists all persons sorted in alphabetical order in the address book to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons sorted in alphabetical order in the address book as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> sortedAllPersons = addressBook.getAllPersons().sortByAlphabetToImmutableList();
        return new CommandResult(getMessageForPersonListShownSummary(sortedAllPersons), sortedAllPersons);
    }
}
