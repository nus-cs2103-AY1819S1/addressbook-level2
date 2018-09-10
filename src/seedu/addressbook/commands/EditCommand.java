package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;

/** Edit a person identified using its last displayed indexed from the address book */


public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit" ;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the person identified by the index number used in the last person listing.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1" + " a/NEW_ADDRESS";


    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Changes saved";

    public EditCommand(int targetVisibleIndex, String... details) {
        super(targetVisibleIndex);
    }

}
