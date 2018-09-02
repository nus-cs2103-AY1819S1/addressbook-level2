package seedu.addressbook.data.person;

/**
 * Street class that represent the street of the address.
 */
public class Street {
    String street;

    /**
     * Constructor to create street for address
     * @param street the street number of the address.
     */
    public Street(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return street;
    }
}
