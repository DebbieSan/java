import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class BusyBussBee {

    public static void main(String[] args) {
        LinkedList<String> tasks = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Welcome to BusyBussBee Task Manager ===");

        while (running) {
            // Display Menu
            System.out.println("\n--- Menu ---");
            System.out.println("1. View to-do list");
            System.out.println("2. Add a task to the end");
            System.out.println("3. Add an urgent task to the front");
            System.out.println("4. Exit (or type 'q')");
            System.out.print("Choose an option: ");

            // Read the input as a String instead of an int to prevent crashes
            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "1":
                    // View Tasks
                    System.out.println("\n--- Today's To-Do List ---");
                    if (tasks.isEmpty()) {
                        System.out.println("(Your list is empty! No tasks yet.)");
                    } else {
                        ListIterator<String> iterator = tasks.listIterator();
                        int count = 1;
                        while (iterator.hasNext()) {
                            System.out.println(count + ". [ ] " + iterator.next());
                            count++;
                        }
                    }
                    break;

                case "2":
                    // Add Last
                    System.out.print("Enter the task description: ");
                    String task = scanner.nextLine();
                    tasks.addLast(task);
                    System.out.println("Task added to the bottom of the list.");
                    break;

                case "3":
                    // Add First (Urgent)
                    System.out.print("Enter URGENT task description: ");
                    String urgentTask = scanner.nextLine();
                    tasks.addFirst(urgentTask);
                    System.out.println("Priority task added to the top!");
                    break;

                case "4":
                case "q":
                case "quit":
                    running = false;
                    System.out.println("\nGoodbye! Have a productive day!");
                    break;

                default:
                    System.out.println("Invalid option. Please enter 1, 2, 3, 4, or q.");
            }
        }

        scanner.close();
    }
}