package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    private Block block;
    private Street street;
    private Unit unit;
    private PostalCode postalCode;


    public static final String EXAMPLE = "123, some street";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses cant be in any format";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";

    private boolean isPrivate;
    public String value;

    /**
     * Validates given address. Every field should be present
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        String[] addressDetails = address.split(", ");
//        if (!isValidAddress(addressDetails)) {
//            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
//        }
//        this.block = new Block(addressDetails[0]);
//        this.street = new Street(addressDetails[1]);
//        this.unit = new Unit(addressDetails[2]);
//        this.postalCode = new PostalCode(addressDetails[3]);

        this.isPrivate = isPrivate;
        //this.value = constructAddress();
        this.value = address;
    }

    /**
     * Constructs the address with its given block, street, unit, postal code.
     * @return the address in string format.
     */
    private String constructAddress() {
        return block.toString() + ", " + street.toString() + ", " + unit.toString() + ", " + postalCode.toString();
    }

    /**
     * Returns true if a given string is a valid person address. As long as we have 4 inputs for block, street, unit
     * and postal code, it is a valid address.
     */
    public static boolean isValidAddress(String[] test) {
        boolean result = false;
        if (test.length == 4) {
            result = true;
        }
        return result;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.value.equals(((Address) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
