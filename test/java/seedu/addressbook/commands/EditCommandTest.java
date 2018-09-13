package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;
import static seedu.addressbook.commands.EditCommand.MESSAGE_SUCCESS;
import static seedu.addressbook.commands.EditCommand.MESSAGE_ALTERNATIVE;

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

public class EditCommandTest {

    private AddressBook actualAddressBook;
    private List<ReadOnlyPerson> listWithEveryone;

    private Person personInAddressBook;
    private Person editedPersonToBeInAddressBook;
    private Person personNotInAddressBook;

    @Before
    public void setUp() throws Exception {
        personInAddressBook = new Person(new Name("John Doe"), new Phone("61234567", false),
                new Email("john@doe.com", false), new Address("395C Ben Road",
                false), Collections.emptySet());

        editedPersonToBeInAddressBook = new Person(new Name("John Doe"), new Phone("61234567", false),
                new Email("johnny@doe.com", false), new Address("395C Ben Road", false), Collections.emptySet());

        personNotInAddressBook = new Person(new Name("Jane Doe"), new Phone("91234567", false),
                new Email("jane@doe.com", false), new Address("33G Ohm Road",
                false), Collections.emptySet());

        actualAddressBook = TestUtil.createAddressBook(personInAddressBook);
        listWithEveryone = TestUtil.createList(personInAddressBook);
    }

    @Test
    public void execute_personInAddressBook_editPersonInAddressBook() {
        String expectedMessage = MESSAGE_SUCCESS;
        expectedMessage = String.format(expectedMessage,
                "John Doe Phone: 61234567 Email: johnny@doe.com Address: 395C Ben Road Tags: ");

        AddressBook expectedAddressBook = TestUtil.createAddressBook(editedPersonToBeInAddressBook);

        EditCommand command = createEditCommand(actualAddressBook, listWithEveryone, editedPersonToBeInAddressBook);
        assertCommandBehaviour(command, expectedMessage, expectedAddressBook, actualAddressBook);
    }

    @Test
    public void execute_personNotInAddressBook_addPersonIntoAddressBook() throws Exception {
        String expectedMessage = MESSAGE_ALTERNATIVE;
        expectedMessage = String.format(expectedMessage,
                "Jane Doe Phone: 91234567 Email: jane@doe.com Address: 33G Ohm Road Tags: ");

        AddressBook expectedAddressBook = TestUtil.createAddressBook(personInAddressBook, personNotInAddressBook);

        EditCommand command = createEditCommand(actualAddressBook, listWithEveryone, personNotInAddressBook);
        assertCommandBehaviour(command, expectedMessage, expectedAddressBook, actualAddressBook);

    }

    private EditCommand createEditCommand(AddressBook addressBook, List<ReadOnlyPerson> displayList, Person toEdit) {
        EditCommand command = new EditCommand(toEdit);
        command.setData(addressBook, displayList);
        return command;
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
