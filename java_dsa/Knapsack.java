public class Knapsack {

    // Recursive function to solve 0/1 Knapsack
    public static int knapsack(int[] values, int[] weights, int n, int capacity) {
        // Base case: no items left or capacity is 0
        if (n == 0 || capacity == 0) {
            return 0;
        }

        // If the current item is too heavy, skip it
        if (weights[n - 1] > capacity) {
            return knapsack(values, weights, n - 1, capacity);
        } else {
            // Either take it or skip it, choose max value
            int take = values[n - 1] + knapsack(values, weights, n - 1, capacity - weights[n - 1]);
            int skip = knapsack(values, weights, n - 1, capacity);
            return Math.max(take, skip);
        }
    }

    public static void main(String[] args) {
        int[] values = {3, 4, 5,9,15};
        int[] weights = {2, 3, 4,5,6};
        int capacity = 5;
        int n = values.length;

        int maxValue = knapsack(values, weights, n, capacity);
        System.out.println("Maximum value in knapsack: " + maxValue);
    }
}
