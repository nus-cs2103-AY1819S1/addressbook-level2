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
        // No arguments (implied no null value)
        assertNotHasNull();

        // Singular arguments

        // singular null argument
        assertHasNull((Object)null);

        // singular non-null argument
        assertNotHasNull(1);

        // Multiple arguments

        // arguments including a single null value
        assertHasNull(1, 2, "Test", null);

        // arguments including a multiple null values
        assertHasNull(null, 1, 2, "Test", null, null);

        // arguments without null value
        assertNotHasNull(1, 2, "Test");
    }

    private void assertHasNull(Object... objects) {
        assertTrue(Utils.isAnyNull(objects));
    }

    private void assertNotHasNull(Object... objects) {
        assertFalse(Utils.isAnyNull(objects));
    }

}
