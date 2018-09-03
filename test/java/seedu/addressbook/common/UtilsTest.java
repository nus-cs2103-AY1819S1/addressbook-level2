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
    public void isAnyNull() {
        // empty list
        assertNoneNull();

        // one non-null object
        assertNoneNull(1);
        assertNoneNull("");
        assertNoneNull("abc");

        // no null objects
        assertNoneNull("abc", "ab", "a");
        assertNoneNull(1, 2);
        assertNoneNull("abc", "abc");
        assertNoneNull("abc", "", "abc", "ABC");
        assertNoneNull("", "abc", "a", "abc");
        assertNoneNull(1, Integer.valueOf(1));

        // one null object
        assertAnyNull((Object) null);
        assertAnyNull(null, 1, Integer.valueOf(1));

        // more than one null object
        assertAnyNull(null, null);
        assertAnyNull(null, "a", "b", null);
    }

    private void assertAnyNull(Object... objects) { assertTrue(Utils.isAnyNull(objects)); }

    private void assertNoneNull(Object... objects) { assertFalse(Utils.isAnyNull(objects)); }
}
