package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;
import static seedu.addressbook.parser.Parser.KEYWORDS_ARGS_FORMAT;

import org.junit.Before;
import org.junit.Test;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;

import seedu.addressbook.util.TestUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.Set;


public class DeleteAllCommandTest {
    private Person[] persons = new Person[4];

    @Before
    public void setUp() throws Exception {
        persons[0] = new Person(new Name("David Grant"), new Phone("61121122", false),
                new Email("david@grant.com", false), new Address("44H Define Road", false),
                Collections.emptySet());
        persons[1]  = new Person(new Name("John Doe"), new Phone("61234567", false),
                new Email("john@doe.com", false), new Address("395C Ben Road", false), Collections.emptySet());
        persons[2] = new Person(new Name("Jane Doe"), new Phone("91234567", false),
                new Email("jane@doe.com", false), new Address("33G Ohm Road", false), Collections.emptySet());
        persons[3] = new Person(new Name("Sam Doe"), new Phone("63345566", false),
                new Email("sam@doe.com", false), new Address("55G Abc Road", false), Collections.emptySet());
    }

    @Test
    public void all_matching_deleted() {
        AddressBook addressBook;
        AddressBook expectedBook;
        List<ReadOnlyPerson> listOfPeople;
        DeleteAllCommand deleteAll;

        //Test 1
        addressBook = TestUtil.createAddressBook(persons[0], persons[1]);
        expectedBook = TestUtil.createAddressBook(persons[0]);
        listOfPeople = TestUtil.createList(persons[1]);
        deleteAll = createDeleteAllCommand("Doe", addressBook, listOfPeople);
        assertCommandBehaviour(deleteAll, listOfPeople, expectedBook, addressBook);

        //Test 2
        addressBook = TestUtil.createAddressBook(persons[0], persons[1], persons[2]);
        expectedBook = TestUtil.createAddressBook(persons[0]);
        listOfPeople = TestUtil.createList(persons[1], persons[2]);
        deleteAll = createDeleteAllCommand("Doe", addressBook, listOfPeople);
        assertCommandBehaviour(deleteAll, listOfPeople, expectedBook, addressBook);

        //Test 3
        addressBook = TestUtil.createAddressBook(persons[0], persons[1], persons[2], persons[3]);
        expectedBook = TestUtil.createAddressBook(persons[0]);
        listOfPeople = TestUtil.createList(persons[1], persons[2], persons[3]);
        deleteAll = createDeleteAllCommand("Doe", addressBook, listOfPeople);
        assertCommandBehaviour(deleteAll, listOfPeople, expectedBook, addressBook);
    }

    @Test
    public void none_deleted() {
        AddressBook addressBook;
        AddressBook expectedBook;
        List<ReadOnlyPerson> listOfPeople;
        DeleteAllCommand deleteAll;

        //Test 1
        addressBook = TestUtil.createAddressBook(persons[1]);
        expectedBook = TestUtil.createAddressBook(persons[1]);
        listOfPeople = TestUtil.createList();
        deleteAll = createDeleteAllCommand("Grant", addressBook, listOfPeople);
        assertCommandBehaviour(deleteAll, listOfPeople, expectedBook, addressBook);

        //Test 2
        addressBook = TestUtil.createAddressBook();
        expectedBook = TestUtil.createAddressBook();
        listOfPeople = TestUtil.createList();
        deleteAll = createDeleteAllCommand("Grant", addressBook, listOfPeople);
        assertCommandBehaviour(deleteAll, listOfPeople, expectedBook, addressBook);
    }

    /**
     * Create a new deleteAll command
     * @param searchString
     * @param addressBook
     * @param displayList
     * @return
     */
    private DeleteAllCommand createDeleteAllCommand(String searchString, AddressBook addressBook,
                                                 List<ReadOnlyPerson> displayList) {

        DeleteAllCommand command = new DeleteAllCommand(parseDeleteAll(searchString));
        command.setData(addressBook, displayList);

        return command;
    }

    /**
     * Executes the command, and checks that the execution was what we had expected.
     */
    private void assertCommandBehaviour(DeleteAllCommand deleteAllCommand, List<ReadOnlyPerson> displayList,
                                        AddressBook expectedAddressBook, AddressBook actualAddressBook) {

        CommandResult result = deleteAllCommand.execute();
        StringBuilder expectedMessage = new StringBuilder();

        for (int i = 0; i < displayList.size(); i++) {
            ReadOnlyPerson targetPerson = displayList.get(i);
            expectedMessage.append(String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, targetPerson));
            expectedMessage.append("\n");
        }
        assertEquals(expectedMessage.toString(), result.feedbackToUser);
        assertEquals(expectedAddressBook.getAllPersons(), actualAddressBook.getAllPersons());
    }

    /**
     * Parses arguments in the context of the delete all command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Set<String> parseDeleteAll(String args) {
        final Matcher matcher = KEYWORDS_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new HashSet<>();
        }

        // keywords delimited by whitespace
        final String[] keywords = matcher.group("keywords").split("\\s+");
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        return keywordSet;
    }
}
