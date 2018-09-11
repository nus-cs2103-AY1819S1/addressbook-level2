package seedu.addressbook.data.history;

import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;

import java.time.LocalTime;

public class AddHistory extends History {

    private Name name;
    private Email email;

    public AddHistory(LocalTime time, Name name, Email email) {
        super(time);
        this.name = name;
        this.email = email;
    }

    @Override
    public String generateMessage() {
        return "Add new Person " + name + " (" + email + ") at " + time.toString();
    }
}
