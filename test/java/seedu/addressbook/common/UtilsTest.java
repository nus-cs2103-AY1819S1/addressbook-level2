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

    @Test
    public void elementsAreNull() throws Exception {
        // empty list
        assertNotNullInList();

        // only one object, null
        assertNullInList((Object) null);

        // only one object, not null
        assertNotNullInList(1);
        assertNotNullInList("");
        assertNotNullInList("abc");

        // more than one item, one null
        assertNullInList("abc", "ab", null);
        assertNullInList(null, 2);

        // more than one item, more than one null
        assertNullInList(null, null);
        assertNullInList(null, "a", "b", null);

        // more than one item, no null
        assertNotNullInList("abc", "ab", "a");
        assertNotNullInList(1, 2);
        assertNotNullInList("abc", "", "abc", "ABC");

    }

    // not tested, just methods
    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNullInList(Object... objects) {
        assertTrue(Utils.isAnyNull(objects));
    }

    private void assertNotNullInList(Object... objects) {
        assertFalse(Utils.isAnyNull(objects));
    }
}
