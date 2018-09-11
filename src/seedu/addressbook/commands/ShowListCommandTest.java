package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import seedu.addressbook.common.Messages;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ShowListCommandTest {

    @Test
    public void execute_returnsValidCommandResult() throws Exception {
        Person chenJuan = new Person(new Name("Chen Juan"), new Phone("85241253", false),
                new Email("ChenJuan@gmail.com", false), new Address("rvrc", false), Collections.emptySet());

        ShowListCommand showListCommand = new ShowListCommand();
        List<ReadOnlyPerson> allPersons = new ArrayList<ReadOnlyPerson>();
        allPersons.add(chenJuan);
        AddressBook addressBook = new AddressBook();
        addressBook.addPerson(chenJuan);
        showListCommand.setData(addressBook, allPersons);

        // currently 1 people
        assertEquals(String.format(
                "Current number of people in address book : %1$s!", 1)+"[Chen Juan Phone: 85241253 Email: ChenJuan@gmail.com Address: rvrc Tags: ]",
                showListCommand.execute().feedbackToUser);


    }
}