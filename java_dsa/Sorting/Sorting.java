import java.util.Arrays;

public class Sorting {

    public static void main(String[] args) {

        // This is the original unsorted sequence.
        int[] numbers = { 3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5 };

        // Print the array before sorting.
        System.out.println("Sequence before: " + Arrays.toString(numbers));

        // Start the merge sort algorithm.
        mergeSort(numbers);

        // Print the array after sorting.
        System.out.println("Sequence after: " + Arrays.toString(numbers));
    }

    public static void mergeSort(int[] arr) {

        // If the array has 0 or 1 elements, it is already sorted.
        // This is the stopping point for recursion.
        if (arr.length < 2) {
            return;
        }

        // Find the middle of the array.
        // For example, if the array has 11 items, mid will be 5.
        int mid = arr.length / 2;

        // Create the left half of the array.
        // It will store everything before the middle.
        int[] left = new int[mid];

        // Create the right half of the array.
        // It will store everything from the middle to the end.
        int[] right = new int[arr.length - mid];

        // Copy the first half of arr into left.
        for (int i = 0; i < mid; i++) {
            left[i] = arr[i];
        }

        // Copy the second half of arr into right.
        for (int i = mid; i < arr.length; i++) {
            right[i - mid] = arr[i];
        }

        // Recursion:
        // Keep splitting the left side until it becomes tiny arrays.
        mergeSort(left);

        // Recursion:
        // Keep splitting the right side until it becomes tiny arrays.
        mergeSort(right);

        // Now that left and right are sorted,
        // merge them back into arr in sorted order.
        merge(arr, left, right);
    }

    public static void merge(int[] arr, int[] left, int[] right) {

        // i points to the current item in the left array.
        int i = 0;

        // j points to the current item in the right array.
        int j = 0;

        // k points to the current position in the main array.
        int k = 0;

        // Compare the front number of left and right.
        // Take the smaller one and put it into arr.
        while (i < left.length && j < right.length) {

            // If the left number is smaller or equal,
            // put it into the main array.
            if (left[i] <= right[j]) {
                arr[k] = left[i];

                // Move to the next number in left.
                i++;
            } else {
                // Otherwise, the right number is smaller,
                // so put it into the main array.
                arr[k] = right[j];

                // Move to the next number in right.
                j++;
            }

            // Move to the next empty spot in arr.
            k++;
        }

        // If there are leftover numbers in left,
        // copy them into arr.
        while (i < left.length) {
            arr[k] = left[i];
            i++;
            k++;
        }

        // If there are leftover numbers in right,
        // copy them into arr.
        while (j < right.length) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }
}
