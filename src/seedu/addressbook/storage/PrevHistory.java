package seedu.addressbook.storage;

import java.util.Iterator;
import java.util.LinkedList;

public class PrevHistory {

    private LinkedList<String> prevCommands;

    public PrevHistory() {
        prevCommands = new LinkedList<>();
    }

    public void add(String command) {
        if (command.equals("history")) {
            return;
        }
        prevCommands.add(command);
        if (prevCommands.size() > 5) {
            prevCommands.poll();
        }
    }

    public String getHistory() {
        if (prevCommands.isEmpty()) {
            return "No History";
        }
        String history = "Previous Commands: \n";
        Iterator it = prevCommands.iterator();
        while (it.hasNext()) {
            history += "-- " + it.next() + "\n";
        }
        return history.trim();
    }

}
