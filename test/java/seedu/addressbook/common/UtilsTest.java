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
    
    @Test
    public void isAnyNull() throws Exception {
        // empty list
        assertNotNull();
        
        // only one object
        assertAreNull((Object) null);
        assertNotNull(1);
        assertNotNull("");
        assertNotNull("abc");
        
        // some identical objects
        assertAreNull(null, null);
        assertNotNull(1, 1);
        assertNotNull("abc", "abc");
        
        // different ordering of null
        assertAreNull(null, 1, 2);
        assertAreNull(1, null, 2);
        assertAreNull(1, 2, null);
    }

    private void assertAreNull(Object... objects) {
        assertTrue(Utils.isAnyNull(Arrays.asList(objects)));
    }

    private void assertNotNull(Object... objects) {
        assertFalse(Utils.isAnyNull(Arrays.asList(objects)));
    }

    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }
}
