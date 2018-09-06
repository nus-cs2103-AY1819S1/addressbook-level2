package seedu.addressbook.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class UtilsTest {


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

    @Test
    public void elementsAreNull() throws Exception {
        // empty list
        assertNotNull();

        // only one object is null
        assertAreNull((Object) null);

        // all objects null
        assertAreNull(null, null, null);
        assertAreNull(null, null);

        // some objects are null
        assertAreNull("abc", "abc",null);
        assertAreNull("abc", "", "abc", "ABC", null);
        assertAreNull("", "abc", "a", "abc", null);

        //all not null
        assertNotNull(1, Integer.valueOf(1));
        assertNotNull("abc", 1, Integer.valueOf(1));
        assertNotNull("a", "b");
        assertNotNull("abc", "a", "b");
    }

    private void assertAreNull(Object... objects) {

        assertTrue(Utils.isAnyNull(objects));
    }

    private void assertNotNull(Object... objects) {

        assertFalse(Utils.isAnyNull(objects));
    }
}
