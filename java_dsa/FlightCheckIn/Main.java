/**
 * Flight priority list program.
 *
 * The hash table represents 13 upgrade-eligible spots on a flight.
 *
 * Each passenger has an upgrade priority number. In this program, the
 * priority number does not mean they are “more important” than someone
 * else; the numeric order itself is not important. It is simply a unique
 * identifier assigned (for example) on a first-come, first-served basis
 * from a predefined list of numbers. All passengers with a priority
 * number are eligible to sit in the priority cabin.
 *
 * We use a hash table with the hash function: key % 13, where the key is
 * the passenger's priority number. Linear probing resolves collisions
 * when two passengers map to the same slot in the table.
 *
 * When all 13 slots are filled, the priority upgrade list is considered
 * full and no one else is eligible for an upgrade in this simplified model,
 * regardless of their rewards status.
 */

public class Main {

    public static void main(String[] args) {

        // accessing FlightSet through FlightCheckIn (nested static class)
        FlightCheckIn.FlightSet upgradeList = new FlightCheckIn.FlightSet();

        // Priority numbers for passengers eligible for upgrade
        int[] priorityNumbers = {
                1, 5, 21, 26, 39, 14, 15, 16,
                17, 18, 19, 20, 111, 145, 146
        };

        String[] names = {
                "Tianna Rogers", // priority 1
                "Wendy Darling", // priority 5
                "Christopher Robin", // priority 21
                "Daisy Duck", // priority 26
                "Snow White", // priority 39
                "Mary Poppins", // priority 14
                "Judy Hopps", // priority 15
                "Alfredo Linguini", // priority 16
                "Jack Skellington", // priority 17
                "James P. Sullivan", // priority 18
                "Nick Wilde", // priority 19
                "Roher Radcliffe", // priority 20
                "Randall Boggs", // priority 111
                "Scrooge McDuck", // priority 145
                "Tinker Bell" // priority 146
        };

        System.out.println("||| Building the upgrade priority list using a hash table (k mod 13) |||\n");

        // Insert passengers into the upgrade list (hash table with linear probing)
        for (int i = 0; i < priorityNumbers.length; i++) {
            System.out.println("Adding passenger: " + names[i]
                    + " with priority number: " + priorityNumbers[i]);
            upgradeList.add(priorityNumbers[i], names[i]);
        }

        // Show the internal state of the hash table after all insertions
        upgradeList.printSet();

        // --- Look up some priority numbers ---
        System.out.println("\n||| Existence checks on the upgrade list |||");
        System.out.println("Does priority number 21 exist? " + upgradeList.exists(21)); // should be true
        System.out.println("Does priority number 999 exist? " + upgradeList.exists(999)); // should be false
        System.out.println("Does priority number 57 exist? " + upgradeList.exists(57)); // should be false

        // --- Update an existing passenger with the same priority number ---
        System.out.println("\n|||Updating an existing priority entry |||");
        System.out.println("Updating priority 21 to 'Christopher Robin Milne'");
        upgradeList.add(21, "Christopher Robin Milne");
        upgradeList.printSet();

        // --- Check if the priority list is full ---
        System.out.println("\n|||Check if the priority upgrade list is full |||");
        System.out.println("Is priority list full? " + upgradeList.isPriorityListFull());

        // --- Try to add another passenger after the list is already full ---
        System.out.println("\n||| Attempting to add another passenger after full |||");
        upgradeList.add(200, "Last Minute Diamond Rewards Member"); // should trigger the 'list is full' message

        // Final printout to confirm no extra entry was added
        upgradeList.printSet();

        System.out.println("\n=== End of priority list upgrade system display ===");
    }

}