/* A simple program to calculate factorials and demonstrate recursive versus iterative method design. 
* Note that in the recursive method, we start with the recursive case instead of the base case. 
* This demonstrate that the order of base case versus recursive case will depend both on problem type and programmer's style.
*/
public class Factorial {

    public static int recursiveFactorial(int n) {
        if (n > 0) {
            return n * recursiveFactorial(n - 1);
        } else {
            return 1;
        }
    }

    public static int iterativeFactorial(int n) {
        int result = 1;

        while (n > 0) {
            result *= n;
            n -= 1;
        }

        return result;
    }

    public static void main(String[] args) {

        int recursiveSolution = recursiveFactorial(5);

        int iterativeSolution = iterativeFactorial(5);

        System.out.println("Recursive solution: " + recursiveSolution);
        System.out.println("Iterative solution: " + iterativeSolution);

        System.out.println("True or false: Is the recursive solution the same as the iterative solution? "
                + (recursiveSolution == iterativeSolution) + ".");

    };

}
