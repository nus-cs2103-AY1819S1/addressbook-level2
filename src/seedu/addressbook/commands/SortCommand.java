package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.Comparator;
import java.util.List;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the address book alphabetically "
            + "\nExample: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Successfully sorted the addressbook";

    public static final String MESSAGE_ERROR = "Not enough data to sort";

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        if (allPersons.size() > 1) {
            addressBook.sort();
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_ERROR);
        }
    }


}
