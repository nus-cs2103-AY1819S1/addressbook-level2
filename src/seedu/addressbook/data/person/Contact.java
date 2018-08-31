package seedu.addressbook.data.person;

/**
 * Represents a Person's contact in the address book.
 */
public class Contact {
    
    /** To check if contact is hidden */
    private boolean isPrivate;

    /**
     * Constructor with isPrivateContact.
     *
     * @param isPrivate To check if the contact is private.
     */
    public Contact(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }
    
    /**
     * Returns on whether contact is private. 
     */
    public boolean isPrivate() {
        return isPrivate;
    }
}
