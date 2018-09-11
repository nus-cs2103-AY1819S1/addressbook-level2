package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import seedu.addressbook.ui.TextUi;

import java.util.Scanner;


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
    public static final String MESSAGE_DELETE_PERSON_CANCELLED = "Deletion Cancelled";

    public static final String MESSAGE_DELETE_CONFIRM = "|| Do you really want to delete this entry? " +
            "Press 1 to confirm, and 0 to cancel: ";


    public DeleteCommand(int targetVisibleIndex, TextUi ui) {
        super(targetVisibleIndex);
        this.ui = ui;
    }

    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetPerson();
            System.out.println(MESSAGE_DELETE_CONFIRM);
            int answer = ui.getUserConfirmation();
            if(answer == 1){
                addressBook.removePerson(target);
                return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, target));
            }else{
                return new CommandResult(MESSAGE_DELETE_PERSON_CANCELLED);
            }
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        }
    }

}
