package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Sorts and list everyone in the address book.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book as a list with index "
            + "numbers sorted according to the the specified field.\n"
            + "Example: " + COMMAND_WORD + " name";

    private final String keyword;

    public SortCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns a copy of keyword in this command.
     */
    public String getKeyword() {
        return keyword;
    }

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersonsSorted = addressBook.getAllPersons()
                .immutableListView()
                .stream()
                .sorted(getComparator())
                .collect(Collectors.toList());

        return new CommandResult(getMessageForPersonListShownSummary(allPersonsSorted), allPersonsSorted);
    }

    /**
     * Returns a first level comparator used for sorting the results.
     *
     * @return Comparator used to sort the result
     */
    private Comparator<ReadOnlyPerson> getComparator() {
        switch (keyword) {
            case "phone":
                return (ReadOnlyPerson x, ReadOnlyPerson y) -> x.getPhone()
                        .value
                        .compareTo(y.getPhone().value);

            case "email":
                return (ReadOnlyPerson x, ReadOnlyPerson y) -> x.getEmail()
                        .value
                        .compareTo(y.getEmail().value);

            case "address":
                return (ReadOnlyPerson x, ReadOnlyPerson y) -> x.getAddress()
                        .value
                        .compareTo(y.getAddress().value);

            case "name":
            default:
                return (ReadOnlyPerson x, ReadOnlyPerson y) -> x.getName()
                        .fullName
                        .compareTo(y.getName().fullName);
        }
    }
}