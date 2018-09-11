package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.function.Function;

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
                .sorted(Comparator.comparing(getDataValue(keyword)))
                .collect(Collectors.toList());

        return new CommandResult(getMessageForPersonListShownSummary(allPersonsSorted), allPersonsSorted);
    }

    /**
     * Returns a first level comparator used for sorting the results.
     *
     * @return Comparator used to sort the result
     */
    private Function<ReadOnlyPerson, String> getDataValue(String keyword) {
        switch (keyword) {
            case "phone":
                return x -> x.getPhone().value;
            case "email":
                return x -> x.getEmail().value;
            case "address":
                return x -> x.getAddress().value;
            case "name":
                return x -> x.getName().fullName;
            default:
                return null;
        }
    }
}