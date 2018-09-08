package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;

public class SortCommand extends Command {
  public static final String COMMAND_WORD = "sort";

  public static final String MESSAGE_USAGE = COMMAND_WORD
      + ": Sorts all persons in the address book by name.\n"
      + "Example: " + COMMAND_WORD;

  @Override
  public CommandResult execute() {
    UniquePersonList allPersonsUnsorted = addressBook.getAllPersons();
    allPersonsUnsorted.sortPersons();
    List<ReadOnlyPerson> allPersonsSorted =allPersonsUnsorted.immutableListView();
  return new CommandResult(getMessageForPersonListShownSummary(allPersonsSorted),allPersonsSorted);
}
}