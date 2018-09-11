package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import seedu.addressbook.common.Messages;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.*;
import seedu.addressbook.util.TestUtil;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class StatsCommandTest {
    
    @Test
    public void execute_returnsValidCommandResult() throws Exception {
        Person johnDoe = new Person(new Name("John Doe"), new Phone("61234567", false),
                new Email("john@doe.com", false), new Address("395C Ben Road", false), Collections.emptySet());
        Person janeDoe = new Person(new Name("Jane Doe"), new Phone("91234567", false),
                new Email("jane@doe.com", false), new Address("33G Ohm Road", false), Collections.emptySet());
        Person samDoe = new Person(new Name("Sam Doe"), new Phone("63345566", false),
                new Email("sam@doe.com", false), new Address("55G Abc Road", false), Collections.emptySet());

        StatsCommand statsCommand = new StatsCommand();
        List<ReadOnlyPerson> allPersons = TestUtil.createList(johnDoe, janeDoe, samDoe);
        AddressBook addressBook = TestUtil.createAddressBook(johnDoe, janeDoe, samDoe);
        statsCommand.setData(addressBook, allPersons);
        
        // 3 people
        assertEquals(
                String.format(Messages.MESSAGE_STATS_NUMBER_OF_PERSONS, "are", 3, "people")
                        + "\n" + Messages.MESSAGE_STATS_LISTED_OVERVIEW,
                statsCommand.execute().feedbackToUser);

        addressBook.removePerson(janeDoe);
        // 2 people
        assertEquals(
                String.format(Messages.MESSAGE_STATS_NUMBER_OF_PERSONS, "are", 2, "people")
                        + "\n" + Messages.MESSAGE_STATS_LISTED_OVERVIEW,
                statsCommand.execute().feedbackToUser);

        addressBook.removePerson(johnDoe);
        // 1 person
        assertEquals(
                String.format(Messages.MESSAGE_STATS_NUMBER_OF_PERSONS, "is", 1, "person")
                        + "\n" + Messages.MESSAGE_STATS_LISTED_OVERVIEW,
                statsCommand.execute().feedbackToUser);

        addressBook.removePerson(samDoe);
        // 0 people
        assertEquals(
                String.format(Messages.MESSAGE_STATS_NUMBER_OF_PERSONS, "are", 0, "people")
                        + "\n" + Messages.MESSAGE_STATS_LISTED_OVERVIEW,
                statsCommand.execute().feedbackToUser);
    }
    
    @Test
    public void createStatsMessage_correctly() throws Exception {
        Person johnDoe = new Person(new Name("John Doe"), new Phone("61234567", false),
                new Email("john@doe.com", false), new Address("395C Ben Road", false), Collections.emptySet());
        Person janeDoe = new Person(new Name("Jane Doe"), new Phone("91234567", false),
                new Email("jane@doe.com", false), new Address("33G Ohm Road", false), Collections.emptySet());
        Person samDoe = new Person(new Name("Sam Doe"), new Phone("63345566", false),
                new Email("sam@doe.com", false), new Address("55G Abc Road", false), Collections.emptySet());
        
        StatsCommand statsCommand = new StatsCommand();
        List<ReadOnlyPerson> personList = TestUtil.createList();
        
        // 0 people
        assertEquals(
                String.format(Messages.MESSAGE_STATS_NUMBER_OF_PERSONS, "are", 0, "people"),
                statsCommand.createStatsMessage(personList));
        
        // 1 person
        personList.add(johnDoe);
        assertEquals(
                String.format(Messages.MESSAGE_STATS_NUMBER_OF_PERSONS, "is", 1, "person"),
                statsCommand.createStatsMessage(personList));

        // 2 people
        personList.add(janeDoe);
        assertEquals(
                String.format(Messages.MESSAGE_STATS_NUMBER_OF_PERSONS, "are", 2, "people"),
                statsCommand.createStatsMessage(personList));
        
        // 2 people
        personList.add(samDoe);
        assertEquals(
                String.format(Messages.MESSAGE_STATS_NUMBER_OF_PERSONS, "are", 3, "people"),
                statsCommand.createStatsMessage(personList));
    }
}
