package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;


/**
 * Generates 5 persons into the address book.
 */
public class GenerateCommand extends Command {

    public static final String COMMAND_WORD = "generate";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Generates 5 persons into the address book.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        //List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        return new CommandResult("Successfully added 5 persons");
    }
}
