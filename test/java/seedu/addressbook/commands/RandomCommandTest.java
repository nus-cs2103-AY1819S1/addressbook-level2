package seedu.addressbook.commands;

import org.junit.Test;
import seedu.addressbook.common.Messages;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TestUtil;
import seedu.addressbook.util.TypicalPersons;

import java.util.Collections;
import java.util.List;

import static seedu.addressbook.commands.ViewCommandTest.assertViewBehavior;

public class RandomCommandTest {
    private TypicalPersons td = new TypicalPersons();
    private AddressBook emptyAddressBook = TestUtil.createAddressBook();
    private List<ReadOnlyPerson> emptyPersonList = Collections.emptyList();

    @Test
    public void execute_invalidIndex_returnsInvalidIndexMessage() {
        // empty addressbook
        assertRandomErrorInvalidIndex(emptyAddressBook, emptyPersonList);
    }

    private void assertRandomErrorInvalidIndex(AddressBook addressBook, List<ReadOnlyPerson> relevantPersons) {
        assertRandomError(addressBook, relevantPersons,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Asserts that the Viewcommand and ViewAllcommand reports the given error for the given input.
     */
    private static void assertRandomError(AddressBook addressBook, List<ReadOnlyPerson> relevantPersons, String expectedMessage) {
        assertViewBehavior(new RandomCommand(), addressBook, relevantPersons, expectedMessage);
    }

}
