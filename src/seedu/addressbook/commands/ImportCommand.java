package seedu.addressbook.commands;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.storage.StorageFile;
import seedu.addressbook.storage.StorageFile.InvalidStorageFilePathException;

public class ImportCommand extends Command{

    public static final String DEFAULT_BACKUP_FILEPATH = "backup.txt";

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Import previously saved address book and merge with " +
            "the current address book.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Import successful!";

    public static final String MESSAGE_FAILURE = "Import failed";

    private StorageFile storage;

    @Override
    public CommandResult execute() {
        try {
            storage = new StorageFile(DEFAULT_BACKUP_FILEPATH);
            AddressBook oldData = storage.load();
            oldData.getAllPersons().forEach(data -> {
                try {
                    addressBook.addPerson(data);
                } catch (UniquePersonList.DuplicatePersonException e) {
                    System.out.println("|| Duplicate persons detected: " + data.toString());
                }
            });
        } catch (StorageFile.StorageOperationException | InvalidStorageFilePathException e) {
            System.out.println(e.getMessage());
            return new CommandResult(MESSAGE_FAILURE);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
