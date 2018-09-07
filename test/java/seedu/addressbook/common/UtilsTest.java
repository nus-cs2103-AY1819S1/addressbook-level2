package seedu.addressbook.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

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
    /**
     *
     */
    public void elementsAreNull() throws Exception {
        // empty list
        assertNoNull();

        // single object no null
        assertNoNull("");
        assertNoNull("abc");
        assertNoNull(1);
        assertNoNull(true);
        assertNoNull(false);

        // all objects without null
        assertNoNull("abc", "ab", "a");
        assertNoNull(1, 2);
        assertNoNull("abc", "abc");
        assertNoNull("abc", "", "abc", "ABC");
        assertNoNull("", "abc", "a", "abc");
        assertNoNull(1, Integer.valueOf(1));

        // some objects with null
        assertContainsNull(null, 1, Integer.valueOf(1));
        assertContainsNull(null, null);
        assertContainsNull(null, "a", "b", null);

        // null with Object typecast
        assertContainsNull((Object) null);

        // null without typecast
        assertContainsNull(null);
    }

    private void assertContainsNull(Object... objects) {
        assertTrue(Utils.isAnyNull(objects));
    }

    private void assertNoNull(Object... objects) {
        assertFalse(Utils.isAnyNull(objects));
    }
}
