package seedu.addressbook.commands;

import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TypicalPersons;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class TagCommandTest {

    private final AddressBook addressBook = new TypicalPersons().getTypicalAddressBook();
    private final TypicalPersons td = new TypicalPersons();

    @Test
    public void execute() throws IllegalValueException {
        //same word, same case: matched
        assertTagCommandBehavior(new String[]{"friends"}, Arrays.asList(td.bill, td.candy));

        //non-existing tag: not matched
        assertTagCommandBehavior(new String[]{"fr"}, Collections.emptyList());

        //multiple tags: matched
        assertTagCommandBehavior(new String[]{"friends", "test"},
                Arrays.asList(td.bill, td.candy, td.dan));

        //repeated keywords: matched
        assertTagCommandBehavior(new String[]{"friends", "friends"}, Arrays.asList(td.bill, td.candy));

    }

    /**
     * Executes the Tag command for the given keywords and verifies
     * the result matches the persons in the expectedPersonList exactly.
     */
    private void assertTagCommandBehavior(String[] keywords, List<ReadOnlyPerson> expectedPersonList) {
        TagCommand command = createTagCommand(keywords);
        CommandResult result = command.execute();

        assertEquals(Command.getMessageForPersonListShownSummary(expectedPersonList), result.feedbackToUser);
    }

    private TagCommand createTagCommand(String[] keywords) {
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        TagCommand command = new TagCommand(keywordSet);
        command.setData(addressBook, Collections.emptyList());
        return command;
    }

}
