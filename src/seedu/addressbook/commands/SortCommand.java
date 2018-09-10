package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Lists all persons in the address book to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book as a list with index numbers sorted according to the the specified field.\n"
            + "Example: " + COMMAND_WORD + " name";

    private final String keyword;

    public SortCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns a copy of keyword in this command.
     */
    public String getKeyword() {
        return keyword;
    }

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
    }
}
