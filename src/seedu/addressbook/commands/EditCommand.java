package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;

public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits information of the person identified by the index number used in last person listing.\n"
            + "Parameters: INDEX field/new value\n"
            + "Example: " + COMMAND_WORD + " 1 e/hello@abc.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";

    private final String prefix;
    private final String value;
    private final boolean isPrivate;

    public EditCommand(int targetVisibleIndex, String prefix, String value, boolean isPrivate) {
        super(targetVisibleIndex);
        this.prefix = prefix;
        this.value = value;
        this.isPrivate = isPrivate;
    }

    @Override
    public CommandResult execute() {
        try {
            ReadOnlyPerson target = getTargetPerson();
            Person updatePerson = getUpdatedPerson(target);
            addressBook.removePerson(target);
            addressBook.addPerson(updatePerson);
            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, updatePerson));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (IllegalValueException ive) {
            return new CommandResult(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ive.getMessage()));
        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        }
    }

    /**
     * Clones a new Person with all values from ReadOnlyPerson except for the value that user
     * requested to edit
     *
     * @param target person to edit
     * @return person with edited values
     * @throws IllegalValueException if the prefix does not match either address, email or phone
     * field (a, e, p)
     */
    private Person getUpdatedPerson(ReadOnlyPerson target) throws IllegalValueException {
        Person person;
        switch (prefix) {
            case "a":
                person = new Person(target.getName(), target.getPhone(), target.getEmail(),
                        new Address(value, isPrivate), target.getTags());
                break;
            case "e":
                person = new Person(target.getName(), target.getPhone(), new Email(value, isPrivate),
                        target.getAddress(), target.getTags());
                break;
            case "p":
                person = new Person(target.getName(), new Phone(value, isPrivate), target.getEmail(),
                        target.getAddress(), target.getTags());
                break;
            default:
                throw new IllegalValueException("Invalid prefix");
        }
        return person;
    }
}
