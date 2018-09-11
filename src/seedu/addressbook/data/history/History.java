package seedu.addressbook.data.history;
import java.time.LocalTime;

/**
 * Represents a piece of history in the history file.
 * Guarantees: details are present and not null, field values are validated and immutable.
 */
public abstract class History {

    LocalTime time;

    /**
     * assumption: the time is not null
     * @param time
     */
    public History(LocalTime time) {
        this.time = time;
    }

    public LocalTime getTime() {
        return time;
    }

    public abstract String generateMessage();

}