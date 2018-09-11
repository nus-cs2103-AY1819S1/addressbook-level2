package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;

/**
 * Display a sorted list of persons by name stored in the address book
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sort all persons in the address book by name.\n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "Sort Completed";

    @Override
    public CommandResult execute() {
        addressBook.sort();
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

}
