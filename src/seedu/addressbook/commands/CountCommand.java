package seedu.addressbook.commands;


import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;

/**
 * Count the number of all persons in the address book to the user.
 */
public class CountCommand extends Command {

    public static final String COMMAND_WORD = "count";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Counts the number of all persons in the address book.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();

        return new CommandResult("Number of people:" + allPersons.size() + "\n", allPersons);
    }
}

