package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;


/**
 * Renames a person identified using it's last displayed index from the address book.
 */

public class RenameCommand extends Command {

    public static final String COMMAND_WORD = "rename";
    private static Name newName;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Renames the person identified by the index number used in the last person listing.\n"
            + "Parameters: INDEX NEW_NAME\n"
            +"Example: " + COMMAND_WORD + " 1 Jack";

    public RenameCommand(String targetVisibleIndex, String newName) {

        super(Integer.parseInt(targetVisibleIndex));
        try {
            this.newName = new Name(newName);
        } catch (IllegalValueException e) {}
    }

    private static final String MESSAGE_RENAME_PERSON_SUCCESS = "Renamed %1$s to newName";

    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetPerson();
            final Person renamed = new Person(
                    newName,
                    target.getPhone(),
                    target.getEmail(),
                    target.getAddress(),
                    target.getTags()
            );
            addressBook.removePerson(target);
            addressBook.addPerson(renamed);
            return new CommandResult(String.format(MESSAGE_RENAME_PERSON_SUCCESS, target));

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (UniquePersonList.PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        } catch (Exception e) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }
}
