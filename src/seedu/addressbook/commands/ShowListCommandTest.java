package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import seedu.addressbook.common.Messages;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ShowListCommandTest {

    @Test
    public void execute_returnsValidCommandResult() throws Exception {
        Person chenJuan = new Person(new Name("Chen Juan"), new Phone("85241253", false),
                new Email("ChenJuan@gmail.com", false), new Address("rvrc", false), Collections.emptySet());
        Person chenJuan2 = new Person(new Name("Chen Juan2"), new Phone("85241254", false),
                new Email("ChenJuan2@gmail.com", false), new Address("rvrc2", false), Collections.emptySet());
        Person chenJuan3 = new Person(new Name("Chen Juan3"), new Phone("85241255", false),
                new Email("ChenJuan3@gmail.com", false), new Address("rvrc3", false), Collections.emptySet());
        Person chenJuan4 = new Person(new Name("Chen Juan4"), new Phone("85241256", false),
                new Email("ChenJuan4@gmail.com", false), new Address("rvrc4", false), Collections.emptySet());

        ShowListCommand showListCommand = new ShowListCommand();
        List<ReadOnlyPerson> allPersons = new List<ReadOnlyPerson>();
        allPersons.add(chenJuan);
        AddressBook addressBook = new AddressBook();
        addressBook.addPerson(chenJuan);
        showListCommand.setData(addressBook, allPersons);

        // currently 1 people
        assertEquals(String.format(
                "Current number of people in address book : %1$s!", 1),
                showListCommand.execute().feedbackToUser);


    }
}