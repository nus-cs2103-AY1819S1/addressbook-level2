package seedu.addressbook.commands;

import com.sun.org.apache.xpath.internal.compiler.Keywords;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.*;

public class NumCommand extends Command {

    public static final String COMMAND_WORD = "num";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose number contain any of "
            + "the specified number and displays them as a list with index numbers.\n"
            + "Parameters: 8-digit numbers\n"
            + "Example: " + COMMAND_WORD + " 12345678";

    private static final String number;

    public NumCommand (String num){
        this.number = num;
    }

    @Override
    public CommandResult execute() {
        final List<ReadOnlyPerson> personsFound = getPersonWithNumber(number);
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    /**
     * Retrieves all persons in the address book who has a particular 8-digit number
     *
     * @param number for searching
     * @return list of persons found
     */
    private List<ReadOnlyPerson> getPersonWithNumber(String number) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            if(person.getPhone().toString() == number) {
                matchedPersons.add(person);
            }
        }
        return matchedPersons;
    }

}
