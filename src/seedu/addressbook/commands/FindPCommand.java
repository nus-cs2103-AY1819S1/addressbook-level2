package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.addressbook.data.person.ReadOnlyPerson;

/**
 * Find all the persons whose phone number contains the number that user has entered.
 */
public class FindPCommand extends Command {

    public static final String COMMAND_WORD = "findP";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Find all persons whose phone number contain "
            + "the number that user has entered.\n"
            + "Parameters: PHONENUMBER\n"
            + "Example: " + COMMAND_WORD + " 91234567";

    private final String phoneNo;

    public FindPCommand(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public CommandResult execute() {
        final List<ReadOnlyPerson> personsFound = getPersonsWithNameContainingAnyKeyword(phoneNo);
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    /**
     * Retrieves all persons in the address book who have the same phone number that user entered .
     *
     * @param phoneNo for searching
     * @return list of persons found
     */
    private List<ReadOnlyPerson> getPersonsWithNameContainingAnyKeyword(String phoneNo) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            if ((" " + person.getPhone().getPhoneNumber()).equals(phoneNo)) {
                matchedPersons.add(person);
            }
        }
        return matchedPersons;
    }
}
