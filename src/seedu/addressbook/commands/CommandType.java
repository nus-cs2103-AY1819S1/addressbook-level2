package seedu.addressbook.commands;

/**
 * Dictionary containing names of all the user commands.
 */
public enum CommandType {

    ADD(AddCommand.COMMAND_WORD), CLEAR(ClearCommand.COMMAND_WORD),
    DELETE(DeleteCommand.COMMAND_WORD), EXIT(ExitCommand.COMMAND_WORD),
    FIND(FindCommand.COMMAND_WORD), HELP(HelpCommand.COMMAND_WORD),
    LIST(ListCommand.COMMAND_WORD), VIEW(ViewCommand.COMMAND_WORD),
    VIEWALL(ListCommand.COMMAND_WORD);

    private final String name;
    CommandType(String name) {
        this.name = name;
    }
}
