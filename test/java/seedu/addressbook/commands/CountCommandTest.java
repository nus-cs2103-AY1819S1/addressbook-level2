package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TestUtil;

import java.util.Collections;
import java.util.List;

public class CountCommandTest {
    private static final List<ReadOnlyPerson> EMPTY_PERSON_LIST = Collections.emptyList();

    @Test
    public void countCheckNonZero() throws Exception {

        AddressBook addressBook = new AddressBook();
        addressBook.addPerson(TestUtil.generateTestPerson());

        CountCommand count = new CountCommand();
        count.setData(addressBook, addressBook.getAllPersons().immutableListView());

        assertEquals("1 persons listed!", count.execute().feedbackToUser);
    }
}
