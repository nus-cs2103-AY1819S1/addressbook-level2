package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && this.fullName.equals(((Name) other).fullName)); // state check
    }

    /**
     * Returns true if the other name is very similar to this name.
     * Two names are considered similar if ...
     */
    public boolean isSimilar(Name other) throws NullPointerException {
        boolean flag = false;
        if (other == null) {
            throw new NullPointerException();
        }
        if (this.fullName.equalsIgnoreCase(other.fullName)){
            flag = true;
        }

        if (this.fullName.toLowerCase().contains(other.fullName.toLowerCase())){
            flag = true;
        }

        char[] cs1 = this.fullName.toCharArray();
        char[] cs2 = other.fullName.toCharArray();
        Arrays.sort(cs1);
        Arrays.sort(cs2);
        if(Arrays.equals(cs1, cs2)){
            flag = true;
        }

        return flag;
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

    @Override
    public String getPrintableString() {
        return "Name: " + fullName;
    }

}
