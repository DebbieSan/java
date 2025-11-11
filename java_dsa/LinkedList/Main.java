/**
 * Class Description:
 * -------------------
 * 
 * Main
 * ----
 * This is, as with any java program, the entry point of the program.
 * Used to create and test different linked list implementations such as
 * SinglyLinkedList, DoublyLinkedList, PriorityQueueLinkedList, and
 * TwoQueueLinkedList.
 * Demonstrates operations like adding, removing, swapping, and reversing
 * elements.
 * 
 */

public class Main {

    public static void main(String[] args) {

        // =========================
        // Simple SinglyLinkedList
        // =========================
        System.out.println("=== SinglyLinkedList ===");
        SinglyLinkedList newList = new SinglyLinkedList();

        // Add a single element to the head
        newList.addToHead("test");

        // Print the list
        newList.printLinkedList();

        // Print the size of the list
        newList.size();

        System.out.println(); // blank line for separation

        // =========================
        // PriorityQueueLinkedList
        // =========================
        System.out.println("=== PriorityQueueLinkedList ===");
        PriorityQueueLinkedList newPrio = new PriorityQueueLinkedList();

        // Add elements to the priority queue
        newPrio.add("pinocchio");
        newPrio.add("Mr.Toad");
        newPrio.add("SnowWhite");

        // Print the priority queue
        newPrio.printLinkedList();

        // Print the size
        newPrio.size();

        // Remove the highest priority element
        newPrio.remove();

        // Print the queue after removal
        newPrio.printLinkedList();

        System.out.println(); // blank line for separation

        // ================================================
        // Swapping Elements Anywhere in a SinglyLinkedList
        // =================================================
        System.out.println("=== Swap Any Elements ===");
        SinglyLinkedList swapList = new SinglyLinkedList();

        // Add elements to the tail
        swapList.addToTail("Indiana Jones");
        swapList.addToTail("Enchanted Tiki Room");
        swapList.addToTail("Jungle Cruise");

        // Print before swap
        System.out.println("Before swap:");
        swapList.printLinkedList();

        // Swap two elements by adjusting links
        SinglyLinkedList.swapElements(swapList, "Indiana Jones", "Jungle Cruise");

        // Print after swap
        System.out.println("After swap:");
        swapList.printLinkedList();

        System.out.println(); // blank line for separation

        // ====================================================
        // Swapping Adjacent Elements in a SinglyLinkedList
        // ====================================================
        System.out.println("=== Swap Adjacent Elements in a SinglyLinkedList ===");
        SinglyLinkedList adjacentSwap = new SinglyLinkedList();

        // Add elements to head (last added becomes first)
        adjacentSwap.addToHead("Space Mountain");
        adjacentSwap.addToHead("Star Tours");
        adjacentSwap.addToHead("Buzz Lightyear Astro Blasters");

        // Print before adjacent swap
        System.out.println("Before adjacent swap:");
        adjacentSwap.printLinkedList();

        // Swap two adjacent elements by adjusting links only
        SinglyLinkedList.swapNext(adjacentSwap, "Buzz Lightyear Astro Blasters", "Star Tours");

        // Print after adjacent swap
        System.out.println("After adjacent swap:");
        adjacentSwap.printLinkedList();
        System.out.println();

        // =================================================
        // Swapping Adjacent Elements in a DoublylinkedList
        // =================================================

        System.out.println("=== Swap Adjacent Elements in a DoublyLinkedList ===");

        // Create a new doubly linked list
        DoublyLinkedList dll = new DoublyLinkedList();

        // Add the three elements
        dll.addToTail("Mark Twain Riverboat");
        dll.addToTail("Sailing Ship Columbia");
        dll.addToTail("Pirate's Lair on Tom Sawyer Island");

        // Print the list before swap
        System.out.println("Before swap:");
        dll.printDoublyLinkedList();

        // Swap "Sailing Ship Columbia" with "Pirate's Lair on Tom Sawyer Island"
        DoublyLinkedList.swapNext(dll, "Sailing Ship Columbia", "Pirate's Lair on Tom Sawyer Island");

        // Print the list after swap
        System.out.println("After swap:");
        dll.printDoublyLinkedList();
        System.out.println();

        // ============================================
        // DoublyLinkedList implementation of a Bag
        // =============================================

        System.out.println("===  DoublyLinkedList implementation of a Bag ===");

        BagLinkedList bag = new BagLinkedList();

        // Add elements (duplicates allowed)
        bag.add("Radiator Springs Racers");
        bag.add("Guardians of the Galaxy - Mission: BREAKOUT");
        bag.add("Incredicoaster");
        bag.add("Soarin' Around the World");
        bag.add("WEB Slingers: A Spider-Man Adventure");
        bag.add("Incredicoaster");
        bag.add("Incredicoaster");

        System.out.println("Bag after adding elements:");
        bag.printDoublyLinkedList();

        // Find first occurrence
        System.out.println("Find 'Incredicoaster': " + bag.find("Incredicoaster"));

        // Find all occurrences
        System.out.println("Find all 'Incredicoaster': " + bag.findAll("Incredicoaster"));

        // Remove one occurrence
        bag.remove("Incredicoaster");
        System.out.println("Bag after removing one 'Incredicoaster':");
        bag.printDoublyLinkedList();

        // Remove another occurrence
        bag.remove("Incredicoaster");
        System.out.println("Bag after removing another 'Incredicoaster':");
        bag.printDoublyLinkedList();

        System.out.println();

        // ===============================================
        // DoublyLinkedList implementation of reverse()
        // ===============================================

        System.out.println("===  DoublyLinkedList implementation of reverse() ===");

        DoublyLinkedList rides = new DoublyLinkedList();

        rides.addToTail("Mark Twain Riverboat");
        rides.addToTail("Sailing Ship Columbia");
        rides.addToTail("Pirate's Lair on Tom Sawyer Island");
        rides.addToTail("Big Thunder Mountain Railroad");

        System.out.println("Original list:");
        rides.printDoublyLinkedList();

        rides.reverse();

        System.out.println("Reversed list:");
        rides.printDoublyLinkedList();

        System.out.println();
    }

}
