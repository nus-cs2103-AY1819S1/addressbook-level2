package seedu.addressbook.commands;

import java.util.HashSet;
import java.util.Set;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.data.tag.Tag;

public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits information for contact. \n"
            + "Identified by the index number in the last shown person listing.\n"
            + "Parameters: INDEX n/NAME [p]p/PHONE [p]e/EMAIL [p]a/ADDRESS [t/TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 n/John Doe p/13579246 e/doejohn@gmail.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney";

    public static final String MESSAGE_SUCCESS = "Contact updated!";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Person toEdit;
    private final int index;
    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public EditCommand(String index,
                      String name,
                      String phone, boolean isPhonePrivate,
                      String email, boolean isEmailPrivate,
                      String address, boolean isAddressPrivate,
                      Set<String> tags) throws IllegalValueException {
        this.index = Integer.parseInt(index);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toEdit = new Person(
                new Name(name),
                new Phone(phone, isPhonePrivate),
                new Email(email, isEmailPrivate),
                new Address(address, isAddressPrivate),
                tagSet
        );
    }

    public EditCommand(String index, Person toEdit) {
        this.index = Integer.parseInt(index);
        this.toEdit = toEdit;
    }

    public ReadOnlyPerson getPerson() {
        return toEdit;
    }

    @Override
    public CommandResult execute() {
        try {
            addressBook.editPerson(Integer.toString(index), toEdit);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toEdit));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (UniquePersonList.DuplicatePersonException dpe) {
            return new CommandResult(MESSAGE_DUPLICATE_PERSON);
        }
    }

}
