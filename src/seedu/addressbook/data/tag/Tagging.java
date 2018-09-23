package seedu.addressbook.data.tag;

import seedu.addressbook.data.person.Person;

public class Tagging {

    private Person person;
    private Tag tag;
    private int taggings;

    Tagging(Person person, Tag tag, int taggings) {
        this.person = person;
        this.tag = tag;
        this.taggings = taggings;
    }


}
