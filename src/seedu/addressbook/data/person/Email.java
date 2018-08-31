package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's email in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class Email extends Contact {

    public static final String EXAMPLE = "valid@e.mail";
    public static final String MESSAGE_EMAIL_CONSTRAINTS =
            "Person emails should be 2 alphanumeric/period strings separated by '@'";
    public static final String EMAIL_VALIDATION_REGEX = "[\\w\\.]+@[\\w\\.]+";

    public final String value;

    /**
     * Constructor with email address and private access.
     *
     * @param email Given email address .
     * @param isPrivate Person email address is private.
     * * @throws IllegalValueException if given email address string is invalid.
     */
    public Email(String email, boolean isPrivate) throws IllegalValueException {
        super(isPrivate);
        this.value = validateEmail(email);
    }

    /**
     * Validate email address.
     *
     * @param email Email Address of the person.
     * @return The trimmed email address.
     * @throws IllegalValueException if given email string is invalid.
     */
    private String validateEmail(String email) throws IllegalValueException {
        String trimmedEmail = email.trim();
        if (!isValidEmail(trimmedEmail)) {
            throw new IllegalValueException(MESSAGE_EMAIL_CONSTRAINTS);
        }
        return trimmedEmail;
    }

    /**
     * Returns true if the given string is a valid person email.
     */
    public static boolean isValidEmail(String test) {
        return test.matches(EMAIL_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Email // instanceof handles nulls
                && this.value.equals(((Email) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
