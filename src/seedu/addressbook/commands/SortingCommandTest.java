 package seedu.addressbook.commands;
 
 import org.junit.Before;
 import org.junit.Test;
 import seedu.addressbook.data.AddressBook;
 import seedu.addressbook.data.person.*;
 import seedu.addressbook.util.TestUtil;
 
 import java.util.Collections;
 import java.util.List;
 
 import static org.junit.Assert.*;
 public class SortCommandTest {
     
 private AddressBook addressBook;
    
 private List<ReadOnlyPerson> sortedList;
     
 public void setUp() throws Exception {
         Person SamAl = new Person(new Name("Sam Al"), new Phone("65234567", false),
                 new Email("sam@al.com", false), new Address("395C Bukit Road", false), Collections.emptySet());
         Person SamUl = new Person(new Name("Sam Ul"), new Phone("95234567", false),
                 new Email("sam@ul.com", false), new Address("33G Bata Road", false), Collections.emptySet());
         Person SamIl = new Person(new Name("Sam Il"), new Phone("65345566", false),
                 new Email("sam@il.com", false), new Address("55G Paku Road", false), Collections.emptySet());
         Person SamEl = new Person(new Name("Sam El"), new Phone("65121122", false),
                new Email("sam@el.com", false), new Address("44H Buana Road", false),
                Collections.emptySet());
        
        addressBook = TestUtil.createAddressBook(SamAl, SamUl, SamIl, SamEl);
        

        sortedList = TestUtil.createList(SamAl, SamEl, SamIl, SamUl);
 }
