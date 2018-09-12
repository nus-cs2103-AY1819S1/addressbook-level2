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

    public void countCheckZero() throws Exception {
        AddressBook addressBook = new AddressBook();
        CountCommand command = new CountCommand();
        command.setData(addressBook, addressBook.getAllPersons().immutableListView());
        assertEquals("0 persons listed!", command.execute().feedbackToUser);
    }

    public void countCheckNonZero() throws Exception {
        AddressBook addressBook = new AddressBook();
        addressBook.addPerson(TestUtil.generateTestPerson());
        CountCommand command = new CountCommand();
        command.setData(addressBook, addressBook.getAllPersons().immutableListView());
        assertEquals("1 persons listed!", command.execute().feedbackToUser);
    }
}