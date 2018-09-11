package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TypicalPersons;

public class SortCommandTest {
	private final AddressBook addressBook = new TypicalPersons().getTypicalAddressBook();
	private final TypicalPersons td = new TypicalPersons();
	
	@Test
	public void execute() {
		assertSortCommandInvalidKey("fgdfgs");
		
		assertSortCommandBehavior("name", getResultAfterSorting(new Comparator<Person>() {
			@Override
			public int compare(Person p1, Person p2) {
				return p1.getName().toString().compareTo(p2.getName().toString());
			}
		}));
		
		assertSortCommandBehavior("email", getResultAfterSorting(new Comparator<Person>() {
			@Override
			public int compare(Person p1, Person p2) {
				return p1.getEmail().toString().compareTo(p2.getEmail().toString());
			}
		}));
		
		assertSortCommandBehavior("address", getResultAfterSorting(new Comparator<Person>() {
			@Override
			public int compare(Person p1, Person p2) {
				return p1.getAddress().toString().compareTo(p2.getAddress().toString());
			}
		}));
	}
	
	private void assertSortCommandBehavior(String keyword, List<ReadOnlyPerson> expectedPersonList) {
        SortCommand command = createSortCommand(keyword);
        CommandResult result = command.execute();

        assertEquals(Command.getMessageForPersonListShownSummary(expectedPersonList), result.getFeedbackToUser());
    }
	
	private void assertSortCommandInvalidKey(String keyword) {
		SortCommand command = createSortCommand(keyword);
		CommandResult result = command.execute();
		
		assertEquals(SortCommand.ILLEGAL_SORTKEY_MESSAGE, result.getFeedbackToUser());
	}
	
	private SortCommand createSortCommand(String keyword) {
        SortCommand command = new SortCommand(keyword);
        command.setData(addressBook, Collections.emptyList());
        return command;
    }
	
	private List<ReadOnlyPerson> getResultAfterSorting(Comparator<? extends Person> sorter) {
		addressBook.sort(new Comparator<Person>() {
			@Override
			public int compare(Person p1, Person p2) {
				return p1.getName().toString().compareTo(p2.getName().toString());
			}
		});
		List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
		return allPersons;
	}
}
