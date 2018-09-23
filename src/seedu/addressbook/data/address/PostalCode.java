package seedu.addressbook.data.address;

import seedu.addressbook.data.exception.IllegalValueException;

public class PostalCode {
    public static final String EXAMPLE = "123456";
    private static final String MESSAGE_POSTAL_CODE_CONSTRAINTS = "Address postal codes can be in any format";
    private static final String POSTAL_CODE_VALIDATION_REGEX = ".+";
    private final String addressPostalCode;

    /**
     * Validates given postal code.
     *
     * @throws IllegalValueException if given postal code string is invalid.
     */
    public PostalCode(String postalCode) throws IllegalValueException {
        String trimmedPostalCode = postalCode.trim();
        if (!isValidPostalCode(trimmedPostalCode)) {
            throw new IllegalValueException(MESSAGE_POSTAL_CODE_CONSTRAINTS);
        }
        this.addressPostalCode = trimmedPostalCode;
    }

    /**
     * Returns true if the given string is a valid address block.
     */
    private static boolean isValidPostalCode(String test) {
        return test.matches(POSTAL_CODE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return addressPostalCode;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Block // instanceof handles nulls
            && this.addressPostalCode.equals(((PostalCode) other).addressPostalCode)); // state check
    }

    @Override
    public int hashCode() {
        return addressPostalCode.hashCode();
    }
}
