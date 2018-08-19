import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Driver {

	public static void main(String[] args) {
		HashMap<Integer, String> map = new HashMap<>(5);

		System.out.println();
		System.out.println("Length: " + map.getTable().length);
		System.out.println("Size: " + map.size());
		System.out.println();

		map.put(0, "A");
		map.put(1, "B");
		map.put(2, "C");
		map.put(3, "D");
		map.put(4, "E");
		map.put(5, "F");

		
		System.out.println();
		System.out.println("Length: " + map.getTable().length);
		System.out.println("Size: " + map.size());
		System.out.println();

		map.clear();

		System.out.println();
		System.out.println("Length: " + map.getTable().length);
		System.out.println("Size: " + map.size());
		System.out.println();

		for (int i = 0; i < HashMap.INITIAL_CAPACITY; i++) {
			System.out.println(map.getTable()[i] == null);
		}


		// System.out.println("Test 1: " + test1 + "\n");
		// System.out.println("Test 2: " + test2 + "\n");
		// System.out.println("Test 3: " + test3 + "\n");
		// System.out.println("Test 4: " + test4 + "\n");
		// System.out.println("Test 5: " + test5 + "\n");
		// System.out.println("Test 6: " + test6 + "\n");
		// System.out.println("Test 7: " + test7 + "\n");

		// String p6 = map.put(6, "F");
		// boolean test1 = p6 == null; //true
		// boolean test2 = map.getTable()[6] == null; //false
		// boolean test3 = "D".equals(map.remove(3)); //true
		// boolean test4 = true == map.getTable()[3].isRemoved(); //true
		// boolean test5 = "D".equals(map.get(3));
		// boolean test6 = true == map.containsKey(3); //true
		// boolean test7 = false == map.containsKey(5); //true

		//keySet() tests
		// Set<Integer> keySet = new HashSet<>();
		// keySet.add(0); keySet.add(1); keySet.add(2);
		// keySet.add(3); keySet.add(4);
		// boolean test1 = keySet.equals(map.keySet()); //true

		//values() test
		// List<String> values = new ArrayList<>();
		// values.add("A"); values.add("B"); values.add("C");
		// values.add("D"); values.add("E");
		// boolean test1 = values.equals(map.values());
		
		
		


	}

}