package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.*;
import seedu.addressbook.util.TestUtil;
import seedu.addressbook.commands.ShowListCommand;

import java.util.Collections;
import java.util.List;

public class ShowListCommandTest {


    @Test
    public void createStatsMessage_correctly() throws Exception {
        Person cj = new Person(new Name("cj"), new Phone("85241253", false),
                new Email("cj@gmail.com", false), new Address("rvrc", false), Collections.emptySet());

        ShowListCommand showListCommand = new ShowListCommand();
        List<ReadOnlyPerson> personList = TestUtil.createList();

        // 0 people
        assertEquals(
                "Current number of people in address book : 0!",
                showListCommand.createStatsMessage(personList));


    }


}
