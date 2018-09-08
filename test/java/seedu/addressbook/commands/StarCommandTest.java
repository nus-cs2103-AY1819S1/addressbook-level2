package seedu.addressbook.commands;

import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.util.TestUtil;
import seedu.addressbook.util.TypicalPersons;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class StarCommandTest {
    private final AddressBook addressBook = new TypicalPersons().getTypicalAddressBook();
    private final TypicalPersons td = new TypicalPersons();

    @Test
    public void execute(){
        Person person = td.amy;
        person.addStar();

        // Person in address book
        String expectedMessage = String.format(StarCommand.MESSAGE_SUCCESS, person);
        assertCommandBehaviour(person.getName().toString(), expectedMessage);

        // Person not in address book
        expectedMessage = String.format(StarCommand.MESSAGE_PERSON_NOT_FOUND, "Cheryl");
        assertCommandBehaviour("Cheryl", expectedMessage);

        // Not full name
        expectedMessage = String.format(StarCommand.MESSAGE_PERSON_NOT_FOUND, "Amy");
        assertCommandBehaviour("Amy", expectedMessage);

        // No name
        expectedMessage = String.format(StarCommand.MESSAGE_NAME_INVALID, "");
        assertCommandBehaviour("", expectedMessage);
    }

    /**
     * Executes the command, and checks that the execution was what we had expected.
     */
    private void assertCommandBehaviour(String name, String expectedMessage) {
        StarCommand command = createStarCommand(name);
        CommandResult result = command.execute();

        assertEquals(expectedMessage, result.feedbackToUser);
    }

    /**
     * Creates context with pre-added data for star command to execute.
     * @param name The person to star.
     * @return The star command.
     */
    private StarCommand createStarCommand(String name) {
        StarCommand command = new StarCommand(name);
        command.setData(addressBook, Collections.emptyList());
        return command;
    }
}
