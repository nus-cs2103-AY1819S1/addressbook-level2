package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TestUtil;
import seedu.addressbook.util.TypicalPersons;

public class SortCommandTest {
  private AddressBook unsortedAddressBook;
  private AddressBook sortedAddressBook;
  private final TypicalPersons td = new TypicalPersons();



  @Test
  public void execute() {
    // empty addressBook so sorted is empty
    unsortedAddressBook = TestUtil.createAddressBook();
    sortedAddressBook = TestUtil.createAddressBook();
    assertSortCommandBehaviour(unsortedAddressBook,sortedAddressBook, Collections.emptyList());

    // one person in unsorted addressBook so sorted is unsorted
    unsortedAddressBook=TestUtil.createAddressBook(td.dan);
    sortedAddressBook=TestUtil.createAddressBook(td.dan);
    assertSortCommandBehaviour(unsortedAddressBook,sortedAddressBook, Arrays.asList(td.dan));

    // two person in unsorted addressBook so sorted is sorted version of unsorted
    unsortedAddressBook=TestUtil.createAddressBook(td.dan,td.amy);
    sortedAddressBook=TestUtil.createAddressBook(td.amy,td.dan);
    assertSortCommandBehaviour(unsortedAddressBook,sortedAddressBook, Arrays.asList(td.amy,td.dan));

    // three person case of unsorted addressBook
    unsortedAddressBook=TestUtil.createAddressBook(td.dan,td.candy,td.amy);
    sortedAddressBook=TestUtil.createAddressBook(td.amy,td.candy,td.dan);
    assertSortCommandBehaviour(unsortedAddressBook,sortedAddressBook, Arrays.asList(td.amy,td.candy,td.dan));

    // four person case of unsorted addressBook
    unsortedAddressBook=TestUtil.createAddressBook(td.dan,td.candy,td.bill,td.amy);
    sortedAddressBook=TestUtil.createAddressBook(td.amy,td.bill,td.candy,td.dan);
    assertSortCommandBehaviour(unsortedAddressBook,sortedAddressBook, Arrays.asList(td.amy,td.bill,td.candy,td.dan));

    //when half of addressBook is sorted
    unsortedAddressBook=TestUtil.createAddressBook(td.candy,td.dan,td.amy,td.bill);
    sortedAddressBook=TestUtil.createAddressBook(td.amy,td.bill,td.candy,td.dan);
    assertSortCommandBehaviour(unsortedAddressBook,sortedAddressBook, Arrays.asList(td.amy,td.bill,td.candy,td.dan));

    // when unsorted is sorted cases
    unsortedAddressBook=TestUtil.createAddressBook(td.amy,td.bill,td.candy,td.dan);
    sortedAddressBook=TestUtil.createAddressBook(td.amy,td.bill,td.candy,td.dan);
    assertSortCommandBehaviour(unsortedAddressBook,sortedAddressBook, Arrays.asList(td.amy,td.bill,td.candy,td.dan));

  }

  /**
   * Creates a new Sort command.
   *
   */
  private SortCommand createSortCommand() {
    SortCommand command = new SortCommand();
    command.setData(unsortedAddressBook, Collections.emptyList());
    return command;

  }
  /**
   * Executes the sort command for the unsorted addressBook and verifies
   * the result matches the persons in the expectedPersonList exactly.
   */
  private void assertSortCommandBehaviour(AddressBook unsorted ,AddressBook sorted ,List<ReadOnlyPerson> expectedPersonList){
    SortCommand command = createSortCommand();
    CommandResult result = command.execute();

    assertEquals(unsorted.getAllPersons(), sorted.getAllPersons());
    assertEquals(Command.getMessageForSortedPersonListShownSummary(expectedPersonList), result.feedbackToUser);

  }
}