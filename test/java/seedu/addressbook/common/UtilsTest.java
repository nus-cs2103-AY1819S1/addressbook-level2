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
    public void isAnyNull() throws Exception {
        //empty list
        assertDoNotHaveNull();

        // one element( has null)
        assertHasNull((Object) null); //one null object

        // one element (no null)
        assertDoNotHaveNull(1);
        assertDoNotHaveNull("a");
        assertDoNotHaveNull("");

        // 2 elements one null object + one number
        assertDoNotHaveNull("a", 1);
        assertDoNotHaveNull("", 1);
        assertHasNull((Object) null, 1);
        assertHasNull((Object) null, "a");
        assertHasNull((Object) null, "");

        // more elements
        assertDoNotHaveNull("a", 1, "abc");
        assertDoNotHaveNull("", 1, "aaa", 2, 3);
        assertHasNull(1, null, "a");
        assertHasNull(222, null, "a");
        assertHasNull(null, "", "aaa", 333);
    }

    private void assertHasNull(Object... objects) {
        assertTrue(Utils.isAnyNull(objects));
    }

    private void assertDoNotHaveNull(Object... objects) {
        assertFalse(Utils.isAnyNull(objects));
    }


}
