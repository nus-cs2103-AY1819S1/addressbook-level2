package seedu.addressbook.storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.history.History;

/**
 * Represents the file used to store address book data.
 */
public class StorageFile {

    /** Default file path used if the user doesn't provide the file name. */
    public static final String DEFAULT_STORAGE_FILEPATH = "addressbook.txt";
    /** Default file path used for history. */
    public static final String DEFAULT_HISTORY_STORAGE_FILEPATH = "history.txt";

    /* Note: Note the use of nested classes below.
     * More info https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html
     */

    /**
     * Signals that the given file path does not fulfill the storage filepath constraints.
     */
    public static class InvalidStorageFilePathException extends IllegalValueException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }

    /**
     * Signals that some error has occured while trying to convert and read/write data between the application
     * and the storage file.
     */
    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }

    public final Path path;
    public final Path historyPath;

    /**
     * @throws InvalidStorageFilePathException if the default path is invalid
     */
    public StorageFile() throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_FILEPATH, DEFAULT_HISTORY_STORAGE_FILEPATH);
    }


    /**
     * @throws InvalidStorageFilePathException if the given file path is invalid
     */
    public StorageFile(String filePath) throws InvalidStorageFilePathException {
        this(filePath, DEFAULT_HISTORY_STORAGE_FILEPATH);
    }

    /**
     * @throws InvalidStorageFilePathException if the given file path is invalid
     */
    public StorageFile(String filePath, String historyPath) throws InvalidStorageFilePathException {
        path = Paths.get(filePath);
        this.historyPath = Paths.get(historyPath);
        if (!isValidPath(path) || !isValidPath(this.historyPath)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
        }
    }

    /**
     * Returns true if the given path is acceptable as a storage file.
     * The file path is considered acceptable if it ends with '.txt'
     */
    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    /**
     * Saves the {@code addressBook} data to the storage file.
     *
     * @throws StorageOperationException if there were errors converting and/or storing data to file.
     */
    public void save(AddressBook addressBook) throws StorageOperationException {
        try {
            List<String> encodedAddressBook = AddressBookEncoder.encodeAddressBook(addressBook);
            Files.write(path, encodedAddressBook);
            saveHistory(addressBook.getAllHistories());
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + path);
        }
    }

    /**
     * Saves the history to the history file.
     */
    public void saveHistory(LinkedList<History> histories) throws StorageOperationException {
        try {
            List<String> stringHistories = HistoryEncoder.encodeHistoryToString(histories);
            Files.write(historyPath, stringHistories);
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + historyPath);
        }
    }

    /**
     * Loads the {@code AddressBook} data from this storage file, and then returns it.
     * Returns an empty {@code AddressBook} if the file does not exist, or is not a regular file.
     *
     * @throws StorageOperationException if there were errors reading and/or converting data from file.
     */
    public AddressBook load() throws StorageOperationException {

        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return new AddressBook();
        }

        try {
            return AddressBookDecoder.decodeAddressBook(Files.readAllLines(path));
        } catch (FileNotFoundException fnfe) {
            throw new AssertionError("A non-existent file scenario is already handled earlier.");
        // other errors
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + path);
        } catch (IllegalValueException ive) {
            throw new StorageOperationException("File contains illegal data values; data type constraints not met");
        }
    }

    /**
     * Loads the hisotry data from the history file.
     *
     * @throws StorageOperationException if there were errors reading and/or converting data from file.
     */
    public void loadHistory(AddressBook addressBook) throws StorageOperationException {

        try {
            addressBook.initializeHistory(HistoryDecoder.decodeHistories((Files.readAllLines(historyPath)), addressBook.getAllPersons()));
        } catch (FileNotFoundException fnfe) {
            throw new AssertionError("A non-existent file scenario is already handled earlier.");
            // other errors
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + path);
        } catch (IllegalValueException ive) {
            throw new StorageOperationException("File contains illegal data values; data type constraints not met");
        }
    }

    public String getPath() {
        return path.toString();
    }

    public String getHistoryPath() {
        return historyPath.toString();
    }

}
