package seedu.addressbook.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    
    @Test
    public void testIsAnyNull() throws Exception {
        // no nulls in list
        isAnyNull_noNull_False("abc", "def", "ghi");
        
        // one null in list
        isAnyNull_oneNull_True("abc", "def", null);
        
        // empty list
        isAnyNull_emptyList_False();
    }

    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }
    
    private void isAnyNull_noNull_False(Object... objects) {
    	assertFalse(Utils.isAnyNull(objects));
    }
    
    private void isAnyNull_oneNull_True(Object... objects) {
    	assertTrue(Utils.isAnyNull(objects));
    }
    
    private void isAnyNull_emptyList_False(Object... objects) {
    	assertFalse(Utils.isAnyNull(objects));
    }
}
