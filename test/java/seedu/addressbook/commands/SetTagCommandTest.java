package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.ui.TextUi;
import seedu.addressbook.util.TestUtil;

public class SetTagCommandTest {

    private AddressBook emptyAddressBook;
    private AddressBook addressBook;

    private List<ReadOnlyPerson> emptyDisplayList;
    private List<ReadOnlyPerson> listWithEveryone;
    private List<ReadOnlyPerson> listWithSurnameDoe;

    private Set<String> tags;

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

        tags = new HashSet<>();
        tags.add("small");
        tags.add("meduim");
        tags.add("large");
    }

    @Test
    public void execute_emptyAddressBook_returnsPersonNotFoundMessage() {
        assertSetTagFailsDueToNoSuchPerson(1, tags, emptyAddressBook, listWithEveryone);
    }

    @Test
    public void execute_noPersonDisplayed_returnsInvalidIndexMessage() {
        assertSetTagFailsDueToInvalidIndex(1, tags, addressBook, emptyDisplayList);
    }

    @Test
    public void execute_targetPersonNotInAddressBook_returnsPersonNotFoundMessage()
            throws IllegalValueException {
        Person notInAddressBookPerson = new Person(new Name("Not In Book"), new Phone("63331444", false),
                new Email("notin@book.com", false), new Address("156D Grant Road", false), Collections.emptySet());
        List<ReadOnlyPerson> listWithPersonNotInAddressBook = TestUtil.createList(notInAddressBookPerson);

        assertSetTagFailsDueToNoSuchPerson(1, tags, addressBook, listWithPersonNotInAddressBook);
    }

    @Test
    public void execute_invalidIndex_returnsInvalidIndexMessage() {
        assertSetTagFailsDueToInvalidIndex(0, tags, addressBook, listWithEveryone);
        assertSetTagFailsDueToInvalidIndex(-1, tags, addressBook, listWithEveryone);
        assertSetTagFailsDueToInvalidIndex(listWithEveryone.size() + 1, tags, addressBook, listWithEveryone);
    }

    @Test
    public void execute_validIndex_personTagsSet() throws PersonNotFoundException {
        assertSetTagSuccessful(1, tags, addressBook, listWithSurnameDoe);
        assertSetTagSuccessful(listWithSurnameDoe.size(), tags, addressBook, listWithSurnameDoe);

        int middleIndex = (listWithSurnameDoe.size() / 2) + 1;
        assertSetTagSuccessful(middleIndex, tags, addressBook, listWithSurnameDoe);
    }

    /**
     * Creates a new settags command.
     *
     * @param targetVisibleIndex of the person that we want to delete
     */
    private SetTagCommand createSetTagCommand(int targetVisibleIndex, Set<String> tags, AddressBook addressBook,
                                              List<ReadOnlyPerson> displayList) throws IllegalValueException {

        SetTagCommand command = new SetTagCommand(targetVisibleIndex, tags);
        command.setData(addressBook, displayList);

        return command;
    }

    /**
     * Executes the command, and checks that the execution was what we had expected.
     */
    private void assertCommandBehaviour(SetTagCommand setTagCommand, String expectedMessage,
                                        AddressBook expectedAddressBook, AddressBook actualAddressBook) {

        CommandResult result = setTagCommand.execute();

        assertEquals(expectedMessage, result.feedbackToUser);
        assertEquals(expectedAddressBook.getAllPersons(), actualAddressBook.getAllPersons());
    }

    /**
     * Asserts that the index is not valid for the given display list.
     */
    private void assertSetTagFailsDueToInvalidIndex(int invalidVisibleIndex, Set<String> tags, AddressBook addressBook,
                                                      List<ReadOnlyPerson> displayList) {

        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

        SetTagCommand command = null;
        try {
            command = createSetTagCommand(invalidVisibleIndex, tags, addressBook, displayList);
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }
        assertCommandBehaviour(command, expectedMessage, addressBook, addressBook);
    }

    /**
     * Asserts that the person at the specified index cannot be deleted, because that person
     * is not in the address book.
     */
    private void assertSetTagFailsDueToNoSuchPerson(int visibleIndex, Set<String> tags, AddressBook addressBook,
                                                      List<ReadOnlyPerson> displayList) {

        String expectedMessage = Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK;
        SetTagCommand command = null;
        try {
            command = createSetTagCommand(visibleIndex, tags, addressBook, displayList);
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }
        assertCommandBehaviour(command, expectedMessage, addressBook, addressBook);
    }

    /**
     * Asserts that the person at the specified index can be successfully deleted.
     *
     * The addressBook passed in will not be modified (no side effects).
     *
     * @throws PersonNotFoundException if the selected person is not in the address book
     */
    private void assertSetTagSuccessful(int targetVisibleIndex, Set<String> tags, AddressBook addressBook,
                                          List<ReadOnlyPerson> displayList) {

        ReadOnlyPerson targetPerson = displayList.get(targetVisibleIndex - TextUi.DISPLAYED_INDEX_OFFSET);

        AddressBook expectedAddressBook = TestUtil.clone(addressBook);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            try {
                tagSet.add(new Tag(tagName));
            } catch (IllegalValueException e) {
                e.printStackTrace();
            }
        }
        expectedAddressBook.setTags(targetPerson, tagSet);
        String expectedMessage = String.format(SetTagCommand.MESSAGE_VIEW_NEW_TAGS, targetPerson.getAsTextHidePrivate());

        AddressBook actualAddressBook = TestUtil.clone(addressBook);

        SetTagCommand command = null;
        try {
            command = createSetTagCommand(targetVisibleIndex, tags, actualAddressBook, displayList);
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }
        assertCommandBehaviour(command, expectedMessage, expectedAddressBook, actualAddressBook);
    }
}