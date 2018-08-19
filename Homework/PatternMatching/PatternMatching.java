import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * Your implementations of various string searching algorithms.
 *
 * @author YOUR NAME HERE
 * @userid YOUR USER ID HERE (i.e. gburdell3)
 * @GTID YOUR GT ID HERE (i.e. 900000000)
 * @version 1.0
 */
public class PatternMatching {

    /**
     * Knuth-Morris-Pratt (KMP) algorithm that relies on the failure table (also
     * called failure function). Works better with small alphabets.
     *
     * Make sure to implement the failure table before implementing this method.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text or comparator is null
     * @param pattern the pattern you are searching for in a body of text
     * @param text the body of text where you search for pattern
     * @param comparator you MUST use this for checking character equality
     * @return list containing the starting index for each match found
     */
    public static List<Integer> kmp(CharSequence pattern, CharSequence text,
                                    CharacterComparator comparator) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("Pattern cannot be null/empty");
        } else if (text == null || comparator == null) {
            throw new IllegalArgumentException(
                "Cannot use null as argument for text and/or comparator");
        }

        List<Integer> indices = new ArrayList<>(); //holds indices of matches
        if (text.length() < pattern.length()) { //matching impossible, return
            return indices;
        }
        int[] failureTable = buildFailureTable(pattern, comparator);
        int i = 0;
        int j = 0;

        while (i <= text.length() - pattern.length()) {
            while (j < pattern.length() && comparator.compare(
                text.charAt(i + j), pattern.charAt(j)) == 0) {
                j += 1;
            }
            if (j == 0) { //no match, increment i only
                i += 1;
            } else {
                if (j == pattern.length()) { //match, add index
                    indices.add(i);
                }
                int index = failureTable[j - 1]; //next item
                i = i + j - index;
                j = index;
            }
        }
        return indices;
    }

    /**
     * Builds failure table that will be used to run the Knuth-Morris-Pratt
     * (KMP) algorithm.
     *
     * The table built should be the length of the input text.
     *
     * Note that a given index i will be the largest prefix of the pattern
     * indices [0..i] that is also a suffix of the pattern indices [1..i].
     * This means that index 0 of the returned table will always be equal to 0
     *
     * Ex. ababac
     *
     * table[0] = 0
     * table[1] = 0
     * table[2] = 1
     * table[3] = 2
     * table[4] = 3
     * table[5] = 0
     *
     * If the pattern is empty, return an empty array.
     *
     * @throws IllegalArgumentException if the pattern or comparator is null
     * @param pattern a {@code CharSequence} you're building a failure table for
     * @param comparator you MUST use this for checking character equality
     * @return integer array holding your failure table
     */
    public static int[] buildFailureTable(CharSequence pattern,
                                          CharacterComparator comparator) {
        if (pattern == null || comparator == null) {
            throw new IllegalArgumentException(
                "Cannot use null for arguments pattern/comparator");
        }

        int[] table = new int[pattern.length()];
        if (pattern.length() == 0) { //empty pattern, return empty table
            return table;
        }
        int i = 0;
        int j = 1;
        while (j < pattern.length()) {
            //match at i & j, increment both and set table[j] to i + 1
            if (comparator.compare(
                pattern.charAt(i), pattern.charAt(j)) == 0) {
                table[j] = i + 1;
                i++;
                j++;
            } else {
                if (i != 0) { //no prefix match
                    i = table[i - 1];
                } else { //i = 0, set table[j] = i and increment j only
                    table[j] = 0;
                    j++;
                }
            }
        }
        return table;

    }

    /**
     * Boyer Moore algorithm that relies on last occurrence table. Works better
     * with large alphabets.
     *
     * Make sure to implement the last occurrence table before implementing this
     * method.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text or comparator is null
     * @param pattern the pattern you are searching for in a body of text
     * @param text the body of text where you search for the pattern
     * @param comparator you MUST use this for checking character equality
     * @return list containing the starting index for each match found
     */
    public static List<Integer> boyerMoore(CharSequence pattern,
                       CharSequence text, CharacterComparator comparator) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("Pattern cannot be null/empty");
        } else if (text == null || comparator == null) {
            throw new IllegalArgumentException(
                "Cannot have null as argument for text and/or comparator");
        }

        List<Integer> indices = new ArrayList<>(); //holds indices of matches
        if (text.length() < pattern.length()) { //matching impossible, return
            return indices;
        }
        Map<Character, Integer> lastTable = buildLastTable(pattern);
        int i = 0;
        while (i <= (text.length() - pattern.length())) {
            int j = pattern.length() - 1; //pattern pointer (end)
            Character curr = text.charAt(i + j);
            //compares characters in both while there is a match
            while (j >= 0
                && comparator.compare(curr, pattern.charAt(j)) == 0) {
                j -= 1;
                if (j >= 0) {
                    curr = text.charAt(i + j);
                }
            }
            if (j == -1) { //match; add starting index
                indices.add(i);
                i += 1;
            } else { //mismatch; shift to last occ. of char or if none, after
                int shiftIndex = (lastTable.containsKey(curr)) 
                    ? lastTable.get(curr) : -1;
                i = (j > shiftIndex) ? (i + (j - shiftIndex)) : i + 1;
            }
        }
        return indices;
    }

    /**
     * Builds last occurrence table that will be used to run the Boyer Moore
     * algorithm.
     *
     * Note that each char x will have an entry at table.get(x).
     * Each entry should be the last index of x where x is a particular
     * character in your pattern.
     * If x is not in the pattern, then the table will not contain the key x,
     * and you will have to check for that in your Boyer Moore implementation.
     *
     * Ex. octocat
     *
     * table.get(o) = 3
     * table.get(c) = 4
     * table.get(t) = 6
     * table.get(a) = 5
     * table.get(everything else) = null, which you will interpret in
     * Boyer-Moore as -1
     *
     * If the pattern is empty, return an empty map.
     *
     * @throws IllegalArgumentException if the pattern is null
     * @param pattern a {@code CharSequence} you are building last table for
     * @return a Map with keys of all of the characters in the pattern mapping
     *         to their last occurrence in the pattern
     */
    public static Map<Character, Integer> buildLastTable(CharSequence pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("Pattern cannot be null.");
        }
        Map<Character, Integer> lastTable = new HashMap<Character, Integer>();
        if (pattern.length() == 0) { //return empty map if empty pattern
            return lastTable;
        }
        for (int i = 0; i < pattern.length(); i++) { //only keys from pattern
            lastTable.put(pattern.charAt(i), i); //uses last index for repeats
        }
        return lastTable;
    }

    /**
     * Prime base used for Rabin-Karp hashing.
     * DO NOT EDIT!
     */
    private static final int BASE = 137;

    /**
     * Runs the Rabin-Karp algorithm. This algorithms generates hashes for the
     * pattern and compares this hash to substrings of the text before doing
     * character by character comparisons.
     *
     * When the hashes are equal and you do character comparisons, compare
     * starting from the beginning of the pattern to the end, not from the end
     * to the beginning.
     *
     * You must use the Rabin-Karp Rolling Hash for this implementation. The
     * formula for it is:
     *
     * sum of: c * BASE ^ (pattern.length - 1 - i), where c is the integer
     * value of the current character, and i is the index of the character
     *
     * For example: Hashing "bunn" as a substring of "bunny" with base 137 hash
     * = b * 137 ^ 3 + u * 137 ^ 2 + n * 137 ^ 1 + n * 137 ^ 0 = 98 * 137 ^ 3 +
     * 117 * 137 ^ 2 + 110 * 137 ^ 1 + 110 * 137 ^ 0 = 254203747
     *
     * Note that since you are dealing with very large numbers here, your hash
     * will likely overflow, and that is fine for this implementation.
     *
     * Another key step for this algorithm is that updating the hashcode from
     * one substring to the next one must be O(1). To update the hash:
     *
     *  remove the oldChar times BASE raised to the length - 1, multiply by
     *  BASE, and add the newChar.
     *
     * For example: Shifting from "bunn" to "unny" in "bunny" with base 137
     * hash("unny") = (hash("bunn") - b * 137 ^ 3) * 137 + y * 137 ^ 0 =
     * (254203747 - 98 * 137 ^ 3) * 137 + 121 * 137 ^ 0 = 302928082
     *
     * Keep in mind that calculating exponents is not O(1) in general, so you'll
     * need to keep track of what BASE^{m - 1} is for updating the hash.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text or comparator is null
     * @param pattern a string you're searching for in a body of text
     * @param text the body of text where you search for pattern
     * @param comparator the comparator to use when checking character equality
     * @return list containing the starting index for each match found
     */
    public static List<Integer> rabinKarp(CharSequence pattern,
                      CharSequence text, CharacterComparator comparator) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("Pattern cannot be null/empty");
        } else if (text == null || comparator == null) {
            throw new IllegalArgumentException(
                "Cannot use null for arguments in text/comparator");
        }

        List<Integer> indices = new ArrayList<>();
        if (text.length() < pattern.length()) { //impossible to match
            return indices;
        }

        int patternHash = 0;
        int textHash = 0;

        int exp = 1; //stores BASE^{m-1}, iterate in reverse order
        for (int i = pattern.length() - 1; i >= 0; i--) {
            patternHash += pattern.charAt(i) * exp;
            textHash += text.charAt(i) * exp;
            exp = (i != 0) ? exp * BASE : exp; //hold last value for l8r
        }

        for (int k = 0; k <= (text.length() - pattern.length()); k++) {
            if (textHash == patternHash) {
                boolean match = true;
                for (int i = 0; i < pattern.length() && match; i++) {
                    if (comparator.compare(
                        pattern.charAt(i), text.charAt(i + k)) != 0) {
                        match = false; //mismatch, stop if found
                    }
                }
                if (match) { //match, add index
                    indices.add(k);
                }
            }
            //update the textHash for new subtext string if needed
            if (k < (text.length() - pattern.length())) {
                char oldChar = text.charAt(k);
                char newChar = text.charAt(k + pattern.length());
                int temp = textHash - oldChar * exp;
                temp = temp * BASE + newChar;
                textHash = temp;
            }
        }
        return indices;
    }
}