package seedu.addressbook.data.address;

import seedu.addressbook.data.exception.IllegalValueException;

public class Street {

    public static final String EXAMPLE = "some street";
    private static final String MESSAGE_STREET_CONSTRAINTS = "Address streets can be in any format";
    private static final String STREET_VALIDATION_REGEX = ".+";
    private final String addressStreet;

    /**
     * Validates given street.
     *
     * @throws IllegalValueException if given street string is invalid.
     */
    public Street(String street) throws IllegalValueException {
        String trimmedStreet = street.trim();
        if (!isValidStreet(trimmedStreet)) {
            throw new IllegalValueException(MESSAGE_STREET_CONSTRAINTS);
        }
        this.addressStreet = trimmedStreet;
    }

    /**
     * Returns true if the given string is a valid address street.
     */
    private static boolean isValidStreet(String test) {
        return test.matches(STREET_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return addressStreet;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Street // instanceof handles nulls
            && this.addressStreet.equals(((Street) other).addressStreet)); // state check
    }

    @Override
    public int hashCode() {
        return addressStreet.hashCode();
    }
}
