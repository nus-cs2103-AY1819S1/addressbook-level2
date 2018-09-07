package seedu.addressbook.commands;

import org.junit.Before;
import org.junit.Test;
import seedu.addressbook.common.Utils;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertTrue;

public class CommandTypeTest {
    private HashSet<String> cmdWords = new HashSet<>();

    @Before
    public void setup() {
        cmdWords.add(AddCommand.COMMAND_WORD);
        cmdWords.add(ClearCommand.COMMAND_WORD);
        cmdWords.add(DeleteCommand.COMMAND_WORD);
        cmdWords.add(ExitCommand.COMMAND_WORD);
        cmdWords.add(FindCommand.COMMAND_WORD);
        cmdWords.add(HelpCommand.COMMAND_WORD);
        cmdWords.add(ListCommand.COMMAND_WORD);
        cmdWords.add(ViewCommand.COMMAND_WORD);
        cmdWords.add(ViewAllCommand.COMMAND_WORD);
    }

    @Test
    public void allCommandTypesUnique() {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(CommandType.values())));
    }

    @Test
    public void shouldHaveAllCommands() {
        for (CommandType commandType : CommandType.values()) {
            assertTrue(cmdWords.contains(commandType.commandName()));
            cmdWords.remove(commandType.commandName());
        }
        assertTrue(cmdWords.isEmpty());
    }
}
