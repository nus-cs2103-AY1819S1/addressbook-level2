package seedu.addressbook.data.utils;

import seedu.addressbook.data.person.Person;
import java.util.Comparator;

public class PersonNameComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return o1.getName().fullName.compareTo(o2.getName().fullName);
    }
}
