import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
/**
 * A set of basic tests to test your HashMap.
 *
 * These tests are NOT exhaustive. Write your own test to ensure code coverage.
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class HashMapStudentTests {

    private HashMap<Integer, String> map;
    private static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        map = new HashMap<>();
        map.put(0, "A");
        map.put(1, "B");
        map.put(2, "C");
        map.put(3, "D");
        map.put(4, "E");
    }

    @Test(timeout = TIMEOUT)
    public void testPut() {
        assertEquals(null, map.put(6, "F"));
        assertNotEquals(null, map.getTable()[6]);
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        assertEquals("D", map.remove(3));
        assertEquals(true, map.getTable()[3].isRemoved());
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        assertEquals("D", map.get(3));
    }

    @Test(timeout = TIMEOUT)
    public void testContainsKey() {
        assertEquals(true, map.containsKey(3));
        assertEquals(false, map.containsKey(5));
    }

    @Test(timeout = TIMEOUT)
    public void testKeySet() {
        Set<Integer> keySet = new HashSet<>();
        keySet.add(0);
        keySet.add(1);
        keySet.add(2);
        keySet.add(3);
        keySet.add(4);

        assertEquals(keySet, map.keySet());
    }

    @Test(timeout = TIMEOUT)
    public void testValues() {
        List<String> values = new ArrayList<>();
        values.add("A");
        values.add("B");
        values.add("C");
        values.add("D");
        values.add("E");

        assertEquals(values, map.values());
    }

    @Test(timeout = TIMEOUT)
    public void testResize() {
        map.resizeBackingTable(5);
        assertEquals(5, map.getTable().length);
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        map.clear();
        for (int i = 0; i < HashMap.INITIAL_CAPACITY; i++) {
            assertEquals(null, map.getTable()[i]);
        }
    }
}