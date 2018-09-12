package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;


/**
 * display the total number of persons stored in the address book
 */
public class TotalCommand extends Command {

    public static final String COMMAND_WORD = "total";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": display the total number of persons stored in the address book.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_TOTAL = "Total number of persons in address book is shown";


    public TotalCommand() {

    }


    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetPerson();
            return new CommandResult(String.format(MESSAGE_TOTAL, target));

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }

}
