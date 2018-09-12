package seedu.addressbook.commands;

import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.util.TypicalPersons;

import java.util.Collections;

import static org.junit.Assert.*;

public class NewestEntryCommandTest {

    private final AddressBook addressBookTypical = new TypicalPersons().getTypicalAddressBook();
    private final AddressBook addressBookEmpty = new AddressBook();

    @Test
    public void testCommandTypical() {

        NewestEntryCommand command = createNewestCommand(addressBookTypical);
        CommandResult result = command.execute();
        String expectedOutput = "The Newest entry is: 4. Dan Smith Phone: (private) 1234556 Email: (private) ss@tt.com Address: (private) NUS Tags: [test]";
        assertEquals(expectedOutput, result.feedbackToUser);

    }

    @Test
    public void testCommandEmpty() {

        NewestEntryCommand command = createNewestCommand(addressBookEmpty);
        CommandResult result = command.execute();
        String expectedOutput = "There is no entry in address book";
        assertEquals(expectedOutput, result.feedbackToUser);

    }

    private NewestEntryCommand createNewestCommand(AddressBook book) {
        NewestEntryCommand command = new NewestEntryCommand();
        command.setData(book, Collections.emptyList());
        return command;
    }

}