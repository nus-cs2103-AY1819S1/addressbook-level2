package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;

public class ShowListCommand extends Command{

    public static final String COMMAND_WORD = "showList";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows the list of people in address book.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> personList = addressBook.getAllPersons().immutableListView();
        return new CommandResult(setList(personList));

    }

    /**
     * print the number of ppl.
     * @param personList The list of ppl in addressbook.
     * @return A string presenting the number of ppl.
     */
    public String setList(List<ReadOnlyPerson> personList) {
        int numPeople = personList.size();
        return String.format(
                "Current number of people in address book : %1$s!", numPeople) ;
    }
    public String createStatsMessage(List<ReadOnlyPerson> personList) {

        int numPeople = personList.size();
        return String.format(
                "Current number of people in address book : %1$s!", numPeople);
    }

}
