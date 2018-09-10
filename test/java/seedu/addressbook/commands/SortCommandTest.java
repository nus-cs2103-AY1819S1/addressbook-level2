package seedu.addressbook.commands;

import org.junit.Before;
import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.*;
import seedu.addressbook.util.TestUtil;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class SortCommandTest {
    private static final List<ReadOnlyPerson> EMPTY_PERSON_LIST = Collections.emptyList();

    private AddressBook unsortedAddressBook;
    private AddressBook sortedAddressBook;
    private AddressBook finalAddressBook;

    private List<ReadOnlyPerson> beforeList;
    private List<ReadOnlyPerson> afterList;

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

        unsortedAddressBook = TestUtil.createAddressBook(johnDoe, janeDoe, davidGrant, samDoe);
        sortedAddressBook = TestUtil.createAddressBook(davidGrant, janeDoe, johnDoe, samDoe);
        finalAddressBook = TestUtil.createAddressBook(davidGrant, janeDoe, johnDoe, samDoe);



        beforeList = TestUtil.createList(johnDoe, janeDoe, davidGrant, samDoe);
        afterList = TestUtil.createList(davidGrant, johnDoe, janeDoe, samDoe);
    }

    @Test
    public void testing() {
        // Start with a unsorted address book. 
        assertSortCommandBehaviour(unsortedAddressBook, finalAddressBook);
        // Start with an already sorted address book.
        assertSortCommandBehaviour(sortedAddressBook, finalAddressBook);


    }

    /**
     * Executes the sort command for the given unsorted address book
     * the result matches the persons in the unsorted address book exactly.
     */
    private void assertSortCommandBehaviour(AddressBook unsorted ,AddressBook sorted){
        SortCommand command = new SortCommand();
        command.setData(unsorted, EMPTY_PERSON_LIST);
        CommandResult result = command.execute();
        assertEquals(unsorted.getAllPersons(), sorted.getAllPersons());
        assertEquals("Sorted!", result.feedbackToUser);
    }

}
