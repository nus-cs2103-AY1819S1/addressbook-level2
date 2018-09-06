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
        // no object
        assertNoNull();
        
        // only one object null
        assertIsNull((Object) null);
        
        // only one object not null
        assertNoNull(1);
        assertNoNull("abc");
        assertNoNull("");
        
        // many objects with null
        assertIsNull(null, 1, "abc");
        assertIsNull("", "ABC", null, 1, null);
        assertIsNull(null, null, null);
        
        // many objects without null
        assertNoNull(1, "abc");
        assertNoNull("", "ABC", 1);
    }
    
    private void assertIsNull(Object... objects) {
        assertTrue(Utils.isAnyNull(objects));
    }
    
    private void assertNoNull(Object... objects) {
        assertFalse(Utils.isAnyNull(objects));
    }
}
