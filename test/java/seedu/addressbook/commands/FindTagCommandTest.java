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

public class FindTagCommandTest {

    private final AddressBook addressBook = new TypicalPersons().getTypicalAddressBook();
    private final TypicalPersons td = new TypicalPersons();

    @Test
    public void execute() throws IllegalValueException {
        //same word, same case: matched
        assertFindTagCommandBehavior(new String[]{"test1"}, Arrays.asList(td.amy, td.dan));

        //same word, different case: not matched
        assertFindTagCommandBehavior(new String[]{"Test1"}, Collections.emptyList());

        //partial word: not matched
        assertFindTagCommandBehavior(new String[]{"test"}, Collections.emptyList());

        //multiple tags: matched
        assertFindTagCommandBehavior(new String[]{"test1", "test2"},
                Arrays.asList(td.amy, td.bill, td.dan));

        //repeated tags: matched
        assertFindTagCommandBehavior(new String[]{"test2", "test2"}, Arrays.asList(td.bill));

        //Keyword matching a name: not matched
        assertFindTagCommandBehavior(new String[]{"Amy Buck"}, Collections.emptyList());

        //Keyword matching a word in address: not matched
        assertFindTagCommandBehavior(new String[]{"Clementi"}, Collections.emptyList());
    }

    /**
     * Executes the findtag command for the given keywords and verifies
     * the result matches the persons in the expectedPersonList exactly.
     */
    private void assertFindTagCommandBehavior(String[] keywords, List<ReadOnlyPerson> expectedPersonList) {
        FindTagCommand command = createFindTagCommand(keywords);
        CommandResult result = command.execute();

        assertEquals(Command.getMessageForPersonListShownSummary(expectedPersonList), result.feedbackToUser);
    }

    private FindTagCommand createFindTagCommand(String[] keywords) {
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        FindTagCommand command = new FindTagCommand(keywordSet);
        command.setData(addressBook, Collections.emptyList());
        return command;
    }

}
