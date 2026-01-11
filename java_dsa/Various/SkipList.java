import java.util.Random;

public class SkipList {
    private static final int MAX_LEVEL = 16;
    private static final double P = 0.5; // probability for level up

    // Node in the skip list
    private static class Node {
        int value;
        Node[] next; // next[i] is the pointer at level i

        Node(int level, int value) {
            this.value = value;
            this.next = new Node[level + 1]; // levels 0..level
        }
    }

    private final Node head = new Node(MAX_LEVEL, Integer.MIN_VALUE);
    private int level = 0; // current max level in the list
    private final Random rand = new Random();

    // Random height for a new node
    private int randomLevel() {
        int lvl = 0;
        while (lvl < MAX_LEVEL && rand.nextDouble() < P) {
            lvl++;
        }
        return lvl;
    }

    // Search: does x exist?
    public boolean contains(int x) {
        Node cur = head;
        for (int i = level; i >= 0; i--) {
            while (cur.next[i] != null && cur.next[i].value < x) {
                cur = cur.next[i];
            }
        }
        cur = cur.next[0];
        return cur != null && cur.value == x;
    }

    // Insert x (no duplicates)
    public void add(int x) {
        Node[] update = new Node[MAX_LEVEL + 1];
        Node cur = head;

        // 1) Find place to insert at each level
        for (int i = level; i >= 0; i--) {
            while (cur.next[i] != null && cur.next[i].value < x) {
                cur = cur.next[i];
            }
            update[i] = cur;
        }

        cur = cur.next[0];

        // already present → do nothing
        if (cur != null && cur.value == x)
            return;

        // 2) Choose random height for new node
        int newLevel = randomLevel();
        if (newLevel > level) {
            for (int i = level + 1; i <= newLevel; i++) {
                update[i] = head;
            }
            level = newLevel;
        }

        // 3) Insert new node, fix pointers on each level
        Node newNode = new Node(newLevel, x);
        for (int i = 0; i <= newLevel; i++) {
            newNode.next[i] = update[i].next[i];
            update[i].next[i] = newNode;
        }
    }

    // Print level 0 (the full sorted list)
    public void printLevel0() {
        Node cur = head.next[0];
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next[0];
        }
        System.out.println();
    }

    // Small demo
    public static void main(String[] args) {
        SkipList sl = new SkipList();
        sl.add(10);
        sl.add(3);
        sl.add(7);
        sl.add(15);
        sl.add(8);

        sl.printLevel0(); // should print numbers in sorted order

        System.out.println("Contains 7?  " + sl.contains(7));
        System.out.println("Contains 11? " + sl.contains(11));
    }
}
