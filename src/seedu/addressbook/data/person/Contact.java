package seedu.addressbook.data.person;

public class Contact {

    protected String value;
    protected boolean isPrivate;

    public String getValue() { return value; }

    public boolean isPrivate() {
        return isPrivate;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
