package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.UniquePersonList;

public class SortCommandTest {

    private AddressBook unsortedAddressBook;
    private AddressBook sortedAddressBook;

    @Before
    public void setUp() throws Exception {
        Person johnDoe = new Person(new Name("John Doe"), new Phone("61234567", false),
                new Email("john@doe.com", false), new Address("395C Ben Road", false), Collections.emptySet());
        Person janeDoe = new Person(new Name("Jane Doe"), new Phone("91234567", false),
                new Email("jane@doe.com", false), new Address("33G Ohm Road", false), Collections.emptySet());
        Person davidGrant = new Person(new Name("David Grant"), new Phone("61121122", false),
                new Email("david@grant.com", false), new Address("44H Define Road", false),
                Collections.emptySet());

        unsortedAddressBook = new AddressBook(new UniquePersonList(johnDoe, davidGrant, janeDoe));
        sortedAddressBook = new AddressBook(new UniquePersonList(davidGrant, janeDoe, johnDoe));

    }

    @Test
    public void test() {
        SortCommand command = new SortCommand();
        command.setData(unsortedAddressBook, new ArrayList<>());
        assertFalse(unsortedAddressBook.equals(sortedAddressBook));
        assertEquals(command.execute().feedbackToUser, SortCommand.MESSAGE_SUCCESS);
        assertTrue(unsortedAddressBook.equals(sortedAddressBook));
    }
}
