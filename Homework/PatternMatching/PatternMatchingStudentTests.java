import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;

/**
 * Pattern Matching student tests.
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class PatternMatchingStudentTests {
    private List<Integer> sellAnswer;
    private List<Integer> emptyList;
    private String sell;
    private String sellNotThere;
    private String sellText;

    private List<Integer> kmpAnswer;
    private String kmpPattern;
    private String kmpText;
    private String kmpNotThere;

    private CharacterComparator comparator;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        sell = "sell";
        sellNotThere = "sea lions trains cardinal boardwalk";
        sellText = "She sells seashells by the seashore.";

        sellAnswer = new ArrayList<>();
        sellAnswer.add(4);

        emptyList = new ArrayList<>();

        kmpPattern = "ababa";
        kmpText = "ababaaababa";
        kmpNotThere = "ababbaba";

        kmpAnswer = new ArrayList<>();
        kmpAnswer.add(0);
        kmpAnswer.add(6);

        comparator = new CharacterComparator();
    }

    @Test(timeout = TIMEOUT)
    public void testBuildFailureTable() {
        int[] failureTable = PatternMatching
                .buildFailureTable(kmpPattern, comparator);
        int[] expected = {0, 0, 1, 2, 3};
        assertArrayEquals(expected, failureTable);
        assertTrue("Did not use the comparator.", comparator.getCount() != 0);
        assertTrue("Comparison count was " + comparator.getCount()
                + ". Should be 4.", comparator.getCount() == 4);
    }

    @Test(timeout = TIMEOUT)
    public void testKMPThere() {
        assertEquals(kmpAnswer,
                PatternMatching.kmp(kmpPattern, kmpText, comparator));
        assertTrue("Did not use the comparator.", comparator.getCount() != 0);
        assertTrue("Comparison count was " + comparator.getCount()
                + ". Should be 18.", comparator.getCount() == 18);
    }

    @Test(timeout = TIMEOUT)
    public void testKMPNotThere() {
        assertEquals(emptyList,
                PatternMatching.kmp(kmpPattern, kmpNotThere, comparator));
        assertTrue("Did not use the comparator.", comparator.getCount() != 0);
        assertTrue("Comparison count was " + comparator.getCount()
                + ". Should be 10.", comparator.getCount() == 10);
    }

    @Test(timeout = TIMEOUT)
    public void testBuildLastTable() {
        Map<Character, Integer> lastTable = PatternMatching
            .buildLastTable(sell);
        Map<Character, Integer> expectedLastTable = new HashMap<>();
        expectedLastTable.put('s', 0);
        expectedLastTable.put('e', 1);
        expectedLastTable.put('l', 3);
        assertEquals(expectedLastTable, lastTable);
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMooreThere() {
        assertEquals(sellAnswer,
                PatternMatching.boyerMoore(sell, sellText, comparator));
        assertTrue("Did not use the comparator.", comparator.getCount() != 0);
        assertTrue("Comparison count was " + comparator.getCount()
                + ". Should be 20.", comparator.getCount() == 20);
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMooreNotThere() {
        assertEquals(emptyList,
                PatternMatching.boyerMoore(sell, sellNotThere, comparator));
        assertTrue("Did not use the comparator.", comparator.getCount() != 0);
        assertTrue("Comparison count was " + comparator.getCount()
                + ". Should be 9.", comparator.getCount() == 9);
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMooreLongerText() {
        assertEquals(emptyList,
                PatternMatching.boyerMoore(sellNotThere, sell, comparator));
        assertEquals(0, comparator.getCount());
    }

    @Test(timeout = TIMEOUT)
    public void testRabinKarpThere() {
        assertEquals(sellAnswer,
                PatternMatching.rabinKarp(sell, sellText, comparator));
        assertTrue("Did not use the comparator.", comparator.getCount() != 0);
        assertTrue("Comparison count was " + comparator.getCount()
                + ". Should be 4.", comparator.getCount() == 4);
    }

    @Test(timeout = TIMEOUT)
    public void testRabinKarpNotThere() {
        assertEquals(emptyList,
                PatternMatching.rabinKarp(sell, sellNotThere, comparator));
        assertEquals(0, comparator.getCount());
    }
}