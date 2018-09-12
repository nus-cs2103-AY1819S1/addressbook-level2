package seedu.addressbook.commands;


import java.io.*;

/**
 * Adds a person to the address book.
 */
public class CreateCommand extends Command {

    public static final String COMMAND_WORD = "create";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a new user account. "
            + "Parameters: USERNAME [p]p/PASSWORD \n"
            + "Example: " + COMMAND_WORD
            + " user123 p/password123 ";

    public CreateCommand(String username, String password) {
        System.out.println("Manage to call \"create\" command");
        System.out.println("New User Created: Username: " + username + " Password: " + password);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true));

            writer.write(username + ":" + password + "\n");
            writer.newLine();
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static final String MESSAGE_ACCOUNT_CREATION_SUCCESSFUL = "Successfully created account!"; // Temporary message for testing

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_ACCOUNT_CREATION_SUCCESSFUL);
    }


}