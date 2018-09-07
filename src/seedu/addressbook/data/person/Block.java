package seedu.addressbook.data.person;

/**
 * A block class representing the block of the addresss.
 */
public class Block {
    String block; // The block number

    /**
     * Constuct the block of the address.
     * @param block represents the string block of the address.
     */
    public Block(String block) {
        this.block = block;
    }

    @Override
    public String toString() {
        return block;
    }
}
