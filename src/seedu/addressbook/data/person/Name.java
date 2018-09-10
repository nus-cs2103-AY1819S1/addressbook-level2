package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

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

    /**
     * Returns true if the other name is very similar to this name.
     * Two names are considered similar if either name is made up of a word
     * that is a substring of the other name.
     * The similarity of names is case-insensitive.
     */
    public boolean isSimilar(Name other) {
        if(other == null) {
            return false;
        }
        List<String> wordsInOtherName = other.getWordsInName().stream()
                .map(word -> word.toLowerCase())
                .collect(Collectors.toList());
        List<String> wordsInThisName = this.getWordsInName().stream()
                .map(word -> word.toLowerCase())
                .collect(Collectors.toList());

        for (String wordOther : wordsInOtherName) {
            for (String wordThis : wordsInThisName) {
                if(Name.isSubstring(wordOther, wordThis)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if either string @code s1 or @code s2 is a substring of the other.
     *
     * @param s1 A string.
     * @param s2 Another String.
     * @return {@code true} If either string is a substring of the other {@code false} Otherwise
     */
    private static boolean isSubstring(String s1, String s2) {
        if(s1.contains(s2) || s2.contains(s1)) {
            return true;
        } else {
            return false;
        }
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

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
