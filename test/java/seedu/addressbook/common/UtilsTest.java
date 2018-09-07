package seedu.addressbook.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class UtilsTest {


    @Test
    public void elementsHasNull() {
        // empty list
        assertNoNull();

        // only one non-null object
        assertNoNull(1);
        assertNoNull("");
        assertNoNull("abc");

        // only one null object
        assertHasNull((Object) null);

        // multiple objects with no null
        assertNoNull("abc", "abc");
        assertNoNull("abc", "", "abc", "ABC");
        assertNoNull("", "abc", "a", "abc");
        assertNoNull(1, Integer.valueOf(1));

        // multiple objects with null
        assertHasNull(null, 1, Integer.valueOf(1));
        assertHasNull(null, null);
        assertHasNull(null, "a", "b", null);

    }

    private void assertHasNull(Object... objects) { assertTrue(Utils.isAnyNull(objects)); }

    private void assertNoNull(Object... objects) { assertFalse(Utils.isAnyNull(objects)); }

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
