package seedu.addressbook.commands;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TypicalPersons;

public class SortCommandTest {

    private TypicalPersons td = new TypicalPersons();
    private AddressBook typicalAddressBook = td.getTypicalAddressBook();
    private List<ReadOnlyPerson> listWithTypicalPersons = Arrays
            .asList(td.getTypicalPersons());
    private List<ReadOnlyPerson> listWithAlphabeticalOrderPersons = Arrays
            .asList(td.getAlphabeticalOrderPersons());
    private SortCommand sortCommand = new SortCommand();

    @Test
    public void sortCommand_alphabeticalOrder() {
        sortCommand.setData(typicalAddressBook, listWithTypicalPersons);
        CommandResult result = sortCommand.execute();
        Optional<List<? extends ReadOnlyPerson>> sortedPersons = result.getRelevantPersons();
        assertTrue(sortedPersons.isPresent());
        List<? extends ReadOnlyPerson> sortedList = sortedPersons.get();
        assertSortSuccess(listWithAlphabeticalOrderPersons, sortedList);
    }

    /**
     * Assert that the sizes of each person list are equal as well as the orders of each person in the list
     * are equal.
     */
    private void assertSortSuccess(List<ReadOnlyPerson> expectedList,
            List<? extends ReadOnlyPerson> sortedList) {
        assertEquals(expectedList.size(), sortedList.size());
        for (int i = 0; i < expectedList.size(); i++) {
            assertTrue(expectedList.get(i).isSamePerson(sortedList.get(i)));
        }
    }

}
