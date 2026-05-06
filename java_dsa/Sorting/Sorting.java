import java.util.Arrays;

public class Sorting {

    public static void main(String[] args) {

        // This is the original unsorted sequence.
        int[] numbers = { 3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5 };

        // We make two copies of the same sequence.
        // This lets us test merge sort and quick sort separately.
        int[] mergeNumbers = Arrays.copyOf(numbers, numbers.length);
        int[] quickNumbers = Arrays.copyOf(numbers, numbers.length);

        // Print the original array.
        System.out.println("Original sequence: " + Arrays.toString(numbers));

        // Start the merge sort algorithm.
        mergeSort(mergeNumbers);

        // Print the array after merge sort.
        System.out.println("After merge sort: " + Arrays.toString(mergeNumbers));

        // Start the quick sort algorithm.
        // quickSort needs the array, the first index, and the last index.
        quickSort(quickNumbers, 0, quickNumbers.length - 1);

        // Print the array after quick sort.
        System.out.println("After quick sort: " + Arrays.toString(quickNumbers));
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

    public static void quickSort(int[] arr, int low, int high) {

        // If low is less than high, that means there are
        // at least two numbers in this section to sort.
        if (low < high) {

            // Partition the array and get the pivot's final position.
            int pivotIndex = partition(arr, low, high);

            // Recursion:
            // Sort the left side of the pivot.
            quickSort(arr, low, pivotIndex - 1);

            // Recursion:
            // Sort the right side of the pivot.
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {

        // Pivot strategy:
        // We choose the last element in this section as the pivot.
        int pivot = arr[high];

        // i keeps track of where the next smaller number should go.
        int i = low - 1;

        // Look at each number from low to high - 1.
        // We do not include high because high is the pivot.
        for (int j = low; j < high; j++) {

            // If the current number is less than or equal to the pivot,
            // move it to the left side.
            if (arr[j] <= pivot) {
                i++;

                // Swap arr[i] and arr[j].
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Put the pivot in its correct final position.
        // Everything smaller is now on the left.
        // Everything bigger is now on the right.
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        // Return the pivot's final position.
        return i + 1;
    }
}