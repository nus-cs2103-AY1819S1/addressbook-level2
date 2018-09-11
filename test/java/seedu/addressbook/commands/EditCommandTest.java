package seedu.addressbook.commands;

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
import seedu.addressbook.util.TestUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EditCommandTest {

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
        Person davidGrant = new Person(new Name("David Grant"), new Phone("61121122", false),
                new Email("david@grant.com", false), new Address("44H Define Road", false),
                Collections.emptySet());

        emptyAddressBook = TestUtil.createAddressBook();
        addressBook = TestUtil.createAddressBook(johnDoe, janeDoe, samDoe, davidGrant);

        emptyDisplayList = TestUtil.createList();
        listWithEveryone = TestUtil.createList(johnDoe, janeDoe, samDoe, davidGrant);
    }

    @Test
    public void execute_editCommand_invalidPrefix_throwsException() {
        for (int i = 0; i < 255; i++) {
            if ((char)i == 'a' || (char)i == 'e' || (char)i == 'p')
                continue;

            assertEditFailsDueToInvalidPrefix(1, addressBook, listWithEveryone, Character.toString((char)i),
                    "12345678asfaghj", false);
        }
    }

    @Test
    public void execute_editCommand_targetPersonNotInAddressBook_returnsPersonNotFoundMessage()
            throws IllegalValueException {
        Person notInAddressBookPerson = new Person(new Name("Not In Book"), new Phone("63331444", false),
                new Email("notin@book.com", false), new Address("156D Grant Road", false),
                Collections.emptySet());
        List<ReadOnlyPerson> listWithPersonNotInAddressBook = TestUtil.createList(notInAddressBookPerson);

        assertEditFailsDueToNoSuchPerson(1, addressBook, listWithPersonNotInAddressBook, "p", "12345678", false);
    }

    @Test
    public void execute_editCommand_wrongFormatForValue_returnsInvalidFormatMessage() {
        String phoneExpectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, Phone.MESSAGE_PHONE_CONSTRAINTS);
        String emailExpectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, Email.MESSAGE_EMAIL_CONSTRAINTS);

        EditCommand command = createEditCommand(1, addressBook, listWithEveryone, "p", "abcd", false);
        assertCommandBehaviour(command, phoneExpectedMessage, addressBook, addressBook);

        command = createEditCommand(1, addressBook, listWithEveryone, "e", "123", false);
        assertCommandBehaviour(command, emailExpectedMessage, addressBook, addressBook);
    }

    private void assertEditFailsDueToInvalidPrefix(int visibleIndex, AddressBook addressBook, List<ReadOnlyPerson> displayList,
                                                   String prefix, String value, boolean isPrivate) {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, "Invalid prefix");

        EditCommand command = createEditCommand(visibleIndex, addressBook, displayList, prefix, value, isPrivate);
        assertCommandBehaviour(command, expectedMessage, addressBook, addressBook);
    }

    private void assertEditFailsDueToNoSuchPerson(int visibleIndex, AddressBook addressBook, List<ReadOnlyPerson> displayList,
                                                  String prefix, String value, boolean isPrivate) {
        String expectedMessage = Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK;

        EditCommand command = createEditCommand(visibleIndex, addressBook, displayList, prefix, value, isPrivate);
        assertCommandBehaviour(command, expectedMessage, addressBook, addressBook);
    }

    private EditCommand createEditCommand(int visibleIndex, AddressBook addressBook, List<ReadOnlyPerson> displayList,
                                          String prefix, String value, boolean isPrivate) {
        EditCommand command = new EditCommand(visibleIndex, prefix, value, isPrivate);
        command.setData(addressBook, displayList);

        return command;
    }

    private void assertCommandBehaviour(EditCommand command, String expectedMessage, AddressBook expectedAddressBook,
                                         AddressBook actualAddressBook) {
        CommandResult result = command.execute();

        assertEquals(expectedMessage, result.feedbackToUser);
        assertEquals(expectedAddressBook.getAllPersons(), actualAddressBook.getAllPersons());
    }
}
