package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;


/**
 * Shows details of the person identified using the last displayed index.
 * Light version of View where only phone number of the contact will be shown.
 */
public class PhoneListCommand extends Command {
    public static final String COMMAND_WORD = "phonelist";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book with only phone number "
            + "information as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        return new CommandResult(getMessageForPersonListShownSummary(allPersons),
                allPersons, true);
    }
}
