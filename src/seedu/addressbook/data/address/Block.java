package seedu.addressbook.data.address;

import seedu.addressbook.data.exception.IllegalValueException;

public class Block {
    public static final String EXAMPLE = "123";
    private static final String MESSAGE_BLOCK_CONSTRAINTS = "Address blocks can be in any format";
    private static final String BLOCK_VALIDATION_REGEX = ".+";
    private final String addressBlock;

    /**
     * Validates given block.
     *
     * @throws IllegalValueException if given block string is invalid.
     */
    public Block(String block) throws IllegalValueException {
        String trimmedBlock = block.trim();
        if (!isValidBlock(trimmedBlock)) {
            throw new IllegalValueException(MESSAGE_BLOCK_CONSTRAINTS);
        }
        this.addressBlock = trimmedBlock;
    }

    /**
     * Returns true if the given string is a valid address block.
     */
    private static boolean isValidBlock(String test) {
        return test.matches(BLOCK_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return addressBlock;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Block // instanceof handles nulls
            && this.addressBlock.equals(((Block) other).addressBlock)); // state check
    }

    @Override
    public int hashCode() {
        return addressBlock.hashCode();
    }
}
