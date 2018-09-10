package seedu.addressbook.common;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UtilsTest {

    //this is a method of UtilsTest that tests the method Utils.elementsAreUnique
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


    // this is a method for testing the method Utils.isAnyNull
    @Test 
    public void isAnyNullTest() throws Exception {
        // empty list --> why does it lead to test failure
        //assertIsAnyNull(); --> if no argument, isAnyNull will treat it as false.--> assertIsAnyNull return exception
        assertAllNotNull();

        // only one object
        assertAllNotNull("");
        assertAllNotNull(Integer.valueOf("2"));
        assertAllNotNull(1);
        assertIsAnyNull( (Object)null);

        // three objects, containing at least 1 null object, at different positions
        assertIsAnyNull((Object) null, "j", "p");
        assertIsAnyNull("j", (Object) null, "p");
        assertIsAnyNull("j", "p", (Object) null);
        assertIsAnyNull("j", (Object) null, (Object) null);
        assertIsAnyNull((Object) null, (Object) null, (Object) null);


        // three none null objects
        assertAllNotNull(1, "kjsa", "");


    }


    private void assertIsAnyNull(Object... objects) {
        assertTrue(Utils.isAnyNull(objects));
    }

    private void assertAllNotNull (Object... objects) {
        assertFalse(Utils.isAnyNull(objects));
    } 
}
