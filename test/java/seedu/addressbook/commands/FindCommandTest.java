package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TypicalPersons;

public class FindCommandTest {

    private final AddressBook addressBook = new TypicalPersons().getTypicalAddressBook();
    private final TypicalPersons td = new TypicalPersons();

    @Test
    public void execute() throws IllegalValueException {
        //same word, same case of fields: matched
        assertFindCommandBehavior(new String[]{"Amy"}, Arrays.asList(td.amy));
        assertFindCommandBehavior(new String[]{"Clementi"}, Arrays.asList(td.amy));
        assertFindCommandBehavior(new String[]{"91119111"}, Arrays.asList(td.amy));
        assertFindCommandBehavior(new String[]{"cd@gmail.com"}, Arrays.asList(td.candy));
        assertFindCommandBehavior(new String[]{"test"}, Arrays.asList(td.dan));

        //same word, different case of fields: not matched
        assertFindCommandBehavior(new String[]{"aMy"}, Collections.emptyList());
        assertFindCommandBehavior(new String[]{"ClementI"}, Collections.emptyList());
        assertFindCommandBehavior(new String[]{"cd@Gmail.com"}, Collections.emptyList());
        assertFindCommandBehavior(new String[]{"Test"}, Collections.emptyList());

        //partial word: not matched
        assertFindCommandBehavior(new String[]{"my"}, Collections.emptyList());
        assertFindCommandBehavior(new String[]{"Clemti"}, Collections.emptyList());
        assertFindCommandBehavior(new String[]{"919111"}, Collections.emptyList());
        assertFindCommandBehavior(new String[]{"cd@g.com"}, Collections.emptyList());
        assertFindCommandBehavior(new String[]{"est"}, Collections.emptyList());

        //multiple words with different fields: matched
        assertFindCommandBehavior(new String[]{"Amy", "Bill", "Candy", "Destiny"},
                Arrays.asList(td.amy, td.bill, td.candy));
        assertFindCommandBehavior(new String[]{"Amy", "92229222", "cd@gmail.com", "test"},
                Arrays.asList(td.amy, td.bill, td.candy, td.dan));

        //multiple keywords of different fields for one person: matched
        assertFindCommandBehavior(new String[]{"Amy", "91119111", "ab@gmail.com"}, Arrays.asList(td.amy));

        //repeated keywords for one person: matched
        assertFindCommandBehavior(new String[]{"Amy", "91119111"}, Arrays.asList(td.amy));

        //keyword matching a word which is private: not matched
        assertFindCommandBehavior(new String[]{"93339333"}, Collections.emptyList());

        //keyword matching with some words which are private: matched
        assertFindCommandBehavior(new String[]{"Amy", "bc@gmail.com", "93339333", "test"},
                Arrays.asList(td.amy, td.bill, td.dan));
    }

    /**
     * Executes the find command for the given keywords and verifies
     * the result matches the persons in the expectedPersonList exactly.
     */
    private void assertFindCommandBehavior(String[] keywords, List<ReadOnlyPerson> expectedPersonList) {
        FindCommand command = createFindCommand(keywords);
        CommandResult result = command.execute();

        assertEquals(Command.getMessageForPersonListShownSummary(expectedPersonList), result.feedbackToUser);
    }

    private FindCommand createFindCommand(String[] keywords) {
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        FindCommand command = new FindCommand(keywordSet);
        command.setData(addressBook, Collections.emptyList());
        return command;
    }

}
