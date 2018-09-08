package seedu.addressbook.commands;


import java.util.List;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;

public class SortCommand extends Command {
  public static final String COMMAND_WORD = "sort";

  public static final String MESSAGE_USAGE = COMMAND_WORD
      + ": Sorts all persons in the address book by name in ascending order.\n"
      + "Example: " + COMMAND_WORD;

  @Override
  public CommandResult execute() {
    UniquePersonList allPersonsSortedMutable = addressBook.sortAllPersons();
    List<ReadOnlyPerson> allPersonsSorted =allPersonsSortedMutable.immutableListView();
  return new CommandResult(getMessageForSortedPersonListShownSummary(allPersonsSorted),allPersonsSorted);
}
}