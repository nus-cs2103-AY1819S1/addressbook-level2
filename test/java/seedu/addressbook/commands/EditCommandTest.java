package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import seedu.addressbook.data.person.UniquePersonList.DuplicatePersonException;
import seedu.addressbook.ui.TextUi;
import seedu.addressbook.util.TestUtil;
import seedu.addressbook.util.TypicalPersons;

public class EditCommandTest {
    private static final Set<String> EMPTY_STRING_SET = Collections.emptySet();

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
    public void editCommand_invalidName_throwsException() {
        final String[] invalidNames = { "", " ", "[]\\[;]" };
        for (String name : invalidNames) {
            assertConstructingInvalidEditCmdThrowsException(name, Phone.EXAMPLE, true, Email.EXAMPLE, false,
                    Address.EXAMPLE, true, EMPTY_STRING_SET, 1);
        }
    }

    @Test
    public void editCommand_invalidPhone_throwsException() {
        final String[] invalidNumbers = { "", " ", "1234-5678", "[]\\[;]", "abc", "a123", "+651234" };
        for (String number : invalidNumbers) {
            assertConstructingInvalidEditCmdThrowsException(Name.EXAMPLE, number, false, Email.EXAMPLE, true,
                    Address.EXAMPLE, false, EMPTY_STRING_SET, 2);
        }
    }

    @Test
    public void editCommand_invalidEmail_throwsException() {
        final String[] invalidEmails = { "", " ", "def.com", "@", "@def", "@def.com", "abc@",
                "@invalid@email", "invalid@email!", "!invalid@email" };
        for (String email : invalidEmails) {
            assertConstructingInvalidEditCmdThrowsException(Name.EXAMPLE, Phone.EXAMPLE, false, email, false,
                    Address.EXAMPLE, false, EMPTY_STRING_SET, 3);
        }
    }

    @Test
    public void editCommand_invalidAddress_throwsException() {
        final String[] invalidAddresses = { "", " " };
        for (String address : invalidAddresses) {
            assertConstructingInvalidEditCmdThrowsException(Name.EXAMPLE, Phone.EXAMPLE, true, Email.EXAMPLE,
                    true, address, true, EMPTY_STRING_SET, 4);
        }
    }

    @Test
    public void editCommand_invalidTags_throwsException() {
        final String[][] invalidTags = { { "" }, { " " }, { "'" }, { "[]\\[;]" }, { "validTag", "" },
                { "", " " } };
        for (String[] tags : invalidTags) {
            Set<String> tagsToAdd = new HashSet<>(Arrays.asList(tags));
            assertConstructingInvalidEditCmdThrowsException(Name.EXAMPLE, Phone.EXAMPLE, true, Email.EXAMPLE,
                    true, Address.EXAMPLE, false, tagsToAdd, 1);
        }
    }

    /**
     * Asserts that attempting to construct an add command with the supplied
     * invalid data throws an IllegalValueException
     */
    private void assertConstructingInvalidEditCmdThrowsException(String name, String phone,
                                                                boolean isPhonePrivate, String email, boolean isEmailPrivate, String address,
                                                                boolean isAddressPrivate, Set<String> tags,
                                                                int targetIndex) {
        try {
            new EditCommand(name, phone, isPhonePrivate, email, isEmailPrivate, address, isAddressPrivate,
                    tags, targetIndex);
        } catch (IllegalValueException e) {
            return;
        }
        String error = String.format(
                "An edit command was successfully constructed with invalid input: %s %s %s %s %s %s %s %s %d",
                name, phone, isPhonePrivate, email, isEmailPrivate, address, isAddressPrivate, tags, targetIndex);
        fail(error);
    }

    @Test
    public void editCommand_validData_correctlyConstructed() throws Exception {
        EditCommand command = new EditCommand(Name.EXAMPLE, Phone.EXAMPLE, true, Email.EXAMPLE, false,
                Address.EXAMPLE, true, EMPTY_STRING_SET, 1);
        ReadOnlyPerson p = command.getPerson();

        // TODO: add comparison of tags to person.equals and equality methods to
        // individual fields that compare privacy to simplify this
        assertEquals(Name.EXAMPLE, p.getName().fullName);
        assertEquals(Phone.EXAMPLE, p.getPhone().value);
        assertTrue(p.getPhone().isPrivate());
        assertEquals(Email.EXAMPLE, p.getEmail().value);
        assertFalse(p.getEmail().isPrivate());
        assertEquals(Address.EXAMPLE, p.getAddress().value);
        assertTrue(p.getAddress().isPrivate());
        boolean isTagListEmpty = !p.getTags().iterator().hasNext();
        assertTrue(isTagListEmpty);
    }

    @Test
    public void execute_noPersonDisplayed_returnsInvalidIndexMessage() {
        Person p = TestUtil.generateTestPerson();
        assertEditingFailsDueToInvalidIndex(p,1, addressBook, emptyDisplayList);
    }

    @Test
    public void execute_targetPersonInAddressBook_returnsPersonFoundMessage() {
        for (ReadOnlyPerson source: listWithEveryone) {
            Person samePerson = new Person(source);
            assertEditingFailsDueToSamePerson(samePerson, 1, addressBook, listWithEveryone);
        }
    }

    @Test
    public void execute_invalidIndex_returnsInvalidIndexMessage() {
        Person p = TestUtil.generateTestPerson();
        assertEditingFailsDueToInvalidIndex(p, 0, addressBook, listWithEveryone);
        assertEditingFailsDueToInvalidIndex(p, -1, addressBook, listWithEveryone);
        assertEditingFailsDueToInvalidIndex(p, listWithEveryone.size() + 1, addressBook, listWithEveryone);
    }

    @Test
    public void execute_validIndex_personIsEdited() throws DuplicatePersonException {
        TypicalPersons testPeople = new TypicalPersons();
        assertEditSuccessful(testPeople.getTypicalPersons()[0], 1, addressBook, listWithSurnameDoe);
        assertEditSuccessful(testPeople.getTypicalPersons()[1], listWithSurnameDoe.size(), addressBook, listWithSurnameDoe);

        int middleIndex = (listWithSurnameDoe.size() / 2) + 1;
        assertEditSuccessful(testPeople.getTypicalPersons()[2], middleIndex, addressBook, listWithSurnameDoe);
    }

    /**
     * Creates a new edit command.
     *
     * @param targetVisibleIndex of the person that we want to edit
     */
    private EditCommand createEditCommand(Person toReplace, int targetVisibleIndex, AddressBook addressBook,
                                                                  List<ReadOnlyPerson> displayList) {

        EditCommand command = new EditCommand(toReplace, targetVisibleIndex);
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

    /**
     * Asserts that the index is not valid for the given display list.
     */
    private void assertEditingFailsDueToInvalidIndex(Person toReplace, int invalidVisibleIndex, AddressBook addressBook,
                                                      List<ReadOnlyPerson> displayList) {

        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

        EditCommand command = createEditCommand(toReplace, invalidVisibleIndex, addressBook, displayList);
        assertCommandBehaviour(command, expectedMessage, addressBook, addressBook);
    }

    /**
     * Asserts that the person at the specified index cannot be edited, because that person
     * is in the address book.
     */
    private void assertEditingFailsDueToSamePerson(Person toReplace, int visibleIndex, AddressBook addressBook,
                                                     List<ReadOnlyPerson> displayList) {

        String expectedMessage = EditCommand.MESSAGE_DUPLICATE_PERSON;

        EditCommand command = createEditCommand(toReplace, visibleIndex, addressBook, displayList);
        assertCommandBehaviour(command, expectedMessage, addressBook, addressBook);
    }

    /**
     * Asserts that the person at the specified index can be successfully edited.
     *
     * The addressBook passed in will not be modified (no side effects).
     *
     * @throws PersonNotFoundException if the selected person is not in the address book
     */
    private void assertEditSuccessful(Person toReplace, int targetVisibleIndex, AddressBook addressBook,
                                          List<ReadOnlyPerson> displayList) throws DuplicatePersonException {

        ReadOnlyPerson targetPerson = displayList.get(targetVisibleIndex - TextUi.DISPLAYED_INDEX_OFFSET);

        AddressBook expectedAddressBook = TestUtil.clone(addressBook);
        expectedAddressBook.editPerson(toReplace, targetVisibleIndex - TextUi.DISPLAYED_INDEX_OFFSET);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, targetPerson) +
                String.format(EditCommand.MESSAGE_SUCCESS, toReplace);

        AddressBook actualAddressBook = TestUtil.clone(addressBook);

        EditCommand command = createEditCommand(toReplace, targetVisibleIndex, actualAddressBook, displayList);
        assertCommandBehaviour(command, expectedMessage, expectedAddressBook, actualAddressBook);
    }
}
