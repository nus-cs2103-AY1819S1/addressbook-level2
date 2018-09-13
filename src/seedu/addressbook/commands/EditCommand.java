package seedu.addressbook.commands;

import java.util.HashSet;
import java.util.Set;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.tag.Tag;

/**
 * Edits a person in the address book.
 * Adds a person if no such person exists.
 */
public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edit a person in the address book. "
            + "Contact details can be marked private by prepending 'p' to the prefix.\n"
            + "Parameters: NAME [p]p/PHONE [p]e/EMAIL [p]a/ADDRESS  [t/TAG]...\n"
            + "Example: " + COMMAND_WORD
            + " John Doe p/98765432 e/johnd@gmail.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney";

    public static final String MESSAGE_SUCCESS = "Person edited: %1$s";
    public static final String MESSAGE_ALTERNATIVE = "No such person. "
            + "A new person will be added\n"
            + "Person added: %1$s";

    private final Person toEdit;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public EditCommand(String name,
                      String phone, boolean isPhonePrivate,
                      String email, boolean isEmailPrivate,
                      String address, boolean isAddressPrivate,
                      Set<String> tags) throws IllegalValueException {
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

    /**
     * Alternative constructor using final {@code Person} value
     *
     * @param toEdit the person to edit
     */
    public EditCommand(Person toEdit) {
        this.toEdit = toEdit;
    }

    @Override
    public CommandResult execute() {
        int initialSize = addressBook.size();
        addressBook.editPerson(toEdit);
        int finalSize = addressBook.size();

        if (hasPersonBeenEdited(initialSize, finalSize)) {
            return new CommandResult(String.format(MESSAGE_SUCCESS, toEdit));
        } else {
            return new CommandResult(String.format(MESSAGE_ALTERNATIVE, toEdit));
        }
    }

    /**
     * Checks to see if a person in the address book has been edited.
     * This is done by comparing the initial and final size of the
     * address book.
     *
     * @param initialSize initial size before {@code editPerson(Person toEdit)}.
     * @param finalSize final size after {@code editPerson(Person toEdit)}.
     * @return true if there has been an edit, false if there has not been an edit
     * and a person has been added instead.
     */
    private boolean hasPersonBeenEdited(int initialSize, int finalSize) {
        return finalSize - initialSize == 0;
    }
}
