package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Display all persons sorted according to ascending order of their names\n"
            + "Example: " + COMMAND_WORD;

    private static final String MESSAGE_SORT_ACKNOWLEDGEMENT = "All persons sorted according to lexicographical order "
            + "their name.";

    @Override
    public CommandResult execute() {
        addressBook.sort();
        return new CommandResult(MESSAGE_SORT_ACKNOWLEDGEMENT);
    }
}
