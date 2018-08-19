import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Rosemary Blair
 * @userid rblair8
 * @GTID 903359318
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     *  in-place
     *  stable
     *  adaptive
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        } else if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null.");
        }
        boolean swapped = true;
        int i = 0;
        int length = arr.length;
        while (i < length - 1 && swapped) {
            swapped = false; //if no swaps made in for-loop, arr is sorted
            for (int j = 0; j < length - i - 1; j++) {
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    T t = arr[j + 1]; //swap items
                    arr[j + 1] = arr[j];
                    arr[j] = t;
                    swapped = true; //continue to iterate
                }
            }
            length -= 1; //adaptive* largest item at end, skip in next comp.
        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *  adaptive
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        } else if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null.");
        }
        // each iteration places an item relative to prev sorted items
        for (int i = 1; i < arr.length; i++) { //start with 2nd, fixed # comp's
            int j = i; //if left item > right, swap until left <= right
            while (j > 0 && comparator.compare(arr[j - 1], arr[j]) > 0) {
                T t = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = t;
                j -= 1; 
            } 
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        } else if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null.");
        }

        if (arr.length > 1) { //arrays with 1 item only are sorted, base case

            int midIndex = (int) (arr.length / 2);
            T[] leftArray = (T[]) new Object[midIndex];
            T[] rightArray = (T[]) new Object[arr.length - midIndex];
            for (int i = 0; i < midIndex; i++) {
                leftArray[i] = arr[i];
            }
            for (int j = midIndex; j < arr.length; j++) {
                rightArray[j - midIndex] = arr[j];
            }
            //sort halves seperately
            mergeSort(leftArray, comparator);
            mergeSort(rightArray, comparator);

            int currIndex = 0;
            int leftIndex = 0; //left marker
            int rightIndex = 0; //right marker

            //if all items in an array are placed, just copy remaining for rest
            while (leftIndex < midIndex
                && rightIndex < (arr.length - midIndex)) { //both have unsorted
                if (comparator.compare(leftArray[leftIndex],
                    rightArray[rightIndex]) <= 0) { //stable* if =, place L->R
                    arr[currIndex] = leftArray[leftIndex];
                    leftIndex += 1;
                } else {
                    arr[currIndex] = rightArray[rightIndex];
                    rightIndex += 1;
                }
                currIndex += 1;
            }
            //fill rest of arr with remaining items in left array
            while (leftIndex < midIndex) {
                arr[currIndex] = leftArray[leftIndex];
                leftIndex += 1;
                currIndex += 1;
            }
            //fill rest of arr with remaining items in right array
            while (rightIndex < arr.length - midIndex) {
                arr[currIndex] = rightArray[rightIndex];
                rightIndex += 1;
                currIndex += 1;
            }

        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        }

        //list holds values with digits -9 (i = 0) to 9 (i = 18)
        List<Integer>[] buckets = new ArrayList[19];
        int longest = arr[0];
        int longestLength = 1; //# of iterations

        //get the longest number by finding max of abs. values
        for (int i = 0; i < arr.length; i++) {
            int curr = Math.abs(arr[i]);
            if (curr > longest) {
                longest = curr;
            }
        }
        //find number of digits in longest number
        while (longest >= 10) {
            longest = (int) longest / 10;
            longestLength += 1;
        }

        int temp = 1; //divisor to determine digit of iteration
        for (int i = 0; i < longestLength; i++) {
            int arrIndex = 0;
            //get bucket from modding and adding 9, since length = 19 not 10
            for (int j = 0; j < arr.length; j++) {
                int curr = arr[j] / temp;
                int bucket = (curr % 10) + 9;
                //if necessary, create list to hold values in current bucket
                if (buckets[bucket] == null) {
                    buckets[bucket] = new ArrayList<Integer>();
                }
                buckets[bucket].add(arr[j]); //adds to back (queue implem.)
            }
            temp *= 10; //to focus on the next digit up next iteration
            //add the ints in order and set bucket back to null when all added
            for (int k = 0; k < buckets.length; k++) {
                if (buckets[k] != null) {
                    for (Integer integer : buckets[k]) {
                        arr[arrIndex] = integer;
                        arrIndex++;
                    }
                    buckets[k] = null;
                }
            }
        }

    }
}
