package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import seedu.addressbook.ui.TextUi;
import seedu.addressbook.util.TestUtil;

public class DeleteAllCommandTest {

    private AddressBook emptyAddressBook;
    private AddressBook addressBook;
    private AddressBook addressBookOnlyDavid;

    private List<ReadOnlyPerson> emptyDisplayList;
    private List<ReadOnlyPerson> listWithEveryone;
    private List<ReadOnlyPerson> listWithSurnameDoe;

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
        addressBookOnlyDavid = TestUtil.createAddressBook(davidGrant);

        emptyDisplayList = TestUtil.createList();

        listWithEveryone = TestUtil.createList(johnDoe, janeDoe, davidGrant, samDoe);
        listWithSurnameDoe = TestUtil.createList(johnDoe, janeDoe, samDoe);
    }

    @Test
    public void execute_emptyAddressBook_returnsListIsEmptyMessage() {
        assertDeletionFailsDueToEmptyList(emptyAddressBook, emptyDisplayList);
    }

    @Test
    public void execute_addressBook_returnsListIsEmptyMessage() {
        assertDeletionFailsDueToEmptyList(addressBook, emptyDisplayList);
    }

    @Test
    public void execute_validDeleteAllAfterFindCommand() {
        assertDeletionSuccess(addressBook, listWithSurnameDoe, addressBookOnlyDavid);
    }

    @Test
    public void execute_validDeleteAllAfterListCommand() {
        assertDeletionSuccess(addressBook, listWithEveryone, emptyAddressBook);
    }

    /**
     * Creates a new deleteAll command.
     */
    private DeleteAllCommand createDeleteAllCommand(AddressBook addressBook,
                                                    List<ReadOnlyPerson> displayList) {

        DeleteAllCommand command = new DeleteAllCommand();
        command.setData(addressBook, displayList);

        return command;
    }

    /**
     * Executes the command, and checks that the execution was what we had expected.
     */
    private void assertCommandBehaviour(DeleteAllCommand deleteAllCommand, String expectedMessage,
                                        AddressBook expectedAddressBook, AddressBook actualAddressBook) {

        CommandResult result = deleteAllCommand.execute();

        assertEquals(expectedMessage, result.feedbackToUser);
        assertEquals(expectedAddressBook.getAllPersons(), actualAddressBook.getAllPersons());
    }

    /**
     * Asserts that nothing in the Address Book is deleted, because the display
     * List is currently empty.
     */
    private void assertDeletionFailsDueToEmptyList(AddressBook addressBook,
                                                   List<ReadOnlyPerson> displayList) {
        String expectedMessage = Messages.MESSAGE_NO_PERSON_IN_LIST;
        AddressBook expectedAddressBook = TestUtil.clone(addressBook);

        DeleteAllCommand command = createDeleteAllCommand(addressBook, displayList);
        assertCommandBehaviour(command, expectedMessage, expectedAddressBook, addressBook);

    }

    /**
     * Asserts that the persons in the display list are successfully deleted.
     *
     * The addressBook passed in will not be modified(no side effects).
     *
     * @param addressBook the address book to be passed in
     * @param displayList the simulated displayList
     * @param expectedAddressBook the expected AddressBook that should result from the command
     */
    private void assertDeletionSuccess(AddressBook addressBook,
                                       List<ReadOnlyPerson> displayList,
                                       AddressBook expectedAddressBook) {
        String expectedMessage = String.format(DeleteAllCommand.MESSAGE_DELETE_ALL_SUCCESS);

        AddressBook actualAddressBook = TestUtil.clone(addressBook);

        DeleteAllCommand command = createDeleteAllCommand(actualAddressBook, displayList);
        assertCommandBehaviour(command, expectedMessage, expectedAddressBook, actualAddressBook);

    }
}
