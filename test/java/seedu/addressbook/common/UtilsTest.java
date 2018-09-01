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

        //only one null object
        assertContainsNull((Object) null);
        assertContainsNull(null, "123", "abc");
        assertContainsNull( Integer.valueOf(1), null);

        //multiple null objects
        assertContainsNull(null, null);
        assertContainsNull(null, "123", "ABC", null );
        assertContainsNull(null, 1, Integer.valueOf(1), null);

        //no null objects
        assertContainsNoNull("123");
        assertContainsNoNull("123", "abc","abc" );
        assertContainsNoNull(1, Integer.valueOf(1));

    }

    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertContainsNull(Object... objects) {
        assertTrue(Utils.isAnyNull(objects));
    }

    private void assertContainsNoNull(Object... objects){
        assertFalse(Utils.isAnyNull(objects));
    }


}
