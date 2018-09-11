package seedu.addressbook.commands;

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

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SortCommandTest {
    private AddressBook emptyAddressBook;
    private AddressBook addressBook;

    private List<ReadOnlyPerson> emptyDisplayList;
    private List<ReadOnlyPerson> listWithEveryone;
    private List<ReadOnlyPerson> listSortedByEmail;
    private List<ReadOnlyPerson> listSortedByName;
    private List<ReadOnlyPerson> listSortedByPhone;


    @Before
    public void setUp() throws Exception {
        Person johnDoe = new Person(new Name("John Doe"), new Phone("61234567", false),
                new Email("john@doe.com", false), new Address("395C Ben Road", false), Collections.emptySet());
        Person janeDoe = new Person(new Name("Jane Doe"), new Phone("91234567", false),
                new Email("jane@doe.com", false), new Address("33G Ohm Road", false), Collections.emptySet());
        Person samDoe = new Person(new Name("Sam Doe"), new Phone("63345566", false),
                new Email("asam@doe.com", false), new Address("55G Abc Road", false), Collections.emptySet());
        Person davidGrant = new Person(new Name("David Grant"), new Phone("61121122", false),
                new Email("david@grant.com", false), new Address("44H Define Road", false),
                Collections.emptySet());

        emptyAddressBook = TestUtil.createAddressBook();
        addressBook = TestUtil.createAddressBook(johnDoe, janeDoe, davidGrant, samDoe);

        emptyDisplayList = TestUtil.createList();

        listWithEveryone = TestUtil.createList(johnDoe, janeDoe, davidGrant, samDoe);
        listSortedByEmail = TestUtil.createList(samDoe, davidGrant, janeDoe, johnDoe);
        listSortedByName = TestUtil.createList(davidGrant, janeDoe, johnDoe, samDoe);
        listSortedByPhone = TestUtil.createList(davidGrant, johnDoe, samDoe, janeDoe);
    }

    private SortCommand createSortCommand(String field, AddressBook addressBook) {
        SortCommand command = new SortCommand(field);
        command.setData(addressBook, addressBook.getAllPersons().immutableListView());
        return command;
    }

    @Test
    public void execute_emptyListCanSort() {
        SortCommand command = createSortCommand("name", emptyAddressBook);
        CommandResult result = command.execute();
        assertTrue(isSameList(command.addressBook, emptyDisplayList));
    }

    @Test
    public void execute_sortByEmail() {
        SortCommand command = createSortCommand("email", addressBook);
        CommandResult result = command.execute();

        assertTrue(isSameList(command.addressBook, listSortedByEmail));
    }

    @Test
    public void execute_sortByName() {
        SortCommand command = createSortCommand("name", addressBook);
        CommandResult result = command.execute();
        assertTrue(isSameList(command.addressBook, listSortedByName));
    }

    @Test
    public void execute_sortByPhone() {
        SortCommand command = createSortCommand("phone", addressBook);
        CommandResult result = command.execute();
        assertTrue(isSameList(command.addressBook, listSortedByPhone));
    }


    public boolean isSameList(AddressBook result, List<ReadOnlyPerson> expectedList) {
        List<ReadOnlyPerson> resultList = result.getAllPersons().immutableListView();
        if (resultList.size() != expectedList.size()) {
            return false;
        }
        for (int i = 0; i < expectedList.size(); i++) {
            if (!resultList.get(i).isSamePerson(expectedList.get(i))) {
                return false;
            }
        }
        return true;
    }
}
