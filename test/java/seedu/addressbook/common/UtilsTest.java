package seedu.addressbook.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.*;
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
    public void isAnyNull() throws Exception {
        assertTrue(Utils.isAnyNull(null, null)); // Test for nulls
        assertTrue(Utils.isAnyNull(1, null));
        assertTrue(Utils.isAnyNull(new String[1]));
        //assertFalse(Utils.isAnyNull(Arrays.asList((Object) null)));
        assertFalse(Utils.isAnyNull(new String[0]));
        assertFalse(Utils.isAnyNull(new List[0]));
        assertFalse(Utils.isAnyNull(1, 2)); // Test for int values
        assertFalse(Utils.isAnyNull("abn", "ab")); // Test for String values
    }
}
