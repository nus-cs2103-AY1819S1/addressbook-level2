package seedu.addressbook.data.person;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {

    public int compare(Person p1, Person p2) {
        return p1.compareName(p2);
    }
}
