package seedu.addressbook.commands;

import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TestUtil;
import seedu.addressbook.util.TypicalPersons;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class SortCommandTest {

    private TypicalPersons td = new TypicalPersons();
    private AddressBook toBeSorted = TestUtil.createAddressBook(td.dan, td.candy);
    private AddressBook expected = TestUtil.createAddressBook(td.candy, td.dan);

    @Test
    public void execute() {
        Command sortCommand = new SortCommand();
        sortCommand.setData(toBeSorted, Collections.emptyList());
        CommandResult result = sortCommand.execute();
        // checks if toBeSorted is actually sorted
        assertEquals(toBeSorted.getAllPersons(), expected.getAllPersons());
        // checks feedback message and no relevant persons returned
        assertEquals(result.feedbackToUser, "2 persons listed!");
//        List<ReadOnlyPerson> ROP = new ArrayList<>();
//        ROP.add(td.candy);
//        ROP.add(td.dan);
        assertEquals(Optional.ofNullable(expected.getAllPersons().immutableListView()), result.getRelevantPersons());
    }

}