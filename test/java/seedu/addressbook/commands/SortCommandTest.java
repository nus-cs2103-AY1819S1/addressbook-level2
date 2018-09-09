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
        UniquePersonList uniquePersonList = new UniquePersonList(td.amy, td.bill, td.candy);
        List<ReadOnlyPerson> sortedPersonList = uniquePersonList.immutableListView();

        // set up an unsorted list.
        Person[] unsortedPeopleList = {td.amy, td.candy, td.bill};

        assertEqualAfterSort(unsortedPeopleList, sortedPersonList);
    }

    /**
     * Executes the sort command for the given unsorted list and verifies the result matches the
     * persons in the expectedSortedPersonList exactly.
     */
    private void assertEqualAfterSort(Person[] people,
            List<ReadOnlyPerson> expectedSortedPersonList)
            throws DuplicatePersonException {
        SortCommand command = createSortCommand(people);
        command.execute(); // the unsorted list has been sorted
        assertEquals(addressBook.getAllPersons().immutableListView(),
                expectedSortedPersonList);
    }

    private SortCommand createSortCommand(Person[] people) throws DuplicatePersonException {
        addressBook.clear();

        // Add the people into the address book
        for (Person keyword : people) {
            addressBook.addPerson(new Person(keyword));
        }

        SortCommand command = new SortCommand();
        command.setData(addressBook, Collections.emptyList());
        return command;
    }
}
