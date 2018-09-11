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
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    //TODO: change to Set
    private final List<Integer> targetIndexes;

    public DeleteMCommand(List<Integer> targetIndexes) {
        this.targetIndexes = targetIndexes;

    }

    @Override
    public CommandResult execute() {
//        try {
        ArrayList<CommandResult> output = new ArrayList <>();

        for (Integer item: targetIndexes){
            setTargetIndex(item);
            final ReadOnlyPerson target = getTargetPerson();

            output.add (executeCommand(Delete) );
        }

        return new CommandResult(output);

            //TODO:
//        } catch (IndexOutOfBoundsException ie) {
//            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
//        } catch (PersonNotFoundException pnfe) {
//            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
//        }
    }
}
