package seedu.addressbook.data.person;

/**
 * PostalCode class encapsulates the postal code field of the Address class.
 */
public class PostalCode {
    private final String postalCode;

    public PostalCode(int postalCode) {
        this.postalCode = isValid(postalCode) ? String.valueOf(postalCode) : null;
    }

    /**
     * Checks if the given postalCode is valid.
     * @param postalCode
     * @return
     */
    private boolean isValid(int postalCode) {
        return Integer.toString(postalCode).length() == 6;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
