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

public class FindPCommandTest {

    private final AddressBook addressBook = new TypicalPersons().getTypicalAddressBook();
    private final TypicalPersons td = new TypicalPersons();

    @Test
    public void execute() throws IllegalValueException {
        //same phone number: matched
        assertFindPCommandBehavior("91119111", Arrays.asList(td.amy));

        //different phone number: not matched
        assertFindPCommandBehavior("91234567", Collections.emptyList());
    }

    /**
     * Executes the findP command for given phone number and verifies
     * the result matches the persons in the expectedPersonList exactly.
     */
    private void assertFindPCommandBehavior(String phoneNo, List<ReadOnlyPerson> expectedPersonList) {
        FindPCommand command = createFindPCommand(phoneNo);
        CommandResult result = command.execute();

        assertEquals(Command.getMessageForPersonListShownSummary(expectedPersonList), result.feedbackToUser);
    }

    private FindPCommand createFindPCommand(String phoneNo) {
        FindPCommand command = new FindPCommand(phoneNo);
        command.setData(addressBook, Collections.emptyList());
        return command;
    }

}
