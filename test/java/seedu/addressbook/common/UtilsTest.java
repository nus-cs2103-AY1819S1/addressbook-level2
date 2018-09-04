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
        // empty list
        assertNotNull();

        // all objects not null
        assertNotNull("abc", "ab", "a");
        assertNotNull(1, 2);

        // some null objects
        assertAreNull((Object) null);
        assertAreNull("abc", null);
        assertAreNull(null, "", "abc", "ABC");
        assertAreNull("", "abc", "a", null);
        assertAreNull(null, 1, Integer.valueOf(1));
        assertAreNull(null, "a", "b", null);

        // all objects null
        assertAreNull(null, null, null);
    }
    
    private void assertAreNull(Object... obj) {
        assertTrue(Utils.isAnyNull(obj));
    }
    
    private void assertNotNull(Object... obj) {
        assertFalse(Utils.isAnyNull(obj));
    }
}
