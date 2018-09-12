package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Deletes a person identified using it's last displayed index from the address book.
 */
public class DeleteMCommand extends Command{
    public static final String COMMAND_WORD = "deleteM";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes people identified by their comma seperated index numbers used in the last find/list call.\n"
            + "Parameters: COMMER SEPERATED INDEXES\n"
            + "Example: " + COMMAND_WORD + " 1,2,3";

    //TODO:
    public static final String MESSAGE_DELETEM_PERSON_SUCCESS = "Deleted Person:%1$s";

    //TODO: change to Set
    private final List<Integer> targetIndexes;

    public DeleteMCommand(List<Integer> targetIndexes) {
        this.targetIndexes = targetIndexes;

    }

    @Override
    public CommandResult execute() {
        ArrayList<String> output = new ArrayList <>();

        for (Integer targetIndex: targetIndexes){
            super.setTargetIndex(targetIndex);
            //TODO: add nextline for better format
            //TODO: simplify the code
            try {
                final ReadOnlyPerson targetPerson = getTargetPerson();
                addressBook.removePerson(targetPerson);
                output.add(String.format("Index "+ Integer.toString(targetIndex)+":"+ MESSAGE_DELETEM_PERSON_SUCCESS, targetPerson));
            } catch (IndexOutOfBoundsException ie) {
                output.add("Index "+ Integer.toString(targetIndex)+":"+Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            } catch (PersonNotFoundException pnfe) {
                output.add("Index "+ Integer.toString(targetIndex)+":"+Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
            }
        }

        return new CommandResult(String.valueOf(output));
    }
}
