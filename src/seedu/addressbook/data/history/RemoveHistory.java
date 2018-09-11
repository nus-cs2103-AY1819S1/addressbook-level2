package seedu.addressbook.data.history;

import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;

import java.time.LocalTime;

public class RemoveHistory extends History {
    private Name name;
    private Email email;

    public RemoveHistory(LocalTime time, Name name, Email email) {
        super(time);
        this.name = name;
        this.email = email;
    }

    @Override
    public String generateMessage() {
        return "Remove the Person " + name + " (" + email + ") at " + time.toString();
    }
}
