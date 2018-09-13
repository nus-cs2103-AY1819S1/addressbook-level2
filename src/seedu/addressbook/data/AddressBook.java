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

    /**
     * Creates an empty address book.
     */
    public AddressBook() {
        allPersons = new UniquePersonList();
    }

    /**
     * Constructs an address book with the given data.
     *
     * @param persons external changes to this will not affect this address book
     */
    public AddressBook(UniquePersonList persons) {
        this.allPersons = new UniquePersonList(persons);
    }

    /**
     * Adds a person to the address book.
     *
     * @throws DuplicatePersonException if an equivalent person already exists.
     */
    public void addPerson(Person toAdd) throws DuplicatePersonException {
        allPersons.add(toAdd);
    }

    /**
     * Adds a person to the address book.
     * This method is mean to be used with {@code editPerson(Person toEdit)}.
     * It silences {@code DuplicatePersonException} since this method is
     * called after {@code PersonNotFoundException} is thrown.
     * Both exceptions are mutually exclusive.
     *
     */
    private void addPersonSafe(Person toAdd) {
        try {
            addPerson(toAdd);
        } catch(DuplicatePersonException dpe) {
            // Will not be run
        }
    }

    /**
     * Edits a person to the address book.
     */
    public void editPerson(Person toEdit) {
        try {
            // Remove the person with the same name and phone number
            removePersonShallow(toEdit);
            addPersonSafe(toEdit);
        } catch (PersonNotFoundException pnfe) {
            addPersonSafe(toEdit);
        }
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
    }

    /**
     * Removes the equivalent person from the address book.
     * This method differs from {@code removePerson(ReadOnlyPerson toRemove)} in that
     * the person is only compared based on their name and phone number.
     *
     * @throws PersonNotFoundException if no such Person could be found.
     */
    public void removePersonShallow(ReadOnlyPerson toRemove) throws PersonNotFoundException {
        allPersons.removeShallow(toRemove);
    }

    /**
     * Returns the number of persons in the address book.
     *
     * @return number of persons in the address book.
     */
    public int size() {
        return allPersons.size();
    }

    /**
     * Clears all persons and tags from the address book.
     */
    public void clear() {
        allPersons.clear();
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
