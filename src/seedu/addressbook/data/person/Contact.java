package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

public abstract class Contact {

    public final String value;
    private boolean isPrivate;

    Contact(String contact, boolean isPrivate) {
        this.isPrivate = isPrivate;
        this.value = contact.trim();
    }

    public abstract boolean isValid(String test);

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Phone // instanceof handles nulls
            && this.value.equals(((Phone) other).value)); // state check
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
