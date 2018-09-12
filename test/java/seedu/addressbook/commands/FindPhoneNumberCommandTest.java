package addressbook.commands;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import seedu.addressbook.commands.Command;
import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.commands.FindPhoneNumberCommand;
import seedu.addressbook.commands.FindPhoneNumberCommand;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TypicalPersons;

public class FindPhoneNumberCommandTest {

    private final AddressBook addressBook = new TypicalPersons().getTypicalAddressBook();
    private final TypicalPersons td = new TypicalPersons();

    @Test
    public void execute() throws IllegalValueException {
        //same word, same case: matched
        assertFindCommandBehavior(new String[]{"91119111"}, Arrays.asList(td.amy));

        //same word, different case: not matched
        assertFindCommandBehavior(new String[]{"9"}, Collections.emptyList());

        //partial word: not matched
        assertFindCommandBehavior(new String[]{""}, Collections.emptyList());

        //multiple words: matched
        assertFindCommandBehavior(new String[]{"91119111", "92229222", "93339333"},
                Arrays.asList(td.amy, td.bill, td.candy));

        //repeated keywords: matched
        assertFindCommandBehavior(new String[]{"91119111", "91119111"}, Arrays.asList(td.amy));
    }

    /**
     * Executes the find command for the given keywords and verifies
     * the result matches the phone number in the expectedPersonList exactly.
     */
    private void assertFindCommandBehavior(String[] keywords, List<ReadOnlyPerson> expectedPersonList) {
        FindPhoneNumberCommand command = createFindPhoneNumberCommand(keywords);
        CommandResult result = command.execute();

        assertEquals(Command.getMessageForPersonListShownSummary(expectedPersonList), result.feedbackToUser);
    }


    private FindPhoneNumberCommand createFindPhoneNumberCommand(String[] keywords) {
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        FindPhoneNumberCommand command = new FindPhoneNumberCommand(keywordSet);
        command.setData(addressBook, Collections.emptyList());
        return command;
    }



}
