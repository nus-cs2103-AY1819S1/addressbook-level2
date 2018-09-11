package seedu.addressbook.data.history;


import java.time.LocalTime;

public class ClearAllHistory extends History {

    int number;

    public ClearAllHistory(LocalTime time, int number) {
        super(time);
        this.number = number;
    }

    @Override
    public String generateMessage() {
        String modifier = " has";
        if(number > 1)
            modifier = "s have";
        return "All the (" +  number + ")person" + modifier + " been removed at " + time.toString();
    }
}
