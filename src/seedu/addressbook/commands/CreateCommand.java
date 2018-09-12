package seedu.addressbook.commands;


/**
 * Adds a person to the address book.
 */
public class CreateCommand extends Command {

    public static final String COMMAND_WORD = "create";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a new user account. "
            + "Parameters: USERNAME [p]p/PASSWORD \n"
            + "Example: " + COMMAND_WORD
            + " user123 p/example password ";

    public static final String MESSAGE_SUCCESS = "New user created: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This user already exists.";

//    private final Person toAdd;
//
//    /**
//     * Convenience constructor using raw values.
//     *
//     * @throws IllegalValueException if any of the raw values are invalid
//     */
//    public CreateCommand(String name,
//                      String phone, boolean isPhonePrivate,
//                      String email, boolean isEmailPrivate,
//                      String address, boolean isAddressPrivate,
//                      Set<String> tags) throws IllegalValueException {
//        final Set<Tag> tagSet = new HashSet<>();
//        for (String tagName : tags) {
//            tagSet.add(new Tag(tagName));
//        }
//        this.toAdd = new Person(
//                new Name(name),
//                new Phone(phone, isPhonePrivate),
//                new Email(email, isEmailPrivate),
//                new Address(address, isAddressPrivate),
//                tagSet
//        );
//    }
//
    public CreateCommand() {
        System.out.println("Manage to call \"create\" command");
    }
//
//    public ReadOnlyPerson getPerson() {
//        return toAdd;
//    }
    public static final String MESSAGE_ACCOUNT_CREATION_SUCCESSFUL = "Successfully created account!"; // Temporary message for testing

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_ACCOUNT_CREATION_SUCCESSFUL);
    }


}