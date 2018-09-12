package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;
import java.util.Random;

/**
 * Generates a random 'lucky person' that the user should contact for fun!
 */
public class RandomCommand extends Command {
  public static final String COMMAND_WORD = "random";
  public static final String MESSAGE_USAGE = COMMAND_WORD + ": Choose a random person in the contact.\n"
    + "Example: " + COMMAND_WORD;
  public static final String MESSAGE_SUCCESS = "Here's a random person! Choose what you want to do to him!\n" +
    "The person is: ";
  public static final String MESSAGE_FAIL = "There is no one in the address book!";

  /**
   * An empty constructor for LuckyCommand
   */
  public RandomCommand() {
  }

  @Override
  public CommandResult execute() {

    Random rnd = new Random();
    List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();

    if (allPersons.size() == 0) {
      return new CommandResult(MESSAGE_FAIL);
    }

    int luckyIndex = rnd.nextInt(allPersons.size()-1);
    ReadOnlyPerson randomPerson = allPersons.get(luckyIndex);
    return new CommandResult(MESSAGE_SUCCESS + randomPerson);
  }

}

