package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class DeleteAllCommand extends Command {
    public static final String COMMAND_WORD = "deleteAll";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes all persons whose names contain any of "
            + "the specified keywords (case-sensitive). Removes the last listing.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " Doe";

    private final Command finder;

    public DeleteAllCommand(Set<String> keywords) {
        finder = new FindCommand(keywords);
    }


    @Override
    public CommandResult execute() {
        finder.setData(addressBook, relevantPersons);
        CommandResult found = finder.execute();
        final Optional<List<? extends ReadOnlyPerson>> personList = found.getRelevantPersons();
        StringBuilder output = new StringBuilder();
        if (personList.isPresent()) {
            List<? extends ReadOnlyPerson> list = personList.get();
            DeleteCommand deleter = new DeleteCommand(0);
            deleter.setData(addressBook, list);
            final int numElem = list.size();
            for (int i = 1; i <= numElem; i++) {
                deleter.setTargetIndex(i);
                output.append(deleter.execute().feedbackToUser);
                output.append("\n");
            }
        }
        return new CommandResult(output.toString());
    }


}
