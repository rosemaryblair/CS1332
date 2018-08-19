import java.util.List;
import java.util.ArrayList;


public class Driver2 {

	public static void main(String[] args) {
		
		int[] unsorted = new int[] {3, -4, 19, 87, -50, 1, -5, 90000};
		int[] sorted = new int[] {-50, -5, -4, 1, 3, 19, 87, 90000};

		boolean check = false;
		Sorting.lsdRadixSort(unsorted);
		for (int i = 0; i < sorted.length; i++) {
			if (sorted[i] != unsorted[i]) {
				check = true;
			}
		}
		System.out.println();
		System.out.println("-- LSD RADIX TEST --");
		System.out.println();

		System.out.println("Array was sorted correctly? " + !check + "\n");
		for (int j = 0; j < sorted.length; j++) {
			System.out.println("Sorted: " + unsorted[j] + " - Solution: " + sorted[j]);
			System.out.println();
		}

	}

}