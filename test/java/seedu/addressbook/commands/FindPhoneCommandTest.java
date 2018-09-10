package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TypicalPersons;

public class FindPhoneCommandTest {

    private final AddressBook addressBook = new TypicalPersons().getTypicalAddressBook();
    private final TypicalPersons td = new TypicalPersons();

    @Test
    public void execute() throws IllegalValueException {
        //same phone number: matched
        assertFindCommandBehavior(new String[]{"91119111"}, Arrays.asList(td.amy));

        //part of number: not matched
        assertFindCommandBehavior(new String[]{"9111"}, Collections.emptyList());

        //multiple numbers: matched
        assertFindCommandBehavior(new String[]{"91119111", "92229222", "93339333", "94449444"},
                Arrays.asList(td.amy, td.bill, td.candy));

        //repeated numbers: matched
        assertFindCommandBehavior(new String[]{"91119111", "91119111"}, Arrays.asList(td.amy));
    }

    /**
     * Executes the find command for the given keywords and verifies
     * the result matches the persons in the expectedPersonList exactly.
     */
    private void assertFindCommandBehavior(String[] phoneNumbers, List<ReadOnlyPerson> expectedPersonList) {
        FindPhoneCommand command = createFindPhoneCommand(phoneNumbers);
        CommandResult result = command.execute();

        assertEquals(Command.getMessageForPersonListShownSummary(expectedPersonList), result.feedbackToUser);
    }

    private FindPhoneCommand createFindPhoneCommand(String[] phoneNumbers) {
        final Set<String> phoneNumberSet = new HashSet<>(Arrays.asList(phoneNumbers));
        FindPhoneCommand command = new FindPhoneCommand(phoneNumberSet);
        command.setData(addressBook, Collections.emptyList());
        return command;
    }

}
