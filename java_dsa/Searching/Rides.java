import java.util.Arrays;

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

        System.out.println(
                "Given two sequences S1 and S2, possibly containing duplicates,\n"
                        + "this program uses sorting to determine whether both sequences\n"
                        + "contain the same set of elements.");

        System.out.println("---------------------------------------------");

        System.out.println("Elements of S1:");
        for (String element : S1) {
            System.out.println(element);
        }

        System.out.println();

        System.out.println("Elements of S2:");
        for (String element : S2) {
            System.out.println(element);
        }

        System.out.println();

        /*
         * Algorithm:
         *
         * We want to determine whether S1 and S2 contain the same set of elements.
         * Since the sequences may contain duplicates, duplicate copies of an element
         * should not affect the answer.
         *
         * Because a total order relation is defined on the elements, we can sort
         * both sequences.
         *
         * Step 1:
         * Make copies of S1 and S2 so the original sequences are not changed.
         *
         * Step 2:
         * Sort both copied sequences.
         *
         * Step 3:
         * Scan through each sorted sequence and skip over duplicate elements.
         *
         * Step 4:
         * Compare the unique elements from both sorted sequences.
         *
         * If every unique element in S1 matches every unique element in S2,
         * then S1 and S2 contain the same set of elements.
         *
         * If one sequence has a unique element that the other does not have,
         * then they do not contain the same set of elements.
         */

        boolean sameSet = containSameSet(S1, S2);

        if (sameSet) {
            System.out.println("S1 and S2 contain the same set of elements.");
        } else {
            System.out.println("S1 and S2 do not contain the same set of elements.");
        }

        /*
         * Running Time Analysis:
         *
         * Let n be the number of elements in S1 and S2.
         *
         * Copying the sequences takes O(n) time.
         * Sorting S1 takes O(n log n) time.
         * Sorting S2 takes O(n log n) time.
         * Scanning through both sorted sequences takes O(n) time.
         *
         * Total time:
         * O(n) + O(n log n) + O(n log n) + O(n)
         * = O(n log n)
         *
         * Therefore, this algorithm runs in O(n log n) time.
         *
         * Space Analysis:
         *
         * We create two copied arrays, each storing up to n elements.
         * Therefore, the space complexity is O(n).
         */
    }

    public static boolean containSameSet(String[] S1, String[] S2) {

        String[] sortedS1 = Arrays.copyOf(S1, S1.length);
        String[] sortedS2 = Arrays.copyOf(S2, S2.length);

        Arrays.sort(sortedS1);
        Arrays.sort(sortedS2);

        int i = 0;
        int j = 0;

        while (i < sortedS1.length && j < sortedS2.length) {

            String currentS1 = sortedS1[i];
            String currentS2 = sortedS2[j];

            int comparison = currentS1.compareTo(currentS2);

            if (comparison != 0) {
                return false;
            }

            while (i < sortedS1.length && sortedS1[i].equals(currentS1)) {
                i++;
            }

            while (j < sortedS2.length && sortedS2[j].equals(currentS2)) {
                j++;
            }
        }

        return i == sortedS1.length && j == sortedS2.length;
    }
}
