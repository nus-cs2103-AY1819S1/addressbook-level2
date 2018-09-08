package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TestUtil;
import seedu.addressbook.util.TypicalPersons;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StatsCommandTest {
    private TypicalPersons td = new TypicalPersons();

    private AddressBook typicalAddressBook = td.getTypicalAddressBook();
    private AddressBook emptyAddressBook = TestUtil.createAddressBook();
    private List<ReadOnlyPerson> emptyPersonList = Collections.emptyList();
    private List<ReadOnlyPerson> listWithAllTypicalPersons = Arrays.asList(td.getTypicalPersons());

    @Test
    public void execute_EmptyAddressBook() {
        StatsCommand command =  new StatsCommand();
        command.setData(emptyAddressBook, emptyPersonList);
        CommandResult result = command.execute();
        assertEquals(result.feedbackToUser,StatsCommand.MESSAGE_STATS + emptyPersonList.size());
    }
    
    @Test
    public void execute_TypicalAddressBook() {
        StatsCommand command =  new StatsCommand();
        command.setData(typicalAddressBook, listWithAllTypicalPersons);
        CommandResult result = command.execute();
        assertEquals(result.feedbackToUser,StatsCommand.MESSAGE_STATS + listWithAllTypicalPersons.size());
    }
}
