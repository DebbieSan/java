import java.util.HashSet;
import java.util.Set;

public class Rides {



    static String[] S1 = {
            "Space Mountain",
            "Pirates of the Caribbean",
            "Haunted Mansion",
            "Matterhorn Bobsleds",
            "Indiana Jones Adventure",
            "Big Thunder Mountain Railroad",
            "Space Mountain",
            "Jungle Cruise",
            "Dumbo the Flying Elephant",
            "Star Tours"
    };

    static String[] S2 = {
            "Expedition Everest",
            "Pirates of the Caribbean",
            "Haunted Mansion",
            "Seven Dwarfs Mine Train",
            "Space Mountain",
            "Tower of Terror",
            "Slinky Dog Dash",
            "Avatar Flight of Passage",
            "Space Mountain",
            "Test Track"
    };

    public static void main(String[] args) {

        // Print all elements of S1
        System.out.println(
                "Given some favourite rides at Disneyland and Disney World,\nI created two sets with rides from each park.\nHere we will look at the elements of each set and \nuse an algorithm to determine if both sets contain the same elements.");
        System.out.println("---------------------------------------------");
        System.out.println("Elements of S1 - Disneyland Rides:");
        for (String ride : S1) {
            System.out.println(ride);
        }

        System.out.println();

        // Print all elements of S2
        System.out.println("Elements of S2 - Disney World Rides: ");
        for (String ride : S2) {
            System.out.println(ride);
        }

        System.out.println();

        /*
         * Algorithm:
         *
         * We want to determine whether S1 and S2 contain the same set of elements.
         *
         * A set does not store duplicates.
         * For example, even though "Space Mountain" appears twice in S1,
         * it will only appear once in the set.
         *
         * Step 1:
         * Create an empty HashSet called set1.
         *
         * Step 2:
         * Add every element from S1 into set1.
         *
         * Step 3:
         * Create another empty HashSet called set2.
         *
         * Step 4:
         * Add every element from S2 into set2.
         *
         * Step 5:
         * Compare set1 and set2 using .equals().
         *
         * If set1.equals(set2) is true, then S1 and S2 contain the same set
         * of elements.
         *
         * If set1.equals(set2) is false, then they do not contain the same set
         * of elements.
         *
         * 
         */

        /*
         * Running Time Analysis:
         *
         * Let n be the number of elements in S1 and S2.
         *
         * Adding all elements from S1 into set1 takes O(n) time.
         * Adding all elements from S2 into set2 takes O(n) time.
         * Comparing set1 and set2 using equals() takes O(n) time.
         *
         * Total time:
         * O(n) + O(n) + O(n) = O(n)
         *
         * Therefore, this algorithm runs in O(n) time.
         *
         * It is efficient because HashSet operations such as add()
         * and contains() usually take O(1) time.
         *
         * Space Analysis:
         *
         * We create two HashSets, set1 and set2.
         * In the worst case, each set stores up to n elements.
         *
         * Therefore, the space complexity is O(n).
         */

        Set<String> set1 = new HashSet<>();

        for (String ride : S1) {
            set1.add(ride);
        }

        Set<String> set2 = new HashSet<>();

        for (String ride : S2) {
            set2.add(ride);
        }

        if (set1.equals(set2)) {
            System.out.println("S1 and S2 contain the same set of elements.");
        } else {
            System.out.println("S1 and S2 do not contain the same set of elements.");
        }

        System.out.println();

        // Rides only in S1:
        // Make a copy of set1 so the original set1 is not changed.
        Set<String> onlyInS1 = new HashSet<>(set1);

        // Remove every ride that also appears in set2.
        // What remains is only the rides that appear in S1.
        onlyInS1.removeAll(set2);

        System.out.println("Rides that appear only in S1:");
        for (String ride : onlyInS1) {
            System.out.println(ride);
        }

        System.out.println();

        // Rides only in S2:
        // Make a copy of set2 so the original set2 is not changed.
        Set<String> onlyInS2 = new HashSet<>(set2);

        // Remove every ride that also appears in set1.
        // What remains is only the rides that appear in S2.
        onlyInS2.removeAll(set1);

        System.out.println("Rides that appear only in S2:");
        for (String ride : onlyInS2) {
            System.out.println(ride);
        }

        System.out.println();

        // Rides in both S1 and S2:
        // Make a copy of set1 so the original set1 is not changed.
        Set<String> inBoth = new HashSet<>(set1);

        // Keep only the rides that also appear in set2.
        inBoth.retainAll(set2);

        System.out.println("Rides that appear in both S1 and S2:");
        for (String ride : inBoth) {
            System.out.println(ride);
        }
    }
}
