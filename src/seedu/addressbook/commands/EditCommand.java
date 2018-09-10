package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import java.util.ArrayList;


/** Edit a person's phone/email/address identified using its last displayed indexed from the address book */


public class EditCommand extends Command {

    private String editD;
    private String targetPrefix;
    private int prefixIndex;
    private ArrayList<String> PREFIXES;

    public static final String COMMAND_WORD = "edit" ;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits one of the details of a person identified by the index number used in the last person listing.\n"
            + "Parameters: INDEX, NAME/[p]p/PHONE/[p]e/EMAIL/[p]a/ADDRESS\n"
            + "Example: " + COMMAND_WORD + " 1" + " a/NEW_ADDRESS";


    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Changes saved";
    public static final String MESSAGE_UNRECOGNIZED_PREFIX = "Unrecognized prefix";

    public EditCommand(int targetVisibleIndex, String editD) {
        super(targetVisibleIndex);
        this.PREFIXES = new ArrayList<>();
        PREFIXES.add("a/");
        PREFIXES.add("e/");
        PREFIXES.add("p/");
        PREFIXES.add("n/");
        PREFIXES.add("pa/");
        PREFIXES.add("pe/");
        PREFIXES.add("pp/");

        this.editD =  editD.substring(2);
        this.targetPrefix = editD.substring(0,2);
    }

    @Override
    public CommandResult execute() {
        try {
            if (PREFIXES.indexOf(targetPrefix) >= 0) {
                final ReadOnlyPerson target = getTargetPerson();
                prefixIndex = PREFIXES.indexOf(targetPrefix);
                addressBook.editDetails(target, prefixIndex, editD);
                return new CommandResult(MESSAGE_EDIT_PERSON_SUCCESS);
            }

            else {
                return new CommandResult(MESSAGE_UNRECOGNIZED_PREFIX);
            }

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        } catch (IllegalValueException e) {
            return new CommandResult((Messages.MESSAGE_INVALID_COMMAND_FORMAT));
        }
    }

}
