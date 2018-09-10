package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;

import org.junit.Before;

import seedu.addressbook.common.Messages;
import seedu.addressbook.util.TestUtil;

public class EditCommandTest {
    private static final List<ReadOnlyPerson> EMPTY_PERSON_LIST = Collections.emptyList();
    private static final Set<String> EMPTY_STRING_SET = Collections.emptySet();

    private AddressBook emptyAddressBook;
    private AddressBook addressBook;

    private List<ReadOnlyPerson> emptyDisplayList;
    private List<ReadOnlyPerson> listWithEveryone;
    private List<ReadOnlyPerson> listWithSurnameDoe;

    private Person johnDoe;
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

        emptyAddressBook = TestUtil.createAddressBook();
        addressBook = TestUtil.createAddressBook(johnDoe, janeDoe, davidGrant, samDoe);

        emptyDisplayList = TestUtil.createList();

        listWithEveryone = TestUtil.createList(johnDoe, janeDoe, davidGrant, samDoe);
        listWithSurnameDoe = TestUtil.createList(johnDoe, janeDoe, samDoe);
    }

    @Test
    public void editCommand_validData_correctlyConstructed() throws Exception {
        EditCommand command = new EditCommand( 1, Phone.EXAMPLE, true, Email.EXAMPLE, false,
                Address.EXAMPLE, true, EMPTY_STRING_SET);

        assertEquals(Phone.EXAMPLE, command.getPhone().value);
        assertTrue(command.getPhone().isPrivate());
        assertEquals(Email.EXAMPLE, command.getEmail().value);
        assertFalse(command.getEmail().isPrivate());
        assertEquals(Address.EXAMPLE, command.getAddress().value);
        assertTrue(command.getAddress().isPrivate());
        boolean isTagListEmpty = !command.getTags().iterator().hasNext();
        assertTrue(isTagListEmpty);
    }

    @Test
    public void editCommand_personExists_correctlyEdited() throws Exception {
        EditCommand editCommand = new EditCommand( 1, "987654321", true, Email.EXAMPLE, false,
                Address.EXAMPLE, true, EMPTY_STRING_SET);

        editCommand.setData(addressBook, listWithEveryone);
        CommandResult editResult = editCommand.execute();
        UniquePersonList people = addressBook.getAllPersons();

        assertTrue(people.contains(editCommand.getPerson()));
        assertFalse(people.contains(johnDoe));
        assertEquals(4, people.immutableListView().size());
        assertFalse(editResult.getRelevantPersons().isPresent());
        assertEquals(String.format(EditCommand.MESSAGE_SUCCESS, editCommand.getPerson()), editResult.feedbackToUser);
    }

    @Test
    public void editCommand_personNotExists_failedEdited()  throws Exception {
        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        EditCommand command = new EditCommand( 5, "987654321", true, Email.EXAMPLE, false,
                Address.EXAMPLE, true, EMPTY_STRING_SET);
        command.setData(addressBook, listWithEveryone);
        assertCommandBehaviour(command, expectedMessage, addressBook, addressBook);
    }

    /**
     * Executes the command, and checks that the execution was what we had expected.
     */
    private void assertCommandBehaviour(EditCommand editCommand, String expectedMessage,
                                        AddressBook expectedAddressBook, AddressBook actualAddressBook) {

        CommandResult result = editCommand.execute();

        assertEquals(expectedMessage, result.feedbackToUser);
        assertEquals(expectedAddressBook.getAllPersons(), actualAddressBook.getAllPersons());
    }
}
