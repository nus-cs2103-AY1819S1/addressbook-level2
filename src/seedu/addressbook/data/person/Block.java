package seedu.addressbook.data.person;

/**
 * Block number of Address class.
 */
public class Block {
    private final String block;

    /**
     * Constructor for Block
     * @param block
     */
    public Block(int block) {
        this.block = String.valueOf(block);
    }

    public String getBlock() {
        return block;
    }
}
