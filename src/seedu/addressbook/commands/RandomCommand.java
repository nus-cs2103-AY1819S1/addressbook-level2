package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomCommand extends Command {
    public static final String COMMAND_WORD = "random";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays a random contact.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        int addressBookSize = allPersons.size();

        if (addressBookSize == 0) {
            return new CommandResult(Messages.MESSAGE_NO_PERSONS);
        }

        Random random = new Random();
        int randomIndex = random.nextInt(addressBookSize);
        ReadOnlyPerson randomPerson = allPersons.get(randomIndex);
        List<ReadOnlyPerson> randomPersonList = new ArrayList<>();
        randomPersonList.add(randomPerson);

        return new CommandResult(Messages.MESSAGE_RANDOM_PERSON, randomPersonList);
    }
}