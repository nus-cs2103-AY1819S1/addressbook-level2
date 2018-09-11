package seedu.addressbook.data.person;

/**
 * Represents notes about a Person in the address book.
 */
public class Notes {

    public static final String EXAMPLE = "Hardworking student.";
    public String value;
    private boolean isPrivate;

    /**
     *  Initialises value
     */
    public Notes(String notes, boolean isPrivate){
        this.isPrivate = isPrivate;
        String trimmedNotes = notes.trim();
        this.value = trimmedNotes;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Notes // instanceof handles nulls
                && this.value.equals(((Notes) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
