package seedu.addressbook.commands;


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

import java.util.HashSet;
import java.util.Set;

/**
 * Update a person in the address book
 */
public class UpdateCommand extends Command {

    public static final String COMMAND_WORD = "update";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Updates a person identified by the index number used in the last person listing.\n"
            + "Parameters: INDEX [[p]p/PHONE] [[p]e/EMAIL] [[p]a/ADDRESS]  [t/TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 p/98765432 e/johnd@gmail.com t/friends t/owesMoney";

    public static final String MESSAGE_UPDATE_PERSON_SUCCESS = "Updated Person: %1$s";
    
    private Person toUpdate;
    private ReadOnlyPerson original;
    private final boolean isUpdatingPhone;
    private final boolean isUpdatingEmail;
    private final boolean isUpdatingAddress;
    private final Phone phone;
    private final Email email;
    private final Address address;
    private final HashSet<Tag> tagSet;

    
    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public UpdateCommand(String targetIndex,
                      boolean isUpdatingPhone,
                      String phone, boolean isPhonePrivate,
                      boolean isUpdatingEmail,
                      String email, boolean isEmailPrivate,
                      boolean isUpdatingAddress,
                      String address, boolean isAddressPrivate,
                      Set<String> tags) throws IllegalValueException {
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        setTargetIndex(Integer.parseInt(targetIndex));
        this.isUpdatingPhone = isUpdatingPhone;
        this.isUpdatingEmail = isUpdatingEmail;
        this.isUpdatingAddress = isUpdatingAddress;
        this.phone = isUpdatingPhone ? new Phone(phone, isPhonePrivate) : null;
        this.email = isUpdatingEmail ? new Email(email, isEmailPrivate) : null;
        this.address = isUpdatingAddress ? new Address(address, isAddressPrivate) : null;
        this.tagSet = (HashSet<Tag>) tagSet;
    }

    /**
     * set the original and updated person
     */
    public void setUpdatedPerson() {
            this.original = getTargetPerson();
            Phone newPhone = isUpdatingPhone ? phone : original.getPhone();
            Email newEmail = isUpdatingEmail ? email : original.getEmail();
            Address newAddress = isUpdatingAddress ? address : original.getAddress();
            Set<Tag> newTagSet = tagSet.isEmpty() ? original.getTags() : tagSet;
            this.toUpdate = new Person(original.getName(), newPhone, newEmail, newAddress, newTagSet);
    }
    
    @Override
    public CommandResult execute() {
        try {
            setUpdatedPerson();
            addressBook.updatePerson(original, toUpdate);
            return new CommandResult(String.format(MESSAGE_UPDATE_PERSON_SUCCESS, toUpdate));

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (UniquePersonList.PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        }
    }


}
