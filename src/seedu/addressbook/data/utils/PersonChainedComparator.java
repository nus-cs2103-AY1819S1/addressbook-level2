package seedu.addressbook.data.utils;

import seedu.addressbook.data.person.Person;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PersonChainedComparator implements Comparator<Person> {
    private List<Comparator<Person>> listComparators;

    @SafeVarargs
    public PersonChainedComparator(Comparator<Person>... comparators) {
        this.listComparators = Arrays.asList(comparators);
    }

    @Override
    public int compare(Person p1, Person p2) {
        for (Comparator<Person> comparator : listComparators) {
            int result = comparator.compare(p1, p2);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }
}
