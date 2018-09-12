package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ListUsersCommand extends Command{

    public static final String COMMAND_WORD = "list_users";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all created users and password.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SUCCESS_MESSAGE = "Users and password listed.";

    @Override
    public CommandResult execute() {

        try {
            File users = new File("users.txt");

            BufferedReader reader = new BufferedReader(new FileReader(users));

            String st;
            while ((st = reader.readLine()) != null) {
                System.out.println(st);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return new CommandResult(SUCCESS_MESSAGE);
    }
}
