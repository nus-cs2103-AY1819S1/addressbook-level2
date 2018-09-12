package seedu.addressbook.commands;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.Tag;

import java.util.HashSet;
import java.util.Set;

/**
 * Adds a person to the address book.
 */
public class AboutCommand extends Command {

    public static final String COMMAND_WORD = "about";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":abou Shows information about this application";
    public static final String MESSAGE_ABOUT = "This is a CLI (Command Line Interface) Address Book application " +
            "written in OOP fashion.\n" +
            "\n" +
            "It is a Java sample application intended for students learning Software Engineering while using Java " +
            "as the main programming language.\n" +
            "\n" +
            "It provides a reasonably well-written code example that is significantly bigger than what students " +
            "usually write in data structure modules.";

    @Override
    public CommandResult execute() {
        return new CommandResult(String.format(MESSAGE_ABOUT));
    }

}
