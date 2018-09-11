package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadOnlyPerson;

public class RandomCommand  extends Command {

    public static final String COMMAND_WORD = "random";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Randomly Views the non-private details of the person "
            + "identified by the index number in the last shown person listing.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_VIEW_PERSON_DETAILS = "Viewing person: %1$s";


    public RandomCommand() {
        super();
    }


    @Override
    public CommandResult execute() {
        int max = relevantPersons.size(), min = 1;
        int randomIndex = (int)(Math.random() * ((max-min) + 1)) + min;
        ViewCommand viewCommand = new ViewCommand(randomIndex);
        viewCommand.setData(addressBook, relevantPersons);
        return viewCommand.execute();
    }

}