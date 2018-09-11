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
import seedu.addressbook.util.TestUtil;
import seedu.addressbook.util.TypicalPersons;

public class SortCommandTest {

    private final TypicalPersons td = new TypicalPersons();

    /**
     * Executes the sort command
     * the result matches the sorted list of all persons
     */

    @Test
    public void execute() {
        // empty AddressBook
        AddressBook inputAddressBook1 = TestUtil.createAddressBook();
        AddressBook expectedAddressBook1 = TestUtil.createAddressBook();
        assertSortCommandBehavior(inputAddressBook1, expectedAddressBook1);

        // 1 element
        AddressBook inputAddressBook2 = TestUtil.createAddressBook(td.bill);
        AddressBook expectedAddressBook2 = TestUtil.createAddressBook(td.bill);
        assertSortCommandBehavior(inputAddressBook2, expectedAddressBook2);

        // 2 or more elements
        AddressBook inputAddressBook3 = TestUtil.createAddressBook(td.bill, td.amy);
        AddressBook expectedAddressBook3 = TestUtil.createAddressBook(td.amy, td.bill);
        assertSortCommandBehavior(inputAddressBook3, expectedAddressBook3);

        AddressBook inputAddressBook4 = TestUtil.createAddressBook(td.bill, td.candy, td.dan, td.amy);
        AddressBook expectedAddressBook4 = TestUtil.createAddressBook(td.amy, td.bill, td.candy, td.dan);
        assertSortCommandBehavior(inputAddressBook4, expectedAddressBook4);
    }

    private void assertSortCommandBehavior(AddressBook inputAddressBook, AddressBook expectedAddressBook) {
        SortCommand command = createSortCommand(inputAddressBook);
        CommandResult result = command.execute();
        assertEquals(expectedAddressBook.getAllPersons(), inputAddressBook.getAllPersons());
    }

    private SortCommand createSortCommand(AddressBook ad) {
        SortCommand command = new SortCommand();
        command.setData(ad, Collections.emptyList());
        return command;
    }

}