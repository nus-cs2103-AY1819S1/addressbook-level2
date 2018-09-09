package seedu.addressbook.commands;


import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;

import java.util.List;

/**
 * Swaps the index of 2 person in the address book.
 */
public class SwapCommand extends Command {

    public static final String COMMAND_WORD = "swap";
    public static final String MESSAGE_USAGE = "Swap index of 2 person in address book.\n"
            + "Example: " + COMMAND_WORD +" 1 2";

    public static final String MESSAGE_SWAP_SUCCESS = "Index successfully swapped.";

    private final int ARRAY_OFFSET = 1;

    private final int target1Index, target2Index;

    public SwapCommand(int target1, int target2) {
        this.target1Index = target1 - ARRAY_OFFSET;
        this.target2Index = target2 - ARRAY_OFFSET;
    }

    @Override
    public CommandResult execute() {

        //swap contacts here
        try {
            addressBook.swapIndex(target1Index, target2Index);
            List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
            return new CommandResult(MESSAGE_SWAP_SUCCESS, allPersons);
        } catch (UniquePersonList.PersonNotFoundException e) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }
}
