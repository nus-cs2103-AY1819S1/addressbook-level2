package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.addressbook.data.person.ReadOnlyPerson;

public class SearchEmailCommand {
	public static final String COMMAND_WORD = "searchEmail";

	public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds and lists persons with email that match with email(s) being input and render an indexed list.\n"
	     + "Parameters: EMAIL [ADDITIONAL_EMAILS]...\n"
	     + "Example: " + COMMAND_WORD + " me@gmail.com you@gmail.com";

	private final Set<String> emails;
	
	public SearchEmailCommand(Set<String> emails) {
	 	this.emails = emails;
	}

	@Override
	public CommandResult execute() {
		final List<ReadOnlyPerson> persons = searchPersonsWithEmail(emails);
		return new CommandResult(getMessageForPersonListShownSummary(persons), persons);
	}

	/**
	* Returns emails in this command.
	*/
	public Set<String> getEmails() {
		return new HashSet<>(emails);
	}

	/**
	* Finds and returns persons with email that match with emails specified
	*
	* @param emails for searching
	* @return list of person(s) with matching email(s)
	*/
	private List<ReadOnlyPerson> searchPersonsWithEmail(Set<String> emails) {
		final List<ReadOnlyPerson> results = new ArrayList<>();
		for (ReadOnlyPerson person : addressBook.getAllPersons()) {
			final Set<String> personEmail = new HashSet<>();
			personEmail.add(person.getPhone().toString());
			if (!Collections.disjoint(personEmail, emails)) {
				results.add(person);
			}
		}
		return results;
	}
}