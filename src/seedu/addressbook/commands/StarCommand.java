package seedu.addressbook.commands;

import seedu.addressbook.data.person.UniquePersonList;

public class StarCommand extends Command {
    public static final String COMMAND_WORD = "star";
    public static final String MESSAGE_USAGE = COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "New person starred: %1$s";



    public CommandResult execute() {
        // star the person
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
