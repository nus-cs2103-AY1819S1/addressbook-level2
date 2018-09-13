package seedu.addressbook.commands;

import static org.junit.Assert.assertTrue;

import java.util.Collections;

import org.junit.Test;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.util.TypicalPersons;
import seedu.addressbook.util.TestUtil;

public class SortCommandTest {

    private final TypicalPersons td = new TypicalPersons();

    @Test
    public void execute() throws IllegalValueException {
        AddressBook sortedAddressBook;
        AddressBook unsortedAddressBook;

        sortedAddressBook = TestUtil.createAddressBook(td.amy, td.bill, td.candy, td.dan);
        unsortedAddressBook = TestUtil.createAddressBook(td.bill, td.dan, td.candy, td.amy);
        assertSortCommandBehavior(unsortedAddressBook, sortedAddressBook);

        sortedAddressBook = TestUtil.createAddressBook(td.amy, td.bill);
        unsortedAddressBook = TestUtil.createAddressBook(td.amy, td.bill);
        assertSortCommandBehavior(unsortedAddressBook, sortedAddressBook);

        sortedAddressBook = TestUtil.createAddressBook();
        unsortedAddressBook = TestUtil.createAddressBook();
        assertSortCommandBehavior(unsortedAddressBook, sortedAddressBook);
    }

    /**
     * Executes the find command for the given keywords and verifies
     * the result matches the persons in the expectedPersonList exactly.
     */
    private void assertSortCommandBehavior(AddressBook input, AddressBook expected) {
        SortCommand command = createSortCommand(input);
        command.execute();

        assertTrue(input.equals(expected));
    }

    private SortCommand createSortCommand(AddressBook input) {
        SortCommand command = new SortCommand();
        command.setData(input, Collections.emptyList());
        return command;
    }

}
