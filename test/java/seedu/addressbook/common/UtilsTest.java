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
        assertFalse(Utils.isAnyNull());

        // One null
        assertTrue(Utils.isAnyNull((Object) null));

        // Multiple nulls
        assertTrue(Utils.isAnyNull(null, null, null));
        assertTrue(Utils.isAnyNull((Object) null, (Object) null, (Object) null));
        assertTrue(Utils.isAnyNull((Object) null, null, null));

        // No nulls
        assertFalse(Utils.isAnyNull(1, 2, 3));
        assertFalse(Utils.isAnyNull('a', 'b', 'c'));
        assertFalse(Utils.isAnyNull(1, 'b', 3.1));

        // Null among non-null
        assertTrue(Utils.isAnyNull(null, 2, 3));
        assertTrue(Utils.isAnyNull('a', (Object) null, 3));
    }
}
