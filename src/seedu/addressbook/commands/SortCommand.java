package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;

import java.util.Comparator;

public class SortCommand extends Command {
    public enum Field {
        NAME("name"), EMAIL("email"), PHONE("phone");

        private String name;

        Field(String name) {
            this.name = name;
        }

        public static Field fromString(String name) {
            for (Field f : Field.values()) {
                if (f.name.equalsIgnoreCase(name)) {
                    return f;
                }
            }
            return null;
        }


    }
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the address book. "
            + "Parameters: [FIELD: name | email | phone]\n"
            + "Example: " + COMMAND_WORD
            + " email";

    public static final String MESSAGE_SUCCESS = "Address book sorted.";
    public static final String MESSAGE_FIELD_DOES_NOT_EXIST = "This field does not exist.";

    private Field field;

    /**
     * Constructor for SortCommand.
     * @param field The field to sort by
     */
    public SortCommand(Field field) {
        this.field = field;
    }

    /**
     * Convenience constructor for using String to initialize SortCommand.
     * @param string The field to be sorted by
     */
    public SortCommand(String string) throws IllegalArgumentException {
        Field f = Field.fromString(string);
        this.field = f;
    }

    /**
     * Returns whether this command is valid, i.e. has a field.
     * @return boolean that tells whether this command is valid.
     */
    public boolean isValid() {
        return field != null;
    }

    @Override
    public CommandResult execute() {
        if (field == null) {
            return new CommandResult(MESSAGE_FIELD_DOES_NOT_EXIST);
        }
        addressBook.sortPersons(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if (field == Field.EMAIL) {
                    return o1.getEmail().value.compareToIgnoreCase(o2.getEmail().value);
                } else if (field == Field.NAME) {
                    return o1.getName().fullName.compareToIgnoreCase(o2.getName().fullName);
                } else if (field == Field.PHONE) {
                    return o1.getPhone().value.compareToIgnoreCase(o2.getPhone().value);
                } else {
                    return 1;
                }
            }
        });
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
