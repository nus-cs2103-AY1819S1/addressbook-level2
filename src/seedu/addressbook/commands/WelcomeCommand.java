package seedu.addressbook.commands;

public class WelcomeCommand extends Command {

    public static final String COMMAND_WORD = "welcome";

    @Override
    public CommandResult execute() {
        return new CommandResult("Welcome! This is AddressBook v2., an upgrade over AddressBook v1.! " +
                "Over the next few weeks, you'll get to see more AddressBook versions (v3. and v4.)! Enjoy!");
    }
}
