package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;

public class StatsCommand extends Command {


    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Show number of people in address book\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_STATS = "Total number of contacts in address book: ";

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        int size = allPersons.size();
        return new CommandResult(MESSAGE_STATS + size);
    }

}
