package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;


/**
 * Lists all persons in the address book to the user.
 */
public class TotalNumberCommand extends Command {

    public static final String COMMAND_WORD = "Total";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays total number of all the people in this address book.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        return new CommandResult(getMessageForTotalNumber(allPersons), null);
    }
}