
/**
 * Demonstrates the main types of Big O notation using simple examples
 * for each time complexity.
 *
 * <p>This program shows how different algorithms behave as the input size
 * changes, including constant time, logarithmic time, linear time,
 * linearithmic time, quadratic time, and exponential time.</p>
 *
 * <p>Each method represents one Big O category and prints an example output
 * to help beginners understand how algorithm efficiency is measured.</p>
 */

import java.util.Arrays;

public class BigODemo {

    public static void main(String[] args) {
        int[] numbers = { 5, 3, 8, 1, 2 };
        int n = numbers.length;

        constantTime(numbers);
        logarithmicTime(n);
        linearTime(numbers);
        nLogNTime(numbers);
        quadraticTime(numbers);
        exponentialTime(5);
    }

    // O(1) - Constant Time
    // Takes the same amount of time no matter how large the input is.
    public static void constantTime(int[] arr) {
        System.out.println("O(1): First element is " + arr[0]);
    }

    // O(log n) - Logarithmic Time
    // Cuts the problem size in half each time.
    public static void logarithmicTime(int n) {
        System.out.print("O(log n): ");
        while (n > 1) {
            System.out.print(n + " ");
            n = n / 2;
        }
        System.out.println();
    }

    // O(n) - Linear Time
    // Goes through each item once.
    public static void linearTime(int[] arr) {
        System.out.print("O(n): ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // O(n log n) - Linearithmic Time
    // Common in efficient sorting algorithms like Merge Sort or Java's Arrays.sort.
    public static void nLogNTime(int[] arr) {
        int[] copy = arr.clone();
        Arrays.sort(copy);

        System.out.print("O(n log n): Sorted array: ");
        for (int num : copy) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // O(n^2) - Quadratic Time
    // Nested loops over the same input.
    public static void quadraticTime(int[] arr) {
        System.out.println("O(n^2): All pairs:");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.println(arr[i] + ", " + arr[j]);
            }
        }
    }

    // O(2^n) - Exponential Time
    // Recursive Fibonacci is a classic example.
    public static int exponentialTime(int n) {
        if (n <= 1) {
            return n;
        }

        int result = exponentialTime(n - 1) + exponentialTime(n - 2);

        if (n == 5) {
            System.out.println("O(2^n): Fibonacci of 5 is " + result);
        }

        return result;
    }
}
