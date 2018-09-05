package seedu.addressbook.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class UtilsTest {
    
    @Test
    public void isAnyNull() throws Exception {
        // only one object
        assertFalse(Utils.isAnyNull(1));
        assertFalse(Utils.isAnyNull(""));
        assertFalse(Utils.isAnyNull(String.valueOf(1)));
        assertFalse(Utils.isAnyNull("abc"));
        
        // null cast to Object
        assertTrue(Utils.isAnyNull((Object) null));
        
        // all objects are not null
        assertFalse(Utils.isAnyNull("abc", "ab", "a"));
        assertFalse(Utils.isAnyNull(1, 2));
        assertFalse(Utils.isAnyNull("abc", 3));
        assertFalse(Utils.isAnyNull(1, Integer.valueOf(1)));
        
        // some objects are null
        assertTrue(Utils.isAnyNull("abc", null, "a", null));
        assertTrue(Utils.isAnyNull(1, 2, null));
        assertTrue(Utils.isAnyNull("abc", null, 3));
        assertTrue(Utils.isAnyNull(1, Integer.valueOf(1), null, null));
        assertTrue(Utils.isAnyNull(String.valueOf(1), null));
        
        // all nulls
        assertTrue(Utils.isAnyNull(null, null));
        assertTrue(Utils.isAnyNull(null, null, null));
        assertTrue(Utils.isAnyNull(null, null, null, null));
        assertTrue(Utils.isAnyNull(null, null, null, null, null));
    }


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
        assertNotUnique("1", String.valueOf(1));
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
