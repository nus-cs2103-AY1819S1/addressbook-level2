package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TypicalPersons;

public class SearchNumberCommandTest {

  private final AddressBook addressBook = new TypicalPersons().getTypicalAddressBook();
  private final TypicalPersons td = new TypicalPersons();

  @Test
  public void execute() {

    //number not belonging to anyone: not matched
    assertSearchCommandBehavior("98472637", Collections.emptyList());

    //number belonging to one user (Amy): matched
    assertSearchCommandBehavior("91119111", Arrays.asList(td.amy));



  }

  /**
   * Executes the find command for the given keywords and verifies
   * the result matches the persons in the expectedPersonList exactly.
   */
  private void assertSearchCommandBehavior(String keyword, List<ReadOnlyPerson> expectedPersonList) {
    SearchNumberCommand command = createSearchNumberCommand(keyword);
    CommandResult result = command.execute();

    assertEquals(Command.getMessageForPersonListShownSummary(expectedPersonList), result.feedbackToUser);
  }

  private SearchNumberCommand createSearchNumberCommand(String keyword) {
    SearchNumberCommand command = new SearchNumberCommand(keyword);
    command.setData(addressBook, Collections.emptyList());
    return command;
  }

}
