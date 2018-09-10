package seedu.addressbook.commands;

import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TypicalPersons;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.function.BiConsumer;

import static org.junit.Assert.*;

public class SortCommandTest {

    private final TypicalPersons td = new TypicalPersons();
    private final AddressBook sortedAddressBook = td.getTypicalAddressBook();

    private final Person[] unsortedPersons = new Person[]{td.dan, td.amy, td.bill, td.candy};
    private final AddressBook unsortedAddressBook = td.getAddressBook(unsortedPersons);

    @Test
    public void sortTest() {
        this.<ReadOnlyPerson>assertArrayLists(sortedAddressBook.getAllPersons().immutableListView(), unsortedAddressBook.getAllPersons().immutableListView(),
                (p1, p2) -> assertNotEquals(p1, p2));
    }

    private <E> void assertArrayLists(List<E> list1, List<E> list2, BiConsumer<E, E> assertion) {
        assert list1.size() == list2.size();
        ListIterator<E> iter1 = list1.listIterator();
        ListIterator<E> iter2 = list2.listIterator();
        while (iter1.hasNext()) {
            assertion.accept(iter1.next(), iter2.next());
        }
    }
}