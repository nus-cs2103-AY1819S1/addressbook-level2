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

public class SearchEmailCommandTest {
	private final AddressBook addressBook = new TypicalPersons().getTypicalAddressBook();
    private final TypicalPersons td = new TypicalPersons();

	@Test
	public void execute() throws IllegalValueException {
		
	}

	/**
	* Executes the SearchEmailCommand and asserts the validity of the output
	*/
	private void assertOutput(String[] emails, List<ReadOnlyPerson> output) {
		SearchEmailCommand command = createSearchEmailCommand(emails);
		CommandResult result = command.execute();

		assertEquals(Command.getMessageForPersonListShownSummary(expectedPersonList), result.feedbackToUser);
	}

	private SearchEmailCommand createSearchEmailCommand(String[] emails) {
		final Set<String> emailsSet = new HashSet<>(Arrays.asList(emails));
		SearchEmailCommand command = new SearchEmailCommand(emailsSet);
		command.setData(addressBook, Collections.emptyList());
		return command;
	}
}