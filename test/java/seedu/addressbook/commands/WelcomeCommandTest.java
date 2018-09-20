package seedu.addressbook.commands;

import org.junit.Test;

import static org.junit.Assert.*;

public class WelcomeCommandTest {

    @Test
    public void execute_emptyAddressBook_returnsWelcomeMessage() {
        assertEquals("Welcome! This is AddressBook v2., an upgrade over AddressBook v1.! Over the next few " +
                        "weeks, you'll get to see more AddressBook versions (v3. and v4.)! Enjoy!",
                (new WelcomeCommand()).execute().feedbackToUser);
    }

}