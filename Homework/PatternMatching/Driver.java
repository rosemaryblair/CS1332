import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class Driver {

	public static void main(String[] args) {

		String sell = "sell";
		String sellNotThere = "sea lions trains cardinal boardwalk";
		String sellText = "She sells seashells by the seashore.";

		List<Integer> sellAnswer = new ArrayList<>();
		sellAnswer.add(4);

		List<Integer> emptyList = new ArrayList<>();

		String kmpPattern = "ababa";
		String kmpText = "ababaaababa";
		String kmpNotThere = "ababbaba";

		List<Integer> kmpAnswer = new ArrayList<>();
		kmpAnswer.add(0);
		kmpAnswer.add(6);

		CharacterComparator comparator = new CharacterComparator();

		System.out.println("\n-- Tests --\n");

		// Rabin Karp
		List<Integer> output = PatternMatching.rabinKarp(sell, sellNotThere, comparator);
		System.out.println("Rabin-Karp correct? " + output.equals(sellAnswer));
		System.out.println("Used comparator? " + (comparator.getCount() != 0));
		System.out.println("Comparison count: " + comparator.getCount()
			+ ". Should be: 0.");

		// Boyer Moore
		// List<Integer> output = PatternMatching.boyerMoore(sellNotThere, sell, comparator);
		// System.out.println("Boyer-Moore correct? " + output.equals(emptyList));

		// int count = comparator.getCount();
		// System.out.println("Did not use the comparator. " + (count == 0));
		// System.out.println("Comparison count was " + count
		// 	+ ". Should be 0.");
		


		// Last Table
		// Map<Character, Integer> lastTable = PatternMatching.buildLastTable(sell);
		// Map<Character, Integer> expectedLT = new HashMap<>();
		// expectedLT.put('s', 0);
		// expectedLT.put('e', 1);
		// expectedLT.put('l', 3);

		// System.out.println("Output: ");
		// Set<Character> keyset = lastTable.keySet();
		// for (char c : keyset) {
		// 	System.out.println(c + " " + lastTable.get(c));
		// }

		// System.out.println();

		// System.out.println("Solution: ");
		// Set<Character> keyset2 = expectedLT.keySet();
		// for (char k : keyset) {
		// 	System.out.println(k + " " + expectedLT.get(k));
		// }

		// System.out.println("\nMatching Last Tables? " + lastTable.equals(expectedLT));

		// KMP
		// List<Integer> output = PatternMatching.kmp(kmpPattern, kmpNotThere, comparator);
		// // for (int i : output) {
		// // 	System.out.println("Answer: " + i);
		// // }
		// System.out.println("Matching solution? " + output.equals(emptyList));
		// System.out.println();

		// int count = comparator.getCount();
		// System.out.println("Did not use the comparator. " + (count == 0));
		// System.out.println("Comparison count was " + count
		// 	+ ". Should be 10.");
		

		// Failure Table
		// int[] failureTable = PatternMatching.buildFailureTable(kmpPattern, comparator);
		// int[] expected = {0, 0, 1, 2, 3};
		// boolean t1 = true;
		// int j = 0;
		// for (int i : failureTable) {
		// 	if (i != expected[j]) {
		// 		t1 = false;
		// 		System.out.println("Mismatch: " + i + " Expected: " + expected[j]);
		// 	} else {
		// 		System.out.println("Match: " + i + " " + expected[j]);
		// 	}
		// 	j++;
		// }
		// System.out.println("\nFailure Table Correct? " + t1 + "\n");

		// int count = comparator.getCount();
		// System.out.println("Did not use the comparator. " + (count == 0));
		// System.out.println("Comparison count was " + count
		// 	+ ". Should be 4.");
		System.out.println();

	}


}