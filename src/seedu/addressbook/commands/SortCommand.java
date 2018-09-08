package seedu.addressbook.commands;

import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;

import java.util.Comparator;
import java.util.List;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts all persons in the address book by name and then by phone number"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        UniquePersonList listOfPersonToBeSorted = addressBook.getAllPersons();
        //Compares two persons in the address book first by name and then by phone number
        Comparator<ReadOnlyPerson> comparePersons = (firstPerson, secondPerson) -> {
            Name firstPersonName = firstPerson.getName(), secondPersonName = secondPerson.getName();
            Phone firstPersonPhone = firstPerson.getPhone(), secondPersonPhone = secondPerson.getPhone();
            int firstComparisonMetric = firstPersonName.compareTo(secondPersonName),
                    secondComparisonMetric = firstPersonPhone.compareTo(secondPersonPhone);
            if (firstComparisonMetric != 0) {
                return firstComparisonMetric;
            } else {
                return secondComparisonMetric;
            }
        };
        listOfPersonToBeSorted.sort(comparePersons);
        List<ReadOnlyPerson> allPersons = listOfPersonToBeSorted.immutableListView();
        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
    }
}
