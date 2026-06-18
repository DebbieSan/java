
/**
 * Browser History Stack is a Java program that simulates how a web browser
 * tracks your visited pages using Java's built-in Deque / ArrayDeque collection.
 * It demonstrates core stack operations: push (visiting a new page or going forward),
 * pop (going back or forward), and peek (viewing the current page).
 * It mirrors how a real browser's back and forward buttons work.
 */
    

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;


public class BrowserHistoryStack {

    public static void main(String[] args) {

        // backHistory holds pages you have visited, most recent on top
       Deque<String> backHistory = new ArrayDeque<>();

        // forwardHistory holds pages you have gone back from, so you can return to them
        Deque<String> forwardHistory = new ArrayDeque<>();

        // Scanner reads the user's input from the keyboard
        Scanner scanner = new Scanner(System.in);

        // Keep looping until the user chooses to quit
        boolean running = true;
        while (running) {

            // Print the menu
            System.out.println("\n=============================");
            System.out.println("   Browser History Stack");
            System.out.println("=============================");

            // Show current page, or "None" if no pages have been visited yet
            System.out.println("Current Page: " + (backHistory.isEmpty() ? "None" : backHistory.peek()));
            System.out.println("-----------------------------");
            System.out.println("1. Visit a new page");
            System.out.println("2. Go back");
            System.out.println("3. Go forward");
            System.out.println("4. Show history");
            System.out.println("5. Quit");
            System.out.println("-----------------------------");
            System.out.print("Enter choice (1-5): ");

            // Read and validate the user's menu choice
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // clear the leftover newline after the number

                // Check the number is within the valid range
                if (choice < 1 || choice > 5) {
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                    continue; // skip the rest of the loop and reprint the menu
                }

            } catch (Exception e) {
                // Catches anything that isn't a number (letters, symbols, etc.)
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.nextLine(); // clear the invalid input so the loop doesn't get stuck
                continue; // skip the rest of the loop and reprint the menu
            }

            switch (choice) {
                case 1:
                    // Ask for a URL and visit it
                    System.out.print("Enter URL: ");
                    String url = scanner.nextLine();
                    backHistory.push(url);
                    forwardHistory.clear(); // new path means no forward history
                    System.out.println("Visited: " + url);
                    break;

                case 2:
                    // Pop current page onto forwardHistory so we can return to it
                    if (backHistory.isEmpty()) {
                        System.out.println("Nothing to go back to!");
                    } else {
                        String page = backHistory.pop();
                        forwardHistory.push(page);
                        System.out.println("Went back to: " + (backHistory.isEmpty() ? "None" : backHistory.peek()));
                    }
                    break;

                case 3:
                    // Pop from forwardHistory back onto backHistory
                    if (forwardHistory.isEmpty()) {
                        System.out.println("Nothing to go forward to!");
                    } else {
                        String page = forwardHistory.pop();
                        backHistory.push(page);
                        System.out.println("Went forward to: " + backHistory.peek());
                    }
                    break;

                case 4:
                    // Show the current state of both stacks
                    System.out.println("Current page:    " + (backHistory.isEmpty() ? "None" : backHistory.peek()));
                    System.out.println("Back history:    " + backHistory);
                    System.out.println("Forward history: " + forwardHistory);
                    break;

                case 5:
                    // Exit the loop
                    System.out.println("Goodbye!");
                    running = false;
                    break;
            }
        }

        // Close the scanner when the loop ends
        scanner.close();
    }
}