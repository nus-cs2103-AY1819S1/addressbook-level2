package seedu.addressbook.commands;

import org.junit.Before;
import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.util.TestUtil;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ContainCommandTest {

    private AddressBook addressbook;
    private AddressBook emptyaddressbook;
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

        emptyaddressbook = TestUtil.createAddressBook();
        addressbook = TestUtil.createAddressBook(johnDoe, janeDoe, davidGrant, samDoe);

        listWithEveryone = TestUtil.createList(johnDoe, janeDoe, davidGrant, samDoe);
    }

    @Test
    public void execute_validAddressBook_personContained() throws IllegalValueException{
        assertContainSuccessful("John Doe", addressbook, true);
    }

    @Test
    public void execute_validAddressBook_personNotContained() throws IllegalValueException {
        assertContainSuccessful("Lili Pan", addressbook, false);
    }
    private void assertContainSuccessful(String name, AddressBook addressbook, boolean contained) throws IllegalValueException {
        String expectedMessage = ContainCommand.MESSAGE_UNIQUE;
        if (contained) {
            expectedMessage= ContainCommand.MESSAGE_DUPLICATE;
        }
        ContainCommand command = createContainCommand(name, addressbook, emptyDisplayList);
        assertCommandBehaviour(command, expectedMessage);
    }

    private ContainCommand createContainCommand(String name, AddressBook addressbook,
                                                List<ReadOnlyPerson> DisplayList) throws IllegalValueException {
        ContainCommand command = new ContainCommand(name);
        command.setData(addressbook, DisplayList);
        return command;
    }

    private void assertCommandBehaviour(ContainCommand containCommand, String expectedMessage) {

        CommandResult result = containCommand.execute();

        assertEquals(expectedMessage, result.feedbackToUser);
    }
}
