package seedu.addressbook.commands;

import org.junit.Before;
import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TestUtil;
import seedu.addressbook.util.TypicalPersons;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class SortCommandTest {
    private TypicalPersons td = new TypicalPersons();
    private AddressBook toBeSorted = TestUtil.createAddressBook(td.bill, td.amy);
    private AddressBook expected = TestUtil.createAddressBook(td.amy, td.bill);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void execute() {
        Command sortCommand = new SortCommand();
        sortCommand.setData(toBeSorted, Collections.emptyList());
        CommandResult result = sortCommand.execute();

        // checks if toBeSorted is actually sorted
        assertEquals(toBeSorted.getAllPersons(), expected.getAllPersons());
        // checks feedback message and no relevant persons returned
        assertEquals(result.feedbackToUser, SortCommand.MESSAGE_SUCCESS);
        assertEquals(Optional.empty(), result.getRelevantPersons());
    }
}