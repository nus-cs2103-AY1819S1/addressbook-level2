package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.addressbook.data.person.ReadOnlyPerson;

/**
 * Finds and lists all persons whose phone number equals to the number(s) specified.
 */
public class FindPhoneCommand extends Command {

    public static final String COMMAND_WORD = "findPhone";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds and lists all persons whose phone number "
            + "equals to the number(s) specified and displays them as a list with index numbers.\n"
            + "Parameters: PHONE_NUMBER [MORE_PHONE_NUMBERS]...\n"
            + "Example: " + COMMAND_WORD + " 81234567 99999999";

    private final Set<String> phoneNumbers;

    public FindPhoneCommand(Set<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    /**
     * Returns a copy of phone numbers in this command.
     */
    public Set<String> getPhoneNumbers() {
        return new HashSet<>(phoneNumbers);
    }

    @Override
    public CommandResult execute() {
        final List<ReadOnlyPerson> personsFound = getPersonsWithPhoneNumber(phoneNumbers);
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    /**
     * Retrieves all persons in the address book whose phone number equals to the number(s) specified.
     *
     * @param phoneNumbers for searching
     * @return list of persons found
     */
    private List<ReadOnlyPerson> getPersonsWithPhoneNumber(Set<String> phoneNumbers) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            final Set<String> personPhone = new HashSet<>();
            personPhone.add(person.getPhone().toString());
            if (!Collections.disjoint(personPhone, phoneNumbers)) {
                matchedPersons.add(person);
            }
        }
        return matchedPersons;
    }

}