package seedu.addressbook.data.person;

/**
 * A unit class that represents the unit of the address.
 */
public class Unit {
    private String unit; // The unit number

    /**
     * Constructs unit number of address.
     * @param unit the unit number.
     */
    public Unit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return unit;
    }
}
