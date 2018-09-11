package seedu.addressbook.commands;

import org.junit.Assert;
import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TypicalPersons;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.function.BiConsumer;

import static org.junit.Assert.*;

public class SortCommandTest {

    private static final List<ReadOnlyPerson> EMPTY_PERSON_LIST = Collections.emptyList();

    private final TypicalPersons td = new TypicalPersons();
    private final AddressBook sortedAddressBook = td.getTypicalAddressBook();

    private final Person[] unsortedPersons = new Person[]{td.dan, td.amy, td.bill, td.candy};
    private final AddressBook unsortedAddressBook = td.getAddressBook(unsortedPersons);

    @Test
    public void sortTest() {
        // Before sorting
        List<ReadOnlyPerson> sortedList = sortedAddressBook.getAllPersons().immutableListView();
        this.<ReadOnlyPerson>assertArrayLists(sortedList, unsortedAddressBook.getAllPersons().immutableListView(),
                Assert::assertNotEquals);

        // Sorting
        SortCommand command = new SortCommand();
        command.setData(unsortedAddressBook, EMPTY_PERSON_LIST);
        command.execute();

        // After sorting
        this.<ReadOnlyPerson>assertArrayLists(sortedList, unsortedAddressBook.getAllPersons().immutableListView(),
                Assert::assertEquals);
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