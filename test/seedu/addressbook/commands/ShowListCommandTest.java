package seedu.addressbook.commands;

        import static org.junit.Assert.assertEquals;
        import org.junit.Before;
        import org.junit.Test;
        import seedu.addressbook.common.Messages;
        import seedu.addressbook.data.AddressBook;
        import seedu.addressbook.data.person.*;
        import seedu.addressbook.util.TestUtil;
        import seedu.addressbook.commands.ShowListCommand;

        import java.util.Collections;
        import java.util.List;
        import java.util.Set;

public class ShowListCommandTest {

    @Test
    public void execute_returnsValidCommandResult() throws Exception {
        Person cj = new Person(new Name("cj"), new Phone("85241253", false),
                new Email("cj@gmail.com", false), new Address("rvrc", false), Collections.emptySet());

        ShowListCommand showListCommand = new ShowListCommand();
        List<ReadOnlyPerson> allPersons = TestUtil.createList(cj);
        AddressBook addressBook = TestUtil.createAddressBook(cj);
        showListCommand.setData(addressBook, allPersons);

        // only cj in addressbook.
        assertEquals(
                String.format(
                        "Current number of people in address book : %1$s!", 1)+"[cj Phone: 85241253 Email: cj@gmail.com Address: rvrc Tags: ]",
                showListCommand.execute().feedbackToUser);


    }
}