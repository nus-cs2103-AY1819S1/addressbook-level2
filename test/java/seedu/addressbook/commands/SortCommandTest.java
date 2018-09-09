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
import seedu.addressbook.util.TestUtil;

public class SortCommandTest {
    private AddressBook addressBook = new TypicalPersons().getTypicalAddressBook();
    private TypicalPersons td = new TypicalPersons();

    @Test
    public void execute() throws IllegalValueException {
        //multiple words: sorted
        addressBook = TestUtil.createAddressBook(td.bill, td.candy, td.amy, td.dan);
        assertSortCommandBehavior(Arrays.asList(td.amy, td.bill, td.candy, td.dan));
        addressBook = TestUtil.createAddressBook(td.dan, td.candy, td.amy);
        assertSortCommandBehavior(Arrays.asList(td.amy, td.candy, td.dan));
    }

    /**
     * Executes the sort command and verifies
     * the result matches the persons in the expectedPersonList exactly.
     */
    private void assertSortCommandBehavior(List<ReadOnlyPerson> expectedPersonList) {
        SortCommand command = createSortCommand();
        CommandResult result = command.execute();
        assertEquals(expectedPersonList, result.getRelevantPersonsList());
    }

    private SortCommand createSortCommand() {
        SortCommand command = new SortCommand();
        command.setData(addressBook, Collections.emptyList());
        return command;
    }
}
