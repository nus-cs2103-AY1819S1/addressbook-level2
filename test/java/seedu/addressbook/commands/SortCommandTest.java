package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.*;
import seedu.addressbook.util.TestUtil;


public class SortCommandTest {

    private AddressBook addressBook;
    private AddressBook sortedAddressBook;
    private AddressBook addressBookNotSorted;
    private AddressBook addressBookWithDiffSize;
    private AddressBook emptyAddressBook;


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

        addressBook = TestUtil.createAddressBook(johnDoe, janeDoe, davidGrant, samDoe);
        sortedAddressBook = TestUtil.createAddressBook(davidGrant, janeDoe, johnDoe, samDoe);
        addressBookNotSorted = TestUtil.createAddressBook(davidGrant, samDoe, janeDoe, johnDoe);
        addressBookWithDiffSize = TestUtil.createAddressBook(davidGrant, janeDoe, johnDoe);
        emptyAddressBook = TestUtil.createAddressBook();
    }


    @Test
    public void execute_sortList() {
        addressBook.sort();
        assertSortSuccessful(sortedAddressBook, addressBook);
        assertSortFailed(addressBookNotSorted, addressBook);
        assertSortFailed(addressBookWithDiffSize, addressBook);
        assertSortFailed(emptyAddressBook, addressBook);
    }


    /**
     * Asserts that the list is sorted successfully given a sorted list.
     *
     * The addressBook passed in will not be modified (no side effects).
     */
    private void assertSortSuccessful(AddressBook sortedAddressBook, AddressBook addressBook) {
        UniquePersonList expected = sortedAddressBook.getAllPersons();
        UniquePersonList actual = addressBook.getAllPersons();
        List<ReadOnlyPerson> expectedList = expected.immutableListView();
        List<ReadOnlyPerson> actualList = actual.immutableListView();
        assertEquals(actualList, expectedList);
    }


    /**
     * Asserts that the actual sorted list is different from the given list.
     *
     * The addressBook passed in will not be modified (no side effects).
     */
    private void assertSortFailed(AddressBook unsortedAddressBook, AddressBook addressBook) {
        UniquePersonList expected = unsortedAddressBook.getAllPersons();
        UniquePersonList actual = addressBook.getAllPersons();
        List<ReadOnlyPerson> expectedList = expected.immutableListView();
        List<ReadOnlyPerson> actualList = actual.immutableListView();
        assertNotEquals(actualList, expectedList);
    }
}