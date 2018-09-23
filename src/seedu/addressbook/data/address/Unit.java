package seedu.addressbook.data.address;

import seedu.addressbook.data.exception.IllegalValueException;

public class Unit {

    public static final String EXAMPLE = "#02-25";
    private static final String MESSAGE_UNIT_CONSTRAINTS = "Address units can be in any format";
    private static final String UNIT_VALIDATION_REGEX = ".+";
    private final String addressUnit;

    /**
     * Validates given unit.
     *
     * @throws IllegalValueException if given unit string is invalid.
     */
    public Unit(String unit) throws IllegalValueException {
        String trimmedUnit = unit.trim();
        if (!isValidUnit(trimmedUnit)) {
            throw new IllegalValueException(MESSAGE_UNIT_CONSTRAINTS);
        }
        this.addressUnit = trimmedUnit;
    }

    /**
     * Returns true if the given string is a valid address unit.
     */
    private static boolean isValidUnit(String test) {
        return test.matches(UNIT_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return addressUnit;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Unit // instanceof handles nulls
            && this.addressUnit.equals(((Unit) other).addressUnit)); // state check
    }

    @Override
    public int hashCode() {
        return addressUnit.hashCode();
    }
}
