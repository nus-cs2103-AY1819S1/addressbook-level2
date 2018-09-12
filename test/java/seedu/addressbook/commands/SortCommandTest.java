package seedu.addressbook.commands;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import seedu.addressbook.data.person.*;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.util.TestUtil;

import java.util.Collections;
import java.util.List;

public class SortCommandTest {

    private static final List<ReadOnlyPerson> EMPTY_PERSON_LIST = Collections.emptyList();

    private AddressBook addressBookUnsorted;
    private AddressBook addressBookSorted;

    @Before
    public void setUp() throws Exception {
        Person johnDoe = new Person(new Name("John Doe"), new Phone("61234567", false),
                new Email("john@doe.com", false), new Address("395C Ben Road", false), Collections.emptySet());
        Person janeDoe = new Person(new Name("Jane Doe"), new Phone("91234567", false),
                new Email("jane@doe.com", false), new Address("33G Ohm Road", false), Collections.emptySet());
        Person samDoe = new Person(new Name("Sam Doe"), new Phone("63345566", false),
                new Email("sam@doe.com", false), new Address("55G Abc Road", false), Collections.emptySet());
        Person davidGrant = new Person(new Name("David Grant"), new Phone("61121122", false),
                new Email("david@grant.com", false), new Address("44H Define Road", false),
                Collections.emptySet());

        addressBookUnsorted = TestUtil.createAddressBook(johnDoe, janeDoe, davidGrant, samDoe);
        addressBookSorted = TestUtil.createAddressBook(davidGrant, janeDoe, johnDoe, samDoe);
    }

    @Test
    public void execute_anyAddressBook_shouldSort() throws Exception {
        boolean isSorted = true;

        // should not match before sorting
        List<ReadOnlyPerson> listSorted = addressBookSorted.getAllPersons().immutableListView();
        List<ReadOnlyPerson> listUnsorted = addressBookUnsorted.getAllPersons().immutableListView();
        for (int i = 0; i < listSorted.size(); i++) {
            if (!listSorted.get(i).equals(listUnsorted.get(i))) {
                isSorted = false;
                break;
            }
        }
        assertFalse(isSorted);

        // should match after sorting
        isSorted = true;
        addressBookUnsorted.sort();
        listSorted = addressBookSorted.getAllPersons().immutableListView();
        listUnsorted = addressBookUnsorted.getAllPersons().immutableListView();
        for (int i = 0; i < listSorted.size(); i++) {
            if (!listSorted.get(i).equals(listUnsorted.get(i))) {
                isSorted = false;
                break;
            }
        }
        assertTrue(isSorted);
    }

}
