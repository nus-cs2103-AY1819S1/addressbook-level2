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
        // No arguments
        assertNoneNull();

        // One argument
        assertHasNull((Object) null);
        assertNoneNull("1");

        // Multiple arguments, with null
        assertHasNull(1, null, "Hello");
        assertHasNull(null, null);

        // Multiple arguments, without null
        assertNoneNull("1", "");
        assertNoneNull(0, new int[2]);
    }

    private void assertHasNull(Object... items) {
        assertTrue(Utils.isAnyNull(items));
    }

    private void assertNoneNull(Object... items) {
        assertFalse(Utils.isAnyNull(items));
    }
}
