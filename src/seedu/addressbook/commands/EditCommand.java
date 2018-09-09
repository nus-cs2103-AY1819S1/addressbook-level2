package seedu.addressbook.commands;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.tag.Tag;

import java.util.HashSet;
import java.util.Set;

public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":  a person identified by the index used in "
            + "the last find/list call.\n"
            + "Contact details can be marked private by prepending 'p' to the prefix.\n"
            + "Parameters: NAME [p]p/PHONE [p]e/EMAIL [p]a/ADDRESS  [t/TAG]...\n"
            + "Example: " + COMMAND_WORD
            + " John Doe p/98765432 e/johnd@gmail.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney";
    public static final String MESSAGE_SUCCESS = "Person edited: %1$s";

    private final Integer toRemoveIndex;
    private final Person toAdd;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public EditCommand(Integer oldIndex, String name,
                      String phone, boolean isPhonePrivate,
                      String email, boolean isEmailPrivate,
                      String address, boolean isAddressPrivate,
                      Set<String> tags) throws IllegalValueException {
        toRemoveIndex = oldIndex;
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toAdd = new Person(
                new Name(name),
                new Phone(phone, isPhonePrivate),
                new Email(email, isEmailPrivate),
                new Address(address, isAddressPrivate),
                tagSet
        );
    }

    @Override
    public CommandResult execute() {
        CommandResult removeResult = new DeleteCommand(toRemoveIndex).execute();
        if (!removeResult.isSuccessful) {
            return removeResult;
        } else {
            CommandResult addResult = new AddCommand(toAdd).execute();
            if (addResult.isSuccessful) {
                return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
            }
            return addResult;
        }
    }
}
