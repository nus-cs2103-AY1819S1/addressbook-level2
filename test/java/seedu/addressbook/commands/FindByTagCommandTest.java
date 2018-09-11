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

public class FindByTagCommandTest {

    private final AddressBook addressBook = new TypicalPersons().getTypicalAddressBook();
    private final TypicalPersons td = new TypicalPersons();

    @Test
    public void execute() throws IllegalValueException {
        //same word, same case: matched
        assertFindByTagCommandBehavior(new String[]{"test"}, Arrays.asList(td.dan));

        //same word, different case: not matched
        assertFindByTagCommandBehavior(new String[]{"tEst"}, Collections.emptyList());

        //partial word: not matched
        assertFindByTagCommandBehavior(new String[]{"my"}, Collections.emptyList());

        //multiple words: matched
        assertFindByTagCommandBehavior(new String[]{"test", "test2"},
                Arrays.asList(td.dan, td.candy));

        //repeated keywords: matched
        assertFindByTagCommandBehavior(new String[]{"test", "test"}, Arrays.asList(td.dan));

        //Keyword matching a word in address: not matched
        assertFindByTagCommandBehavior(new String[]{"test2", "test10"}, Arrays.asList(td.candy));
    }

    /**
     * Executes the find command for the given keywords and verifies
     * the result matches the persons in the expectedPersonList exactly.
     */
    private void assertFindByTagCommandBehavior(String[] keywords, List<ReadOnlyPerson> expectedPersonList) {
        FindByTagCommand command = createFindByTagCommand(keywords);
        CommandResult result = command.execute();

        assertEquals(Command.getMessageForPersonListShownSummary(expectedPersonList), result.feedbackToUser);
    }

    private FindByTagCommand createFindByTagCommand(String[] keywords) {
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        FindByTagCommand command = new FindByTagCommand(keywordSet);
        command.setData(addressBook, Collections.emptyList());
        return command;
    }

}
