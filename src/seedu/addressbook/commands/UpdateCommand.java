package seedu.addressbook.commands;

public class UpdateCommand {
    public static final String COMMAND_WORD = "update";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates details of the person " +
            "identified by the index number used in the last person listing.\n"
            + "Parameters: INDEX NAME [p]p/PHONE [p]e/EMAIL [p]a/ADDRESS  [t/TAG]...\n"
            + "Example: " + COMMAND_WORD +
            " 1 John Smith p/11111111 e/john@email.com a/21st Orchard Rd, #12-02 Garden Towers t/friends";
    public static final String MESSAGE_SUCCESS = "%1$s updated.";
}
