package seedu.addressbook.common;

public interface Printable {

    String getPrintableString();

    /**
     * Returns a concatenated version of the printable strings of each object.
     */
    default String getPrintableString(Printable... printables) {
        String output = new String();
        for (Printable obj : printables) {
            output = output.concat(obj.toString() + ' ');
        }
        return output.trim();
    }
}
