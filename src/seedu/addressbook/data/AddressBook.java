package seedu.addressbook.data;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.data.person.UniquePersonList.DuplicatePersonException;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;

/**
 * Represents the entire address book. Contains the data of the address book.
 */
public class AddressBook {

    private final UniquePersonList allPersons;
    private static int numOfPersons;
    /**
     * Creates an empty address book.
     */
    public AddressBook() {
        allPersons = new UniquePersonList();
        numOfPersons = 0;
    }

    /**
     * Constructs an address book with the given data.
     *
     * @param persons external changes to this will not affect this address book
     */
    public AddressBook(UniquePersonList persons) {
        numOfPersons = getSize(persons);
        this.allPersons = new UniquePersonList(persons);
    }

    /**
     * Returns the number of elements in the container behind an iterable.
     */
    public static <T> int getSize(Iterable<T> it) {
        int numberOfElementsSeen = 0;

        for (T elem : it) {
            numberOfElementsSeen++;
        }

        return numberOfElementsSeen;
    }

    /**
     * Adds a person to the address book.
     *
     * @throws DuplicatePersonException if an equivalent person already exists.
     */
    public void addPerson(Person toAdd) throws DuplicatePersonException {
        allPersons.add(toAdd);
        numOfPersons++;
    }

    /**
     * Returns true if an equivalent person exists in the address book.
     */
    public boolean containsPerson(ReadOnlyPerson key) {
        return allPersons.contains(key);
    }

    /**
     * Removes the equivalent person from the address book.
     *
     * @throws PersonNotFoundException if no such Person could be found.
     */
    public void removePerson(ReadOnlyPerson toRemove) throws PersonNotFoundException {
        allPersons.remove(toRemove);
        numOfPersons--;
    }

    /**
     * Clears all persons and tags from the address book.
     */
    public void clear() {
        numOfPersons = 0;
        allPersons.clear();
    }

    /**
     * Returns the number of persons in the address book.
     */
    public int getNumOfPersons() {
        return numOfPersons;
    }

    /**
     * Returns a new UniquePersonList of all persons in the address book at the time of the call.
     */
    public UniquePersonList getAllPersons() {
        return new UniquePersonList(allPersons);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                        && this.allPersons.equals(((AddressBook) other).allPersons));
    }
}
