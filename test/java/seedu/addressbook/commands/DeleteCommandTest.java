package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import java.text.Format;
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
import seedu.addressbook.ui.Formatter;
import seedu.addressbook.ui.TextUi;
import seedu.addressbook.util.TestUtil;

public class DeleteCommandTest {

    private AddressBook emptyAddressBook;
    private AddressBook addressBook;

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

        emptyDisplayList = TestUtil.createList();

        listWithEveryone = TestUtil.createList(johnDoe, janeDoe, davidGrant, samDoe);
        listWithSurnameDoe = TestUtil.createList(johnDoe, janeDoe, samDoe);
    }

    @Test
    public void execute_emptyAddressBook_returnsPersonNotFoundMessage() {
        assertDeletionFailsDueToNoSuchPerson(emptyAddressBook, listWithEveryone, 1);
        assertDeletionFailsDueToNoSuchPerson(emptyAddressBook, listWithEveryone, new int[] {1, 2, 3, 4});
    }

    @Test
    public void execute_noPersonDisplayed_returnsInvalidIndexMessage() {
        assertDeletionFailsDueToInvalidIndex(addressBook, emptyDisplayList, 1);
        assertDeletionFailsDueToInvalidIndex(addressBook, emptyDisplayList, new int[] {400, 1, 23});
    }

    @Test
    public void execute_targetPersonNotInAddressBook_returnsPersonNotFoundMessage()
            throws IllegalValueException {
        Person notInAddressBookPerson1 = new Person(new Name("Not In Booka"), new Phone("63331444", false),
                new Email("notin@book.com", false), new Address("156D Grant Road", false), Collections.emptySet());
        Person notInAddressBookPerson2 = new Person(new Name("Not In Bookb"), new Phone("633314445", false),
                new Email("notina@book.com", false), new Address("156A Grant Road", false), Collections.emptySet());
        List<ReadOnlyPerson> listWithPersonNotInAddressBook = TestUtil.createList(notInAddressBookPerson1, notInAddressBookPerson2);

        assertDeletionFailsDueToNoSuchPerson(addressBook, listWithPersonNotInAddressBook, 1);
        assertDeletionFailsDueToNoSuchPerson(addressBook, listWithPersonNotInAddressBook, 1, 2);
    }

    @Test
    public void execute_invalidIndex_returnsInvalidIndexMessage() {
        assertDeletionFailsDueToInvalidIndex(addressBook, listWithEveryone, 0);
        assertDeletionFailsDueToInvalidIndex(addressBook, listWithEveryone, -1);
        assertDeletionFailsDueToInvalidIndex(addressBook, listWithEveryone, listWithEveryone.size() + 1);
        
        assertDeletionFailsDueToInvalidIndex(addressBook, listWithEveryone, new int[] {0, -1});
        assertDeletionFailsDueToInvalidIndex(addressBook, listWithEveryone, new int[] {0, -1, listWithEveryone.size() + 1});
    }

    @Test
    public void execute_validIndex_personIsDeleted() throws PersonNotFoundException {
        assertMultipleDeletionSuccessful(addressBook, listWithSurnameDoe, 1);
        assertMultipleDeletionSuccessful(addressBook, listWithSurnameDoe, listWithSurnameDoe.size());

        int middleIndex = (listWithSurnameDoe.size() / 2) + 1;
        assertMultipleDeletionSuccessful(addressBook, listWithSurnameDoe, middleIndex);
        
        // deleting multiple persons
        assertMultipleDeletionSuccessful(addressBook, listWithEveryone, new int[] {1,2,3});
        assertMultipleDeletionSuccessful(addressBook, listWithSurnameDoe, new int[] {1, 2, 3});
    }

    /**
     * Creates a new delete command.
     *
     * @param targetVisibleIndexes
     */
    private DeleteCommand createDeleteCommand(AddressBook addressBook,
                                              List<ReadOnlyPerson> displayList, int... targetVisibleIndexes) {

        DeleteCommand command = new DeleteCommand(targetVisibleIndexes);
        command.setData(addressBook, displayList);

        return command;
    }

    /**
     * Executes the command, and checks that the execution was what we had expected.
     */
    private void assertCommandBehaviour(DeleteCommand deleteCommand, String expectedMessage,
                                        AddressBook expectedAddressBook, AddressBook actualAddressBook) {

        CommandResult result = deleteCommand.execute();

        assertEquals(expectedMessage, result.feedbackToUser);
        assertEquals(expectedAddressBook.getAllPersons(), actualAddressBook.getAllPersons());
    }

    /**
     * Asserts that the index is not valid for the given display list.
     */
    private void assertDeletionFailsDueToInvalidIndex(AddressBook addressBook, List<ReadOnlyPerson> displayList, 
                                                      int... invalidVisibleIndexes) {
        String expectedMessage = "";
        for (int invalidVisibleIndex : invalidVisibleIndexes) {
            String resultFromDelete = String.format(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX_WITH_CONTEXT, invalidVisibleIndex);
            expectedMessage = Formatter.formatConcatResultMsg(expectedMessage, resultFromDelete);
        }

        DeleteCommand command = createDeleteCommand(addressBook, displayList, invalidVisibleIndexes);
        assertCommandBehaviour(command, expectedMessage, addressBook, addressBook);
    }

    /**
     * Asserts that the person at the specified index cannot be deleted, because that person
     * is not in the address book.
     */
    private void assertDeletionFailsDueToNoSuchPerson(AddressBook addressBook, List<ReadOnlyPerson> displayList,
                                                      int... visibleIndexes) {
        String expectedMessage = "";
        for (int visibleIndex : visibleIndexes) {
            String resultFromDelete = String.format(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK_WITH_CONTEXT, visibleIndex);
            expectedMessage = Formatter.formatConcatResultMsg(expectedMessage, resultFromDelete);
        }

        DeleteCommand command = createDeleteCommand(addressBook, displayList, visibleIndexes);
        assertCommandBehaviour(command, expectedMessage, addressBook, addressBook);
    }

    /**
     * Asserts that the person at the specified index can be successfully deleted.
     *
     * The addressBook passed in will not be modified (no side effects).
     *
     * @throws PersonNotFoundException if the selected person is not in the address book
     */
    private void assertMultipleDeletionSuccessful(AddressBook addressBook, List<ReadOnlyPerson> displayList, 
                                                  int... targetVisibleIndexes) throws PersonNotFoundException {
        AddressBook expectedAddressBook = TestUtil.clone(addressBook);
        String expectedMessage = "";
        for (int targetVisibleIndex : targetVisibleIndexes) {
            ReadOnlyPerson targetPerson = displayList.get(targetVisibleIndex - TextUi.DISPLAYED_INDEX_OFFSET);
            expectedAddressBook.removePerson(targetPerson);
            String resultFromDelete = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, targetPerson);
            expectedMessage = Formatter.formatConcatResultMsg(expectedMessage, resultFromDelete);
        }
        
        AddressBook actualAddressBook = TestUtil.clone(addressBook);

        DeleteCommand command = createDeleteCommand(actualAddressBook, displayList, targetVisibleIndexes);
        assertCommandBehaviour(command, expectedMessage, expectedAddressBook, actualAddressBook);
    }
    
    
}
