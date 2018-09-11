package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
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
        return new CommandResult("No User Found");
    }

}