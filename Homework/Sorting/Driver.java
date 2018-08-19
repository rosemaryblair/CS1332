import java.util.Comparator;

public class Driver {

	public static void main(String[] args) {

		Thing[] things = new Thing[7];
		Thing[] thingsByName = new Thing[7];
		Thing[] thingsById = new Thing[7];
		ComparatorPlus<Thing> compName;
		ComparatorPlus<Thing> compId;

		things[0] = new Thing("Abba", 10);
		things[1] = new Thing("Ruru", 3);
		things[2] = new Thing("Bede", 43);
		things[3] = new Thing("Roth", 7);
		things[4] = new Thing("Yeye", 2);
		things[5] = new Thing("Axxe", 18);
		things[6] = new Thing("Bede", 90);

		thingsByName[0] = things[0];
		thingsByName[1] = things[5];
		thingsByName[2] = things[2];
		thingsByName[3] = things[6];
		thingsByName[4] = things[3];
		thingsByName[5] = things[1];
		thingsByName[6] = things[4];

		compName = Thing.getNameComparator();

		// MERGE SORT //
		System.out.println("\n-- Merge Sort Tests --\n");
		Sorting.mergeSort(things, compName);
		boolean t1 = true;
		for (int i = 0; i < things.length; i++) {
			if (!(things[i].equals(thingsByName[i]))) {
				t1 = false;
				System.out.println("Mismatch found: ");
				System.out.println("Expected: " + thingsByName[i]
					+ ", Result: " + things[i] + "\n");
			} else {
				System.out.println("Expect: " + thingsByName[i].toString()
					+ "Result: " + things[i].toString() + "\n");
			}
		}
		System.out.println("Merge Sort Successful? " + t1);
		System.out.println();

		System.out.println("Number of Comparisons: " + compName.getCount());
		// System.out.println("Result: " + (compName.getCount() <= 20 && compName.getCount() != 0));
		System.out.println();

		// INSERTION SORT //
		// System.out.println("\n-- Insertion Sort Tests --\n");
		// Sorting.insertionSort(things, compName);
		// boolean t1 = true;
		// for (int i = 0; i < things.length; i++) {
		// 	if (!(things[i].equals(thingsByName[i]))) {
		// 		t1 = false;
		// 		System.out.println("Mismatch found: ");
		// 		System.out.println("Expected: " + thingsByName[i]
		// 			+ ", Result: " + things[i] + "\n");
		// 	} else {
		// 		System.out.println("Expect: " + thingsByName[i].toString()
		// 			+ "Result: " + things[i].toString() + "\n");
		// 	}
		// }
		// System.out.println("Insertion Sort Successful? " + t1);
		// System.out.println();

		// System.out.println("Number of Comparisons: " + compName.getCount());
		// // System.out.println("Result: " + (compName.getCount() <= 20 && compName.getCount() != 0));
		// System.out.println();

		// BUBBLE SORT //
		// System.out.println("\n-- Bubble Sort Tests --\n");
		// Sorting.bubbleSort(things, compName);
		// boolean t1 = true;
		// for (int i = 0; i < things.length; i++) {
		// 	if (!(things[i].equals(thingsByName[i]))) {
		// 		t1 = false;
		// 		System.out.println("Mismatch found: ");
		// 		System.out.println("Expected: " + thingsByName[i]
		// 			+ ", Result: " + things[i] + "\n");
		// 	}
		// }
		// System.out.println("Bubble Sort Successful? " + t1);
		// System.out.println();

		// System.out.println("Number of Comparisons: " + compName.getCount());
		// System.out.println("Result: " + (compName.getCount() <= 20 && compName.getCount() != 0));
		// System.out.println();



	}

	private static class Thing {

		private String name;
		private int id;

		public Thing(String name, int id) {
			this.name = name;
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public int getId() {
			return id;
		}

		@Override
		public String toString() {
			return "Name: " + name + "; ID#: " + id + "\n";
		}

		@Override
		public boolean equals(Object other) {
			if (other == null) { return false; }
			if (this == other) { return true; }
			if (!(other instanceof Thing)) { return false; }
			Thing that = (Thing) other;
			if (that.getName().equals(this.name) && that.getId() == this.id) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public int hashCode() {
			int num = 17;
			num = 31 * num + name.hashCode();
			num = 31 * num + id;
			return num;
		}

		public static ComparatorPlus<Thing> getNameComparator() {
			return new ComparatorPlus<Thing>() {
				@Override
				public int compare(Thing one, Thing two) {
					incrementCount();
					return one.getName().compareTo(two.getName());
				}
			};
		}

		// if +, one > two. if -, one < two. if =, one = two.
		public static ComparatorPlus<Thing> getIdComparator() {
			return new ComparatorPlus<Thing>() {
				@Override
				public int compare(Thing one, Thing two) {
					incrementCount();
					return one.getId() - two.getId();			}
			};
		}
	}

	private abstract static class ComparatorPlus<T> implements Comparator<T> {
	private int count;

	public int getCount() {
		return count;
	}

	public void incrementCount() {
		count++;
	}

}
}

		// TeachingAssistant[] tas;
		// TeachingAssistant[] tasByName;
		// ComparatorPlus<TeachingAssistant> comp;

		// //set up
		// tas = new TeachingAssistant[10];
		// tas[0] = new TeachingAssistant("Adrianna");
		// tas[1] = new TeachingAssistant("Chad");
		// tas[2] = new TeachingAssistant("Jackie");
		// tas[3] = new TeachingAssistant("Miguel");
		// tas[4] = new TeachingAssistant("Ashley");
		// tas[5] = new TeachingAssistant("Scott");
		// tas[6] = new TeachingAssistant("Tim");
		// tas[7] = new TeachingAssistant("Joey");
		// tas[8] = new TeachingAssistant("Raymond");
		// tas[9] = new TeachingAssistant("Bartosz");

		// tasByName = new TeachingAssistant[10];
		// tasByName[0] = tas[0];
		// tasByName[1] = tas[4];
		// tasByName[2] = tas[9];
		// tasByName[3] = tas[1];
		// tasByName[4] = tas[2];
		// tasByName[5] = tas[7];
		// tasByName[6] = tas[3];
		// tasByName[7] = tas[8];
		// tasByName[8] = tas[5];
		// tasByName[9] = tas[6];

		// comp = TeachingAssistant.getNameComparator();

		// //merge sort
		// Sorting.mergeSort(tas, comp);
		// boolean t1 = true;
		// for (int i = 0; i < 10; i++) {
		// 	if (!tasByName[i].equals(tas[i])) {
		// 		t1 = false;
		// 		System.out.println(
		// 			tasByName[i] + " vs. " + tas[i]);
		// 	}
		// }

		// System.out.println();
		// System.out.println("Test 3 - Sorting Names w/ Merge Sort: "
		// 	+ t1);
		// System.out.println();

		// boolean t2 = comp.getCount() <= 21 && comp.getCount() != 0;
		// System.out.println("Number of Comp's Match? " + t2);
		// System.out.println("Comparisons made: " + comp.getCount());
		// System.out.println();


		// //insertion sort
		// Sorting.insertionSort(tas, comp);
		// boolean t1 = true;
		// for (int i = 0; i < 10; i++) {
		// 	if (!tasByName[i].equals(tas[i])) {
		// 		t1 = false;
		// 		System.out.println(
		// 			tasByName[i] + " vs. " + tas[i]);
		// 	}
		// }

		// System.out.println();
		// System.out.println("Test 2 - Sorting Names w/ Insertion Sort: "
		// 	+ t1);
		// System.out.println();

		// boolean t2 = comp.getCount() <= 24 && comp.getCount() != 0;
		// System.out.println("Number of Comp's Match? " + t2);
		// System.out.println("Comparisons made: " + comp.getCount());
		// System.out.println();

		// //bubble sort
		// Sorting.bubbleSort(tas, comp);
		// boolean t1 = true;
		// for (int i = 0; i < 10; i++) {
		// 	if (!tasByName[i].equals(tas[i])) {
		// 		t1 = false;
		// 		System.out.println(
		// 			tasByName[i] + " vs. " + tas[i]);
		// 	}
		// }
		// System.out.println();
		// System.out.println("Test 1 - Sorting Names w/ Bubble Sort: "
		// 	+ t1);
		// System.out.println();

		// boolean t2 = comp.getCount() <= 44 && comp.getCount() != 0;
		// System.out.println(comp.getCount());
		// System.out.println("Test 2 - Bubble Sort # of Comp's: " + t2);
		// System.out.println();


	// }

	// private static class TeachingAssistant {

	// 	private String name;

	// 	public TeachingAssistant(String name) {
	// 		this.name = name;
	// 	}

	// 	public String getName() {
	// 		return name;
	// 	}

	// 	public void setName(String name) {
	// 		this.name = name;
	// 	}

	// 	@Override
	// 	public String toString() {
	// 		return "Name: " + name;
	// 	}

	// 	@Override
	// 	public boolean equals(Object other) {
	// 		if (this == other) {
	// 			return true;
	// 		} else if (!(other instanceof TeachingAssistant)) {
	// 			return false;
	// 		}
	// 		TeachingAssistant that = (TeachingAssistant) other;
	// 		return this.getName().equals(that.getName());
	// 	}

	// 	public static ComparatorPlus<TeachingAssistant> getNameComparator() {
	// 		return new ComparatorPlus<TeachingAssistant>() {
	// 			@Override
	// 			public int compare(TeachingAssistant ta1,
	// 				TeachingAssistant ta2) {
	// 				incrementCount();
	// 				return ta1.getName().compareTo(ta2.getName());
	// 			}
	// 		};
	// 	}
	// }

	// private abstract static class ComparatorPlus<T> implements Comparator<T> {
	// 	private int count;

	// 	public int getCount() {
	// 		return count;
	// 	}

	// 	public void incrementCount() {
	// 		count++;
	// 	}
	// }

