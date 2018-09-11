package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.Test;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TypicalPersons;

public class FindNumberCommandTest {

    private final AddressBook addressBook = new TypicalPersons().getTypicalAddressBook();
    private final TypicalPersons td = new TypicalPersons();

    @Test
    public void execute() throws IllegalValueException {
        //same word, same case: matched
        assertFindCommandBehavior(new String[]{"Amy"}, td.amy.getPhone().value);

        //same word, same case: matched
        assertFindCommandBehavior(new String[]{"Candy", "Destiny"}, td.candy.getPhone().value);
        
    }

    /**
     * Executes the find command for the given keywords and verifies
     * the result matches the persons in the expectedPersonList exactly.
     */
    private void assertFindCommandBehavior(String[] keywords, String number) {
        FindNumberCommand command = createFindNumberCommand(keywords);
        CommandResult result = command.execute();

        final Optional<List<? extends ReadOnlyPerson>> resultPersons = result.getRelevantPersons();
        assertEquals(resultPersons.get().get(0).getPhone().value, number);
    }

    private FindNumberCommand createFindNumberCommand(String[] keywords) {
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        FindNumberCommand command = new FindNumberCommand(keywordSet);
        command.setData(addressBook, Collections.emptyList());
        return command;
    }

}
