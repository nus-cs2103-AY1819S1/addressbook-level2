package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import seedu.addressbook.ui.Formatter;

import java.util.Set;


/**
 * Deletes a person identified using it's last displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the last person listing.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";


    public DeleteCommand(int... targetVisibleIndex) {
        super(targetVisibleIndex);
    }


    @Override
    public CommandResult execute() {
        Set<Integer> targetIndexes = getTargetIndexes();
        String msgToReturn = "";
        for (Integer targetIndex : targetIndexes) {
            try {
                final ReadOnlyPerson target = getTargetPerson(targetIndex);
                addressBook.removePerson(target);
                String resultFromDelete = String.format(MESSAGE_DELETE_PERSON_SUCCESS, target);
                msgToReturn = Formatter.formatConcatResultMsg(msgToReturn, resultFromDelete);
            } catch (IndexOutOfBoundsException ie) {
                msgToReturn = Formatter.formatConcatResultMsg(msgToReturn, 
                        String.format(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX_WITH_CONTEXT, targetIndex));
            } catch (PersonNotFoundException pnfe) {
                msgToReturn = Formatter.formatConcatResultMsg(msgToReturn,
                        String.format(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK_WITH_CONTEXT, targetIndex));
            }
        }
        return new CommandResult(msgToReturn);
    }
}
