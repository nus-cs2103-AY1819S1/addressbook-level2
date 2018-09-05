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
    public void iaAnyNull() throws Exception {
        // empty list
        assertContainsNoNull();

        // all objects are not null
        assertContainsNoNull("abc", "ab", "a");
        assertContainsNoNull(1, 2);

        // only one null
        assertContainsNull(null, 1);
        assertContainsNull("", null);
        assertContainsNull("", null, "abc");

        // some are null
        assertContainsNull("abc", null, "abc", null, null);
        assertContainsNull("abc", "", "ABC", null);
        assertContainsNull(null, "", "abc", null, "a", "abc");

        // all are null
        assertContainsNull(null, null);
    }

    private void assertContainsNull(Object... objects) {
        assertTrue(Utils.isAnyNull(objects));
    }

    private void assertContainsNoNull(Object... objects) {
        assertFalse(Utils.isAnyNull(objects));
    }
}
