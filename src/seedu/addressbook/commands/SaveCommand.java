package seedu.addressbook.commands;

import java.util.List;
import seedu.addressbook.data.person.ReadOnlyPerson;

public class SaveCommand extends Command {
    public static final String COMMAND_WORD = "save";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Saves current contact list to current directory\n"
        + "Example: " + COMMAND_WORD + " 1";

    public CommandResult execute() {
      List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
      return new CommandResult(saveAddressBook(allPersons));
    }
}
