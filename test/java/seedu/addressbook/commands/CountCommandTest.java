package seedu.addressbook.commands;

import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.*;
import seedu.addressbook.util.TestUtil;

import java.util.*;

import static org.junit.Assert.*;

public class CountCommandTest {
    private static final List<ReadOnlyPerson> EMPTY_PERSON_LIST = Collections.emptyList();

    @Test
    public void countCommand_emptyList_returnsZero() {
        AddressBook book = new AddressBook();
        CountCommand command = new CountCommand();
        command.setData(book, EMPTY_PERSON_LIST);

        assertEquals("0",command.execute().feedbackToUser);
    }

    @Test
    public void countCommand_nonEmptyList_returnsCount() throws Exception {
        Person p = TestUtil.generateTestPerson();
        AddressBook book = new AddressBook();
        book.addPerson(p);
        CountCommand command = new CountCommand();
        command.setData(book, book.getAllPersons().immutableListView());

        assertEquals("1",command.execute().feedbackToUser);
    }
}
