package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TestUtil;

public class SortCommandTest {
    private AddressBook addressBook;

    private List<ReadOnlyPerson> commandSorted;
    private List<ReadOnlyPerson> manualSorted;

    @Before
    public void setUp() throws Exception {
        Person johnDoe = new Person(new Name("John Doe"), new Phone("61234567", false),
            new Email("john@doe.com", false), new Address("395C Ben Road", false), Collections
            .emptySet());
        Person janeDoe = new Person(new Name("Jane Doe"), new Phone("91234567", false),
            new Email("jane@doe.com", false), new Address("33G Ohm Road", false), Collections.emptySet());
        Person samDoe = new Person(new Name("Sam Doe"), new Phone("63345566", false),
            new Email("sam@doe.com", false), new Address("55G Abc Road", false), Collections.emptySet());
        Person davidGrant = new Person(new Name("David Grant"), new Phone("61121122", false),
            new Email("david@grant.com", false), new Address("44H Define Road", false),
            Collections.emptySet());

        addressBook = TestUtil.createAddressBook(johnDoe, janeDoe, davidGrant, samDoe);
        addressBook.sort();
        commandSorted = addressBook.getAllPersons().immutableListView();

        manualSorted = TestUtil.createList(johnDoe, janeDoe, davidGrant, samDoe);

        Collections.sort(manualSorted, new Comparator<ReadOnlyPerson>(){
            public int compare(ReadOnlyPerson p1, ReadOnlyPerson p2) {
                return p1.getName().toString().compareTo(p2.getName().toString());
            }
        });
    }


    /**
     * Executes the sort command and verifies the result matches the persons in
     * the expectedPersonList exactly.
     */
    @Test
    public void assertSortedCommandBehavior() {
        assertEquals(commandSorted, manualSorted);
    }
}
