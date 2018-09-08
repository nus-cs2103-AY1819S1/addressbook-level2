package seedu.addressbook.commands;

import org.junit.Before;
import org.junit.Test;
import seedu.addressbook.common.Messages;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.ui.TextUi;
import seedu.addressbook.util.TestUtil;

import java.util.*;

import static org.junit.Assert.*;

public class UpdateCommandTest {
    private static final List<ReadOnlyPerson> EMPTY_PERSON_LIST = Collections.emptyList();
    private static final Set<String> EMPTY_STRING_SET = Collections.emptySet();

    private AddressBook emptyAddressBook;
    private AddressBook addressBook;

    private List<ReadOnlyPerson> emptyDisplayList;
    private List<ReadOnlyPerson> listWithEveryone;

    @Before
    public void setUp() throws Exception {
        Person johnDoe = new Person(new Name("John Doe"), new Phone("61234567", false),
                new Email("john@doe.com", false), new Address("395C Ben Road", false), Collections.emptySet());
        Person janeDoe = new Person(new Name("Jane Doe"), new Phone("91234567", false),
                new Email("jane@doe.com", false), new Address("33G Ohm Road", false), Collections.emptySet());
        Person samDoe = new Person(new Name("Sam Doe"), new Phone("63345566", false),
                new Email("sam@doe.com", false), new Address("55G Abc Road", false), Collections.emptySet());

        emptyAddressBook = TestUtil.createAddressBook();
        addressBook = TestUtil.createAddressBook(johnDoe, janeDoe, samDoe);

        emptyDisplayList = TestUtil.createList();

        listWithEveryone = TestUtil.createList(johnDoe, janeDoe, samDoe);
    }

    @Test
    public void execute_emptyAddreslsBook_returnsPersonNotFoundMessage() throws IllegalValueException {
        assertUpdatingFailsDueToNoSuchPerson(1, createFillerPerson(), emptyAddressBook, listWithEveryone);
    }

    @Test
    public void execute_noPersonDisplayed_returnsInvalidIndexMessage() throws IllegalValueException{
        assertUpdatingFailsDueToInvalidIndex(1, createFillerPerson(), addressBook, emptyDisplayList);
    }

    @Test
    public void execute_targetPersonNotInAddressBook_returnsPersonNotFoundMessage() throws IllegalValueException {
        Person notInAddressBookPerson = new Person(new Name("Not In Book"), new Phone("63331444", false),
                new Email("notin@book.com", false), new Address("156D Grant Road", false), Collections.emptySet());
        List<ReadOnlyPerson> listWithPersonNotInAddressBook = TestUtil.createList(notInAddressBookPerson);

        assertUpdatingFailsDueToNoSuchPerson(1, createFillerPerson(), addressBook, listWithPersonNotInAddressBook);
    }

    @Test
    public void execute_invalidIndex_returnsInvalidIndexMessage() throws IllegalValueException {
        assertUpdatingFailsDueToInvalidIndex(0, createFillerPerson(), addressBook, listWithEveryone);
        assertUpdatingFailsDueToInvalidIndex(-1, createFillerPerson(), addressBook, listWithEveryone);
        assertUpdatingFailsDueToInvalidIndex(listWithEveryone.size() + 1, createFillerPerson(), addressBook, listWithEveryone);
    }

    @Test
    public void execute_validIndex_personIsUpdated() throws UniquePersonList.PersonNotFoundException, IllegalValueException {
        assertUpdatingSuccessful(1, createFillerPerson(), addressBook, listWithEveryone);
        assertUpdatingSuccessful(listWithEveryone.size(), createFillerPerson(), addressBook, listWithEveryone);

        int middleIndex = (listWithEveryone.size() / 2) + 1;
        assertUpdatingSuccessful(middleIndex, createFillerPerson(), addressBook, listWithEveryone);
    }


    private Person createFillerPerson() throws IllegalValueException {
        return new Person(new Name("Mr Filler"), new Phone("5555", false),
                new Email("filler@person.com", false), new Address("156D Grant Road", false), Collections.emptySet());
    }

    /**
     * Creates a new update command.
     *
     * @param targetVisibleIndex of the person that we want to update
     */
    private UpdateCommand createUpdateCommand(int targetVisibleIndex, Person toUpdate, AddressBook addressBook,
                                              List<ReadOnlyPerson> displayList) {

        UpdateCommand command = new UpdateCommand(targetVisibleIndex, toUpdate);
        command.setData(addressBook, displayList);

        return command;
    }

    /**
     * Executes the command, and checks that the execution was what we had expected.
     */
    private void assertCommandBehaviour(UpdateCommand updateCommand, String expectedMessage,
                                        AddressBook expectedAddressBook, AddressBook actualAddressBook) {

        CommandResult result = updateCommand.execute();

        assertEquals(expectedMessage, result.feedbackToUser);
        assertEquals(expectedAddressBook.getAllPersons(), actualAddressBook.getAllPersons());
    }

    /**
     * Asserts that the index is not valid for the given display list.
     */
    private void assertUpdatingFailsDueToInvalidIndex(int invalidVisibleIndex, Person toUpdate, AddressBook addressBook,
                                                      List<ReadOnlyPerson> displayList) {

        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

        UpdateCommand command = createUpdateCommand(invalidVisibleIndex, toUpdate, addressBook, displayList);
        assertCommandBehaviour(command, expectedMessage, addressBook, addressBook);
    }


    /**
     * Asserts that the person at the specified index cannot be updated, because that person
     * is not in the address book.
     */
    private void assertUpdatingFailsDueToNoSuchPerson(int visibleIndex, Person toUpdate, AddressBook addressBook,
                                                      List<ReadOnlyPerson> displayList) {
        String expectedMessage = Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK;

        UpdateCommand command = createUpdateCommand(visibleIndex, toUpdate, addressBook, displayList);
        assertCommandBehaviour(command, expectedMessage, addressBook, addressBook);
    }

    /**
     * Asserts that the person at the specified index can be successfully updated.
     *
     * The addressBook passed in will not be modified (no side effects).
     *
     * @throws UniquePersonList.DuplicatePersonException if the person being updated to is already in the address book
     * @throws UniquePersonList.PersonNotFoundException if the selected person is not in the address book
     */
    private void assertUpdatingSuccessful(int targetVisibleIndex, Person toUpdate, AddressBook addressBook,
                                          List<ReadOnlyPerson> displayList) throws UniquePersonList.DuplicatePersonException, UniquePersonList.PersonNotFoundException {

        ReadOnlyPerson targetPerson = displayList.get(targetVisibleIndex - TextUi.DISPLAYED_INDEX_OFFSET);

        AddressBook expectedAddressBook = TestUtil.clone(addressBook);
        expectedAddressBook.updatePerson(targetPerson, toUpdate);
        String expectedMessage = String.format(UpdateCommand.MESSAGE_SUCCESS, toUpdate);

        AddressBook actualAddressBook = TestUtil.clone(addressBook);

        UpdateCommand command = createUpdateCommand(targetVisibleIndex, toUpdate, actualAddressBook, displayList);
        assertCommandBehaviour(command, expectedMessage, expectedAddressBook, actualAddressBook);
    }
}
