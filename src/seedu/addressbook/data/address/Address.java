package seedu.addressbook.data.address;

/**
 * Represents a Person's address in the address book.
 */
public class Address {

    private Block block;
    private Street street;
    private Unit unit;
    private PostalCode postalCode;
    private boolean isPrivate;

    public Address(Block block, Street street, Unit unit, PostalCode postalCode, boolean isPrivate) {
        this.block = block;
        this.street = street;
        this.unit = unit;
        this.postalCode = postalCode;
        this.isPrivate = isPrivate;
    }

    public Block getBlock() {
        return block;
    }

    public Street getStreet() {
        return street;
    }

    public Unit getUnit() {
        return unit;
    }

    public PostalCode getPostalCode() {
        return postalCode;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        final String separator = ", ";
        builder.append(getBlock())
            .append(separator);
        builder.append(getStreet())
            .append(separator);
        builder.append(getUnit())
            .append(separator);
        builder.append(getPostalCode());
        return builder.toString();
    }

    public boolean isPrivate() {
        return isPrivate;
    }


}
