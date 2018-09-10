package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.addressbook.data.person.ReadOnlyPerson;

public class SearchEmailCommand {
	public static final String COMMAND_WORD = "searchEmails";

	public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds and lists persons with email that match with email(s) being input and render an indexed list.\n"
	     + "Parameters: EMAIL [ADDITIONAL_EMAILS]...\n"
	     + "Example: " + COMMAND_WORD + " me@gmail.com you@gmail.com";

	private final Set<String> emails;
	
	public SearchEmailCommand(Set<String> emails) {
	 	this.emails = emails;
	}

	@Override
	public CommandResult execute() {
		
	}
}