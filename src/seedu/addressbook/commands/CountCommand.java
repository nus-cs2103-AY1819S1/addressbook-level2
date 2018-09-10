package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;

public class CountCommand extends Command {
    public static final String COMMAND_WORD = "count";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the count of address entries in the address book.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_COUNT = "%1$d persons counted!";

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        return new CommandResult(String.format(MESSAGE_COUNT, allPersons.size()));
    }
}
