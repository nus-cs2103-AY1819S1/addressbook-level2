package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;

/**
 * Deletes every person in the last displayed list in the address book.
 */
public class DeleteAllCommand extends Command {

    public static final String COMMAND_WORD = "deleteall";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes every person in the last displayed list in the address book.\n"
            + "Parameters: none\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_DELETE_ALL_SUCCESS = "All Persons from last displayed list deleted";

    @Override
    public CommandResult execute() {
        try {
            int lastIndex = this.relevantPersons.size();
            if (lastIndex == 0) {
                return new CommandResult(Messages.MESSAGE_NO_PERSON_IN_LIST);
            }
            while (lastIndex > 0) {
                setTargetIndex(lastIndex);
                ReadOnlyPerson target = getTargetPerson();
                addressBook.removePerson(target);
                lastIndex--;
            }
            return new CommandResult(String.format(MESSAGE_DELETE_ALL_SUCCESS));

        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_NO_PERSON_IN_LIST);
        }
    }

}
