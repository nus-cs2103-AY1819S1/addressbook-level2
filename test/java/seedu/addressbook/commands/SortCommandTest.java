package seedu.addressbook.commands;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;
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
    private AddressBook sortedAddressBook;
    private AddressBook emptyAddressBook;
    private List<ReadOnlyPerson> listWithEveryone;
    private List<ReadOnlyPerson> emptyList;


    @Before
    public void setUp() throws Exception {
        Person adamDoe = new Person(new Name("Adam Doe"), new Phone("61234567", false),
                new Email("adam@doe.com", false), new Address("395C Ben Road", false),
                Collections.emptySet());
        Person bellDoe = new Person(new Name("Bell Doe"), new Phone("91234567", false),
                new Email("bell@doe.com", false), new Address("33G Ohm Road", false),
                Collections.emptySet());
        Person samDoe = new Person(new Name("Sam Doe"), new Phone("63345566", false),
                new Email("sam@doe.com", false), new Address("55G Abc Road", false), Collections.emptySet());
        Person davidGrant = new Person(new Name("David Grant"), new Phone("61121122", false),
                new Email("david@grant.com", false), new Address("44H Define Road", false),
                Collections.emptySet());

        addressBook = TestUtil.createAddressBook(samDoe, bellDoe, davidGrant, adamDoe);
        sortedAddressBook = TestUtil.createAddressBook(adamDoe, bellDoe, davidGrant, samDoe);

        emptyAddressBook = TestUtil.createAddressBook();
        emptyList = TestUtil.createList();
        listWithEveryone = TestUtil.createList(adamDoe, bellDoe, davidGrant, samDoe);
    }

    @Test
    public void execute_addressBook_sorted() {
        // sort on empty address book
        assertSortSuccessful(emptyAddressBook, emptyList, emptyAddressBook);

        // sort on address book
        assertSortSuccessful(addressBook, listWithEveryone, sortedAddressBook);
    }

    /**
     * Creates a new sort command.
     *
     * @return
     */
    private SortCommand createSortCommand(AddressBook addressBook, List<ReadOnlyPerson> displayList) {
        SortCommand command = new SortCommand();
        command.setData(addressBook, displayList);

        return command;
    }

    /**
     * Asserts that the address book is successfully sorted.
     *
     * The addressBook passed in will not be modified (no side effects).
     */
    private void assertSortSuccessful(AddressBook addressBook, List<ReadOnlyPerson> displayList,
            AddressBook expectedAddressBook) {
        String expectedMessage = SortCommand.MESSAGE_SORTED;

        AddressBook actualAddressBook = TestUtil.clone(addressBook);

        SortCommand command = createSortCommand(actualAddressBook, displayList);
        assertCommandBehaviour(command, expectedMessage, expectedAddressBook, actualAddressBook);
    }

    /**
     * Executes the command, and checks that the execution was what we had expected.
     */
    private void assertCommandBehaviour(SortCommand sortCommand, String expectedMessage,
            AddressBook expectedAddressBook, AddressBook actualAddressBook) {

        CommandResult result = sortCommand.execute();

        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedAddressBook.getAllPersons(), actualAddressBook.getAllPersons());
    }
}