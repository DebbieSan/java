/*
 * Parenthesis Checker
 *
 * Validates whether the brackets in a given string are correctly matched
 * and properly nested. Supports three bracket types: (), [], and {}.
 *
 * Uses a custom-built stack data structure to track opening brackets,
 * ensuring every one is closed in the correct order.
 */

public class ParenthesisChecker {

    // A custom stack data structure that holds characters (LIFO — last in, first
    // out)
    class Stack {

        char[] data; // the array that stores the characters in the stack
        int top; // index of the top element; -1 means the stack is empty

        // Constructor — creates the array and sets top to -1 (empty)
        public Stack(int size) {
            data = new char[size];
            top = -1;
        }

        // Adds a character to the top of the stack
        public void push(char c) {
            top++; // move top up to the next slot
            data[top] = c; // place the character there
        }

        // Removes and returns the character at the top of the stack
        public char pop() {
            char c = data[top]; // read the top character
            top--; // shrink the stack by moving top down
            return c; // return what was on top
        }

        // Returns true if the stack has no elements
        public boolean isEmpty() {
            return top == -1;
        }
    }

    // Given a closing bracket, returns the expected matching opening bracket
    // e.g. ')' -> '(', ']' -> '[', '}' -> '{'
    // Returns '\0' (null character) if the input isn't a closing bracket
    public char getOpening(char closing) {
        switch (closing) {
            case ')':
                return '(';
            case ']':
                return '[';
            case '}':
                return '{';
            default:
                return '\0';
        }
    }

    // Checks whether all brackets in the string are correctly matched and nested
    public boolean isValid(String s) {
        // Create a stack sized to the string length (worst case: all openers)
        Stack stack = new Stack(s.length());

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i); // look at each character one at a time

            if (c == '(' || c == '[' || c == '{') {
                // Opening bracket — push it onto the stack to match later
                stack.push(c);

            } else if (c == ')' || c == ']' || c == '}') {
                // Closing bracket — there must be a matching opener on the stack
                if (stack.isEmpty()) {
                    return false; // nothing to match — invalid
                }

                char popped = stack.pop(); // remove the most recent opener

                if (getOpening(c) != popped) {
                    return false; // opener doesn't match this closer — invalid
                }
            }
        }

        // If the stack is empty, all openers were matched — valid!
        // If not, some openers were never closed — invalid
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ParenthesisChecker checker = new ParenthesisChecker();

        System.out.println(checker.isValid("()")); // true
        System.out.println(checker.isValid("()[]{}")); // true
        System.out.println(checker.isValid("{[()]}")); // true
        System.out.println(checker.isValid("(]")); // false
        System.out.println(checker.isValid("([)]")); // false
        System.out.println(checker.isValid("{[")); // false
        System.out.println(checker.isValid("")); // true
    }
}