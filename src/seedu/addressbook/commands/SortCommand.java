package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;

import java.util.Comparator;
import java.util.List;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the address book alphabetically "
            + "\nExample: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Successfully sorted the addressbook";

    public static final String MESSAGE_ERROR = "Not enough data to sort";

    @Override
    public CommandResult execute() {
        List<Person> allPersons = addressBook.getAllPersons().mutableListView();
        if(allPersons.size() > 1){
            allPersons.sort(Comparator.comparing(o -> o.getName().fullName));
            return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
        }else {
            return new CommandResult(MESSAGE_ERROR);
        }
    }



}
