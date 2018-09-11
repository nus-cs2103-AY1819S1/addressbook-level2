package seedu.addressbook.commands;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.util.TypicalPersons;

import java.util.List;

public class SortCommandTest {
    private TypicalPersons td = new TypicalPersons();

    private AddressBook typicalAddressBook;

    @Before
    public void initialisePersonToSort() {
        typicalAddressBook = td.getTypicalAddressBook();
    }

    @Test
    public void sortNameCommand_correctlySorted() {
        typicalAddressBook.sortByName();
        UniquePersonList sortedPersonsByName = typicalAddressBook.getAllPersons();
        assertCorrectlySortedByName(sortedPersonsByName);
    }

    @Test
    public void sortPhoneCommand_correctlySorted() {
        typicalAddressBook.sortByPhone();
        UniquePersonList sortedPersonsByPhone = typicalAddressBook.getAllPersons();
        assertCorrectlySortedByPhone(sortedPersonsByPhone);
    }

    private void assertCorrectlySortedByName(UniquePersonList sortedPersons) {
        int indexToBeTested = 1;
        List<ReadOnlyPerson> sortedPersonList = sortedPersons.immutableListView();
        ReadOnlyPerson person1;
        ReadOnlyPerson person2;
        while (indexToBeTested < sortedPersonList.size()) {
            person1 = sortedPersonList.get(indexToBeTested - 1);
            person2 = sortedPersonList.get(indexToBeTested);
            assertTrue(person1.getName().compareTo(person2.getName()) <= 0);
            indexToBeTested++;
        }
    }

    private void assertCorrectlySortedByPhone(UniquePersonList sortedPersons) {
        int indexToBeTested = 1;
        List<ReadOnlyPerson> sortedPersonList = sortedPersons.immutableListView();
        ReadOnlyPerson person1;
        ReadOnlyPerson person2;
        while (indexToBeTested < sortedPersonList.size()) {
            person1 = sortedPersonList.get(indexToBeTested - 1);
            person2 = sortedPersonList.get(indexToBeTested);
            assertTrue(person1.getPhone().compareTo(person2.getPhone()) <= 0);
            indexToBeTested++;
        }
    }
}
