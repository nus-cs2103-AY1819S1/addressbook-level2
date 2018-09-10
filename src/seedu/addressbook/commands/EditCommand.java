package seedu.addressbook.commands;

import java.util.HashSet;
import java.util.Set;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;

/**
 * Edits the information of a person identified by number index.
 */
public class EditCommand extends Command{

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":  Edits the information of a person identified by number index. "
            + "Contact details can be marked private by prepending 'p' to the prefix.\n"
            + "Parameters: INDEX [p]p/PHONE [p]e/EMAIL [p]a/ADDRESS  [t/TAG]...\n"
            + "Example: " + COMMAND_WORD
            + " 1 p/98765432 e/johnd@gmail.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney";

    public static final String MESSAGE_SUCCESS = "Edited person: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private Person toEdit;
    private Name name;
    private final Phone phone;
    private final Email email;
    private final Address address;
    private final Set<Tag> tagSet;

    public EditCommand(int targetVisibleIndex,
                       String phone, boolean isPhonePrivate,
                       String email, boolean isEmailPrivate,
                       String address, boolean isAddressPrivate,
                       Set<String> tags) throws IllegalValueException {
        super(targetVisibleIndex);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.phone = new Phone(phone, isPhonePrivate);
        this.email = new Email(email, isEmailPrivate);
        this.address = new Address(address, isAddressPrivate);
        this.tagSet = tagSet;
    }

    public Person getPerson() {return toEdit;}
    public Phone getPhone() {return phone;}
    public Email getEmail() {return email;}
    public Address getAddress() {return address;}
    public Set<Tag> getTags() {return tagSet;}

    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetPerson();
            name = target.getName();
            addressBook.removePerson(target);
            toEdit = new Person(name,phone,email,address,tagSet);
            addressBook.addPerson(toEdit);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toEdit));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        } catch (UniquePersonList.DuplicatePersonException dpe) {
            return new CommandResult(MESSAGE_DUPLICATE_PERSON);
        }
    }
}
