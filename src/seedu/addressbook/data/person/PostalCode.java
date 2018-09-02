package seedu.addressbook.data.person;

/**
 * A postal code class that represents the postal code to the address.
 */
public class PostalCode {
    String postalCode;

    /**
     * Constructor to create the postal code of the address.
     * @param postalCode the postal code number of the address.
     */
    public PostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return postalCode;
    }
}
