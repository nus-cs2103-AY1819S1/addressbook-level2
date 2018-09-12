package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.ArrayList;
import java.util.List;

/**
 * Shows the newest entry into the address book.
 */
public class NewestEntryCommand extends Command {

    public static final String COMMAND_WORD = "newest";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the newest entry into the address book. "
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson lastPerson = addressBook.getLastPerson();
            final List<ReadOnlyPerson> asList = new ArrayList<>();
            asList.add(lastPerson);
            return new CommandResult(getMessageForPersonListShownSummary(asList), asList);
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(Messages.MESSAGE_NO_ENTRY_IN_THE_LIST);
        }
    }
}
