package seedu.addressbook.data.person;

import java.util.Arrays;
import java.util.List;

import seedu.addressbook.common.Printable;
import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name implements Printable {

    public static final String EXAMPLE = "John Doe";
    public static final String MESSAGE_NAME_CONSTRAINTS = "Person names should be spaces or alphabetic characters";
    public static final String NAME_VALIDATION_REGEX = "[\\p{Alpha} ]+";
    public final String fullName;

    /**
     * Validates given name.
     *
     * @throws IllegalValueException if given name string is invalid.
     */
    public Name(String name) throws IllegalValueException {
        String trimmedName = name.trim();
        if (!isValidName(trimmedName)) {
            throw new IllegalValueException(MESSAGE_NAME_CONSTRAINTS);
        }
        this.fullName = trimmedName;
    }

    /**
     * Returns true if the given string is a valid person name.
     */
    public static boolean isValidName(String test) {
        return test.matches(NAME_VALIDATION_REGEX);
    }

    /**
     * Retrieves a listing of every word in the name, in order.
     */
    public List<String> getWordsInName() {
        return Arrays.asList(fullName.split("\\s+"));
    }

    @Override
    public String toString() {
        return fullName;
    }

    /**
     * Returns whether a name is relatively similar to fullName
     */
    public boolean isSimilar(Name other) {
        if (!(other instanceof Name)) {
            return false;
        }
        String[] splittedOther = other.fullName.split("\\s+");
        String[] splittedThis = this.fullName.split("\\s+");
        for (String o : splittedOther) {
            for (String t : splittedThis) {
                if (o.equals(t)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns a printable string representation of name.
     */
    @Override
    public String getPrintableString() {
        return "Name: " + this.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && this.fullName.equals(((Name) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
