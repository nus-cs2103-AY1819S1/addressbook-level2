package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;


/**
 * Lists all persons in the address book to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book as an alphabetically sorted list with index numbers.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersonsAlpha = addressBook.getAllPersonsAlphabetical().immutableListView();
        return new CommandResult(getMessageForPersonListShownSummary(allPersonsAlpha), allPersonsAlpha);
    }
}
