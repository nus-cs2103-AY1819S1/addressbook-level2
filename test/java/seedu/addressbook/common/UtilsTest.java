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
        assertHasNull((Object) null);
        assertAreUnique(1);
        assertNoNull(1);
        assertAreUnique("");
        assertNoNull("");
        assertAreUnique("abc");
        assertNoNull("abc");

        // all objects unique
        assertAreUnique("abc", "ab", "a");
        assertNoNull("abc", "ab", "a");
        assertAreUnique(1, 2);
        assertNoNull(1,2);

        // some identical objects
        assertNotUnique("abc", "abc");
        assertNoNull("abc", "abc");
        assertNotUnique("abc", "", "abc", "ABC");
        assertNoNull("abc", "abc", "ABC");
        assertNotUnique("", "abc", "a", "abc");
        assertNoNull("", "abc", "a", "abc");
        assertNotUnique(1, Integer.valueOf(1));
        assertNoNull(1, Integer.valueOf(1));
        assertNotUnique(null, 1, Integer.valueOf(1));
        assertHasNull(null, 1, Integer.valueOf(1));
        assertNotUnique(null, null);
        assertHasNull(null, null);
        assertNotUnique(null, "a", "b", null);
        assertHasNull(null, "a", "b", null);
    }

    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertHasNull(Object... objects) { assertTrue(Utils.isAnyNull(objects)); };

    private void assertNoNull(Object... objects) { assertFalse(Utils.isAnyNull(objects)); };
}