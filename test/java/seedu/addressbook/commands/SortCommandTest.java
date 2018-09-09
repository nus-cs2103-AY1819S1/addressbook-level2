package seedu.addressbook.commands;

import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TestUtil;
import seedu.addressbook.util.TypicalPersons;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class SortCommandTest {
    private TypicalPersons td = new TypicalPersons();

    private static final List<Person> EMPTY_PERSON_LIST = Collections.emptyList();
    private List<Person> listWithSinglePerson = Arrays.asList(td.candy);
    private List<Person> unsortedListWithAllTypicalPersons = Arrays.asList(td.dan, td.amy, td.candy, td.bill);
    private List<Person> sortedListWithAllTypicalPersons = Arrays.asList(td.getTypicalPersons());

    @Test
    public void execute_sortEmptyAddressBook_returnsSuccess() {
        assertSortBehavior(EMPTY_PERSON_LIST, EMPTY_PERSON_LIST, SortCommand.MESSAGE_SORT_SUCCESS);
    }

    @Test
    public void execute_sortSinglePersonAddressBook_returnsSuccess() {
        assertSortBehavior(listWithSinglePerson, listWithSinglePerson, SortCommand.MESSAGE_SORT_SUCCESS);
    }

    @Test
    public void execute_sortMultiplePersonAddressBook_returnsSuccess() {
        assertSortBehavior(unsortedListWithAllTypicalPersons, sortedListWithAllTypicalPersons, SortCommand.MESSAGE_SORT_SUCCESS);
    }

    /**
     * Executes the sort command for a given list of persons, to be added to an addressbook.
     * Checks that SortCommand correctly sorts the list of persons in alphabetical order. Namely:
     * 1. The feedback message of the CommandResult it returns matches expectedMessage.
     * 2. The addressbook data matches the expected list of persons expectedPersons.
     */
    private static void assertSortBehavior(List<Person> givenPersons,
                                           List<Person> expectedPersons, String expectedMessage) {
        AddressBook addressBook = TestUtil.createAddressBook(
                givenPersons.toArray(new Person[0]));

        SortCommand sortCommand = new SortCommand();
        sortCommand.setData(addressBook, EMPTY_PERSON_LIST);
        CommandResult result = sortCommand.execute();

        // feedback message is as expected and there are no relevant persons returned.
        assertEquals(expectedMessage, result.feedbackToUser);
        assertEquals(Optional.empty(), result.getRelevantPersons());

        // addressbook is now sorted
        assertEquals(addressBook.getAllPersons().immutableListView(), expectedPersons);
    }

}
