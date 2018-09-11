package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;


/**
 * Lists all persons in the address book to the user.
 */
public class StatsCommand extends Command {

    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows statistics of the address book.\n"
            + "Example: " + COMMAND_WORD;
    
    /**
     * Executes the command and returns the result.
     */
    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        return new CommandResult(
                createStatsMessage(allPersons)
                + "\n" + Messages.MESSAGE_STATS_LISTED_OVERVIEW);
    }

    /**
     * Formats the stats message that informs the user of statistics.
     * @param allPersons The list of persons to read from.
     * @return A string representation of the statistics of allPersons.
     */
    public String createStatsMessage(List<ReadOnlyPerson> allPersons) {
        int numberOfPeople = allPersons.size();
        String isOrAre = numberOfPeople == 1 ? "is" : "are";
        String peopleOrPerson = numberOfPeople == 1 ? "person" : "people";
        return String.format(
                Messages.MESSAGE_STATS_NUMBER_OF_PERSONS, isOrAre, numberOfPeople, peopleOrPerson);
    }
}
