package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.util.TestUtil;


public class EditCommandTest {
  private static final List<ReadOnlyPerson> EMPTY_PERSON_LIST = Collections.emptyList();
  private static final Set<String> EMPTY_STRING_SET = Collections.emptySet();

  @Test
  public void editCommand_validData_CorrectlyDone() {
    Person p = TestUtil.generateTestPerson();
    EditCommand command = new EditCommand(p);
    AddressBook book = new AddressBook();
    command.setData(book, EMPTY_PERSON_LIST);
    CommandResult result = command.execute();
    UniquePersonList people = book.getAllPersons();

    assertTrue(people.contains(p));
    assertEquals(1, people.immutableListView().size());
    assertFalse(result.getRelevantPersons().isPresent());
    assertEquals(String.format(EditCommand.MESSAGE_SUCCESS, p), result.feedbackToUser);
  }
}
