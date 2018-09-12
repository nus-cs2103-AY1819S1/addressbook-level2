package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.List;

import seedu.addressbook.data.person.ReadOnlyPerson;

/**
 * Finds a contact by his/her phone nymber and displays it for the user to view.
 */

public class SearchNumberCommand extends Command {

  public static final String COMMAND_WORD = "search";

  public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the person with the phone number"
          + " and displays the contact.\n"
          + "Parameters : KEYWORD [NUMBER_TO_SEARCH]...\n"
          + "Example: " + COMMAND_WORD + " 98746372";

  private final String number;

  public SearchNumberCommand(String number) {
    this.number = number;
  }

  public CommandResult execute() {
    final List<ReadOnlyPerson> personFound = executeSearchNumber(number);
    return new CommandResult(getMessageForPersonListShownSummary(personFound), personFound);
  }

  /**
   * Retrieves a person in the address book who has the same number as the specified number.
   *
   * @param number
   * @return list of person found
   */
  private List<ReadOnlyPerson> executeSearchNumber(String number) {

    String keyword = number;

    final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
    for (ReadOnlyPerson person : addressBook.getAllPersons()) {
      if (person.getPhone().toString().equals(keyword)) {
        matchedPersons.add(person);
        return matchedPersons;
      }
    }
    return matchedPersons;
  }



}
