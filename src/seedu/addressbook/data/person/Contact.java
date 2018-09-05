package seedu.addressbook.data.person;
import seedu.addressbook.common.Printable;

public abstract class Contact implements Printable {
    public String value;
    protected boolean isPrivate;

    @Override
    public String toString() {
        return value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
