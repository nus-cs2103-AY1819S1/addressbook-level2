package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.util.TestUtil;
import seedu.addressbook.util.TypicalPersons;

public class SortCommandTest {
    private final AddressBook EMPTY_ADDRESS_BOOK = TestUtil.createAddressBook();
    private final TypicalPersons TYPICAL_PERSONS_DATA = new TypicalPersons();

    private SortCommand command;

    @Test
    public void execute() {
        //test sorting on an empty address book
        assertSortCommandBehaviour_emptyBook(EMPTY_ADDRESS_BOOK);

        //test sorting on a sample address book with no duplicates
        AddressBook sampleAddressBook = TestUtil.createAddressBook(TYPICAL_PERSONS_DATA.candy, TYPICAL_PERSONS_DATA.amy,
                TYPICAL_PERSONS_DATA.bill, TYPICAL_PERSONS_DATA.dan);
        assertSortCommandBehaviour_firstSampleBook(sampleAddressBook);

        //test sorting on a sample address book with duplicates
        Person[] testPersons = createTestPersons(sampleAddressBook);
        assertSortCommandBehaviour_secondSampleBook(testPersons, sampleAddressBook);
    }

    private Person[] createTestPersons(AddressBook addressBook) {
        try {
            Person[] testPersons = {TYPICAL_PERSONS_DATA.amy, TYPICAL_PERSONS_DATA.dan};
            testPersons[0] = new Person(testPersons[0].getName(), new Phone("91119112", true),
                               TYPICAL_PERSONS_DATA.candy.getEmail(), new Address("75 Science Park", true),
                               TYPICAL_PERSONS_DATA.bill.getTags());
            testPersons[1] = new Person(testPersons[1].getName(), new Phone("11111234", false),
                               new Email("british@hotmail.com", true), TYPICAL_PERSONS_DATA.candy.getAddress(),
                                TYPICAL_PERSONS_DATA.amy.getTags());
            for (Person p: testPersons) {
                addressBook.addPerson(p);
            }
            return testPersons;
        } catch(IllegalValueException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    private void assertSortCommandBehaviour_firstSampleBook(AddressBook addressBook) {
        command = new SortCommand();
        command.setData(addressBook, addressBook.getAllPersons().immutableListView());
        CommandResult result = command.execute();
        assertEquals(result.getRelevantPersons().get(), TYPICAL_PERSONS_DATA.getTypicalAddressBook().getAllPersons().immutableListView());
        assertEquals(result.feedbackToUser, "4 persons listed!");
    }

    private void assertSortCommandBehaviour_secondSampleBook(Person[] testPersons, AddressBook addressBook) {
        command = new SortCommand();
        command.setData(addressBook, addressBook.getAllPersons().immutableListView());
        CommandResult result = command.execute();
        List<Person> expectedPersons = Arrays.asList(TYPICAL_PERSONS_DATA.amy, testPersons[0], TYPICAL_PERSONS_DATA.bill,
                                         TYPICAL_PERSONS_DATA.candy, testPersons[1], TYPICAL_PERSONS_DATA.dan);
        assertEquals(result.getRelevantPersons().get(), expectedPersons);
        assertEquals(result.feedbackToUser, "6 persons listed!");
    }

    private void assertSortCommandBehaviour_emptyBook(AddressBook addressBook) {
        command = new SortCommand();
        command.setData(addressBook, null);
        assertEquals(command.execute().feedbackToUser, "0 persons listed!");
    }
}
