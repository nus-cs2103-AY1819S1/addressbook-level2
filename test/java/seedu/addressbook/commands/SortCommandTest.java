package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TypicalPersons;

public class SortCommandTest {

    private final AddressBook addressBook = new TypicalPersons().getTypicalAddressBook();
    private final TypicalPersons td = new TypicalPersons();

    @Test
    public void execute() throws IllegalValueException {
        //sort by valid keyword
        assertSortCommandBehavior("phone", Arrays.asList( td.dan, td.amy, td.bill, td.candy));
        assertSortCommandBehavior("address", Arrays.asList(td.amy, td.dan, td.bill, td.candy));

        //sort by invalid keyword
        assertSortCommandBehavior("emale", Messages.MESSAGE_INVALID_SORT);
        assertSortCommandBehavior("Name", Messages.MESSAGE_INVALID_SORT);
    }

    /**
     * Executes the sort command for the given keywords and verifies
     * the result matches the persons in the expectedPersonList exactly.
     */
    private void assertSortCommandBehavior(String keyword, List<ReadOnlyPerson> expectedPersonList) {
        SortCommand command = createSortCommand(keyword);
        CommandResult result = command.execute();

        assertEquals(Command.getMessageForPersonListShownSummary(expectedPersonList), result.feedbackToUser);
    }

    private void assertSortCommandBehavior(String keyword, String errorMessage) {
        SortCommand command = createSortCommand(keyword);
        CommandResult result = command.execute();
        System.out.println(result.feedbackToUser);

        assertEquals(errorMessage, result.feedbackToUser);
    }

    private SortCommand createSortCommand(String keyword) {
        SortCommand command = new SortCommand(keyword);
        command.setData(addressBook, Collections.emptyList());
        return command;
    }

}
