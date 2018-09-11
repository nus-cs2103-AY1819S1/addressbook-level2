package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import seedu.addressbook.data.tag.Tag;

import java.util.HashSet;
import java.util.Set;

public class UpdateCommand extends Command{
    public static final String COMMAND_WORD = "update";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates details of the person " +
            "identified by the index number used in the last person listing.\n"
            + "Parameters: INDEX NAME [p]p/PHONE [p]e/EMAIL [p]a/ADDRESS  [t/TAG]...\n"
            + "Example: " + COMMAND_WORD +
            " 1 John Smith p/11111111 e/john@email.com a/21st Orchard Rd, #12-02 Garden Towers t/friends";
    public static final String MESSAGE_SUCCESS = "%1$s updated.";
    public static final String MESSAGE_DUPLICATE_DETAILS = "Details provided are the same.";
    private final Person updatePerson;

    public UpdateCommand(int targetVisibleIndex, String name,
                         String phone, boolean isPhonePrivate,
                         String email, boolean isEmailPrivate,
                         String address, boolean isAddressPrivate,
                         Set<String> tags) throws IllegalValueException {
        super(targetVisibleIndex);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.updatePerson = new Person(
                new Name(name),
                new Phone(phone, isPhonePrivate),
                new Email(email, isEmailPrivate),
                new Address(address, isAddressPrivate),
                tagSet
        );
    }

    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetPerson();
            addressBook.addPerson(updatePerson);
            addressBook.removePerson(target);
            return new CommandResult(String.format(MESSAGE_SUCCESS, updatePerson));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        } catch (UniquePersonList.DuplicatePersonException dpe) {
            return new CommandResult(MESSAGE_DUPLICATE_DETAILS);
        }
    }
    
}
