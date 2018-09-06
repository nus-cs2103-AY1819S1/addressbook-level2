package seedu.addressbook.common;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void isAnyNull() throws Exception {
        //Solution below adapted from the current file, UtilsTest.java (line 44-64) and from
        //https://stackoverflow.com/questions/7123555/assertnull-should-be-used-or-assertnotnull

        // empty
        assertNotNull();
        assertNotNull("");

        // check null or mix of null with object
        assertNull(null,null);
        assertNull("123",null);
        assertNull((Object) null);
        assertNull("test",null,null);

        // check not null
        assertNotNull(2);
        assertNotNull("abc","123");
        assertNotNull("def");

    }

    private void assertNull(Object... objects) {
        assertTrue(Utils.isAnyNull(objects));
    }

    private void assertNotNull(Object... objects) {
        assertFalse(Utils.isAnyNull(objects));
    }

    @Test
    public void elementsAreUnique() throws Exception {
        // empty list
        assertAreUnique();

        // only one object
        assertAreUnique((Object) null);
        assertAreUnique(1);
        assertAreUnique("");
        assertAreUnique("abc");

        // all objects unique
        assertAreUnique("abc", "ab", "a");
        assertAreUnique(1, 2);

        // some identical objects
        assertNotUnique("abc", "abc");
        assertNotUnique("abc", "", "abc", "ABC");
        assertNotUnique("", "abc", "a", "abc");
        assertNotUnique(1, Integer.valueOf(1));
        assertNotUnique(null, 1, Integer.valueOf(1));
        assertNotUnique(null, null);
        assertNotUnique(null, "a", "b", null);
    }

    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }
}
