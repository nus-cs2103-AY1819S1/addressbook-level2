package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;


/**
 * Count number of persons in address book.
 */
public class CountCommand extends Command {

    public static final String COMMAND_WORD = "count";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Count number of persons in this address book.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        return new CommandResult(getMessageForPersonListShownSummary(allPersons));
    }
}
