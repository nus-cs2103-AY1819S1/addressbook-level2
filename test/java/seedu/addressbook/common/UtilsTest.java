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
        //One item, null
        assertTrue(Utils.isAnyNull((Object)null));

        //One item, non null
        assertFalse(Utils.isAnyNull(""));
        assertFalse(Utils.isAnyNull(1));
        assertFalse(Utils.isAnyNull(false));

        //Multiple items, has null
        assertTrue(Utils.isAnyNull("A", null));
        assertTrue(Utils.isAnyNull(null, 1));
        assertTrue(Utils.isAnyNull(false, "", null));

        //Multiple items, all null
        assertTrue(Utils.isAnyNull(null, null));
        assertTrue(Utils.isAnyNull(null, null, null));

        //Multiple items, none null
        assertFalse(Utils.isAnyNull("A", 1));
        assertFalse(Utils.isAnyNull(false, ""));
        assertFalse(Utils.isAnyNull(0, true));
        assertFalse(Utils.isAnyNull(0, true, ""));
    }
}
