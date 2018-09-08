package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.UniquePersonList;

import java.util.Set;

public class StarCommand extends Command {
    public static final String COMMAND_WORD = "star";
    public static final String MESSAGE_USAGE = COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "New person starred: %1$s";

    private final String name;
//    private final Person toStar;

    public StarCommand(String name) {
        this.name = name;
    }

    public CommandResult execute() {
        // retrieve person from addressbook
        // edit the person's star
        // save 
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
