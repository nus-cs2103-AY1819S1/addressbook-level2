package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.data.person.UniquePersonList.DuplicatePersonException;
import seedu.addressbook.util.TypicalPersons;

public class SortCommandTest {
    private final AddressBook addressBook = new AddressBook();
    private final TypicalPersons td = new TypicalPersons();

    @Test
    public void execute() throws DuplicatePersonException {
        // set up the sorted list
        List<ReadOnlyPerson> sortedPeopleList = new UniquePersonList(td.amy, td
                .bill, td.candy).immutableListView();

        // set up an unsorted list.
        UniquePersonList unsortedPeopleList = new UniquePersonList(td.candy, td
                .bill, td.amy);
        assertEqualAfterSort(unsortedPeopleList, sortedPeopleList);
    }

    /**
     * Executes the sort command for the given toBeSortedList and checks the
     * result matches the
     * persons in the expectedSortedPersonList exactly.
     */
    private void assertEqualAfterSort(UniquePersonList toBeSortedList,
                                      List<ReadOnlyPerson>
                                              expectedSortedPersonList) throws
            DuplicatePersonException {

        addressBook.clear();
        // Add the people into the address book
        for (Person keyword : toBeSortedList) {
            addressBook.addPerson(new Person(keyword));
        }

        SortCommand command = new SortCommand();
        command.setData(addressBook, Collections.emptyList());
        command.execute();

        assertEquals(addressBook.getAllPersons().immutableListView(),
                expectedSortedPersonList);
    }

}