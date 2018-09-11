package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a Person's module in the address book.
 * No validation yet.
 */
public class Module {

    public static final String EXAMPLE = "CS2130";
    public static final String MESSAGE_MODULE_CONSTRAINTS = "Module names should be spaces, numbers or alphabetic characters";
    public final String fullModule;

    /**
     * Validates given name.
     *
     * @throws IllegalValueException if given name string is invalid.
     */
    public Module(String module) throws IllegalValueException {
        String trimmedModule = module.trim();
        if (!isValidModule(trimmedModule)) {
            throw new IllegalValueException(MESSAGE_MODULE_CONSTRAINTS);
        }
        this.fullModule = trimmedModule;
    }

    /**
     * TO COMPLETE!
     * Returns true if the given string is a valid module name.
     */
    public static boolean isValidModule(String test) {
        return true;
    }

    /**
     * Retrieves a listing of every word in the name, in order.
     */
    public List<String> getWordsInModule() {
        return Arrays.asList(fullModule.split("\\s+"));
    }

    @Override
    public String toString() {
        return fullModule;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && this.fullModule.equals(((Name) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullModule.hashCode();
    }

}
