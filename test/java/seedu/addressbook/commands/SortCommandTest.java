package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TestUtil;
import seedu.addressbook.util.TypicalPersons;

public class SortCommandTest {
    private TypicalPersons td = new TypicalPersons();

    private AddressBook typicalAddressBook = td.getTypicalAddressBook();
    private List<ReadOnlyPerson> listWithTypicalPersons = Arrays.asList(td.bill, td.amy, td.candy, td.dan);
    private List<ReadOnlyPerson> secondListWithTypicalPersons = Arrays.asList(td.dan, td.candy, td.amy, td.bill);
    private List<ReadOnlyPerson> thirdListWithTypicalPersons = Arrays.asList(td.candy, td.dan, td.bill, td.amy);
    
    @Test
    public void execute_validIndex_returnsPersonDetails() {
        assertViewBehavior(new SortCommand(), typicalAddressBook, listWithTypicalPersons);
        assertViewBehavior(new SortCommand(), typicalAddressBook, secondListWithTypicalPersons);
        assertViewBehavior(new SortCommand(), typicalAddressBook, thirdListWithTypicalPersons);
    }

    /**
     * Executes the test command for the given addressbook data.
     * Checks that SortCommand exhibits the correct command behavior, namely:
     * 1. The feedback message of the CommandResult it returns matches expectedMessage.
     * 3. The original addressbook data is not modified after executing SortCommand.
     */
    private static void assertViewBehavior(Command viewCommand, AddressBook addressBook,
        List<ReadOnlyPerson> relevantPersons) {
        AddressBook expectedAddressBook = TestUtil.clone(addressBook);

        viewCommand.setData(addressBook, relevantPersons);
        CommandResult result = viewCommand.execute();
        String expectedMessage = String.format(SortCommand.MESSAGE_PERSONS_SORTED_DETAILS,
            relevantPersons.size());
        
        // feedback message is as expected and there are no relevant persons returned.
        assertEquals(expectedMessage, result.getFeedbackToUser());

        // addressbook was not modified.
        assertEquals(expectedAddressBook.getAllPersons(), addressBook.getAllPersons());
    }

}
