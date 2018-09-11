package seedu.addressbook.commands;

import org.junit.Before;
import org.junit.Test;
import seedu.addressbook.common.Messages;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.ui.TextUi;
import seedu.addressbook.util.TestUtil;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class EditCommandTest {
    private AddressBook emptyAddressBook;
    private AddressBook addressBook;

    private List<ReadOnlyPerson> emptyDisplayList;
    private List<ReadOnlyPerson> listWithEveryone;
    private List<ReadOnlyPerson> listWithEdits;

    final private boolean PRIVATE = false;

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
        addressBook = TestUtil.createAddressBook(johnDoe, janeDoe, davidGrant);

        emptyDisplayList = TestUtil.createList();

        listWithEveryone = TestUtil.createList(johnDoe, janeDoe, davidGrant);
        listWithEdits = TestUtil.createList(samDoe);
    }


    @Test
    public void execute_noPersonDisplayed_returnsInvalidIndexMessage() throws IllegalValueException {
        assertEditFailsDueToInvalidIndex(100, addressBook, emptyDisplayList);
    }


    @Test
    public void execute_invalidIndex_returnsInvalidIndexMessage() throws IllegalValueException {
        assertEditFailsDueToInvalidIndex(0, addressBook, listWithEveryone);
        assertEditFailsDueToInvalidIndex(-1, addressBook, listWithEveryone);
        assertEditFailsDueToInvalidIndex(listWithEveryone.size() + 1, addressBook, listWithEveryone);
    }

    @Test
    public void execute_validIndex_personIsEdited() throws IllegalValueException {
        assertEditSuccessful(1, addressBook, listWithEdits);
        assertEditSuccessful(listWithEdits.size(), addressBook, listWithEdits);

        int middleIndex = (listWithEdits.size() / 2) + 1;
        assertEditSuccessful(middleIndex, addressBook, listWithEdits);
    }

    /**
     * Creates a new edit command.
     *
     * @param index of the person that we want to edit
     */
    private EditCommand createEditCommand(int index, ReadOnlyPerson person, AddressBook addressBook,
                                              List<ReadOnlyPerson> displayList) throws IllegalValueException {

        Set<String> tags = new HashSet<>();
        for (Tag tag : person.getTags()) {
            tags.add(tag.toString());
        }

        EditCommand command = new EditCommand(Integer.toString(index),
                person.getName().toString(),
                person.getPhone().toString(), PRIVATE,
                person.getEmail().toString(), PRIVATE,
                person.getAddress().toString(), PRIVATE,
                tags);
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
    private void assertEditFailsDueToInvalidIndex(int index,
                                                  AddressBook addressBook,
                                                  List<ReadOnlyPerson> displayList) throws IllegalValueException {

        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

        EditCommand command = createEditCommand(index, listWithEdits.get(0),
                 addressBook, displayList);
        assertCommandBehaviour(command, expectedMessage, addressBook, addressBook);
    }


    /**
     * Asserts that the person at the specified index can be successfully edited.
     *
     * The addressBook passed in will not be modified (no side effects).
     *
     */
    private void assertEditSuccessful(int index,
                                      AddressBook addressBook,
                                      List<ReadOnlyPerson> displayList) throws IllegalValueException {

        ReadOnlyPerson targetPerson = displayList.get(index - TextUi.DISPLAYED_INDEX_OFFSET);

        AddressBook expectedAddressBook = TestUtil.clone(addressBook);
        expectedAddressBook.editPerson(Integer.toString(index), (Person)targetPerson);
        String expectedMessage = String.format(EditCommand.MESSAGE_SUCCESS, targetPerson);

        AddressBook actualAddressBook = TestUtil.clone(addressBook);

        EditCommand command = createEditCommand(index, listWithEdits.get(0), actualAddressBook, displayList);
        assertCommandBehaviour(command, expectedMessage, expectedAddressBook, actualAddressBook);
    }
}