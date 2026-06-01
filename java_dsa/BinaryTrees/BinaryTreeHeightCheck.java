/*
This program demonstrates the idea that a binary tree with k leaves must have height at least log2(k).
 It creates several sample binary trees, counts the number of leaves in each tree, 
 calculates the height of each tree, and compares the height to log2(number of leaves).
  The output shows that in every case, the height is greater than or equal to log2(k),
   which supports the proof from the exercise.
*/

public class BinaryTreeHeightCheck {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    public static int height(Node root) {
        if (root == null) {
            return -1;
        }

        return 1 + Math.max(height(root.left), height(root.right));
    }

    public static int countLeaves(Node root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        return countLeaves(root.left) + countLeaves(root.right);
    }

    public static double log2(int k) {
        return Math.log(k) / Math.log(2);
    }

    public static void testTree(String name, Node root) {
        int h = height(root);
        int k = countLeaves(root);
        double logK = log2(k);

        System.out.println(name);
        System.out.println("Number of leaves: " + k);
        System.out.println("Height: " + h);
        System.out.println("log2(leaves): " + logK);
        System.out.println("Does height >= log2(leaves)? " + (h >= logK));
        System.out.println();
    }

    public static void main(String[] args) {

        Node tree1 = new Node(1);

        tree1.left = new Node(2);
        tree1.right = new Node(3);

        testTree("Tree 1", tree1);

        Node tree2 = new Node(1);

        tree2.left = new Node(2);
        tree2.right = new Node(3);

        tree2.left.left = new Node(4);
        tree2.left.right = new Node(5);

        tree2.right.left = new Node(6);
        tree2.right.right = new Node(7);

        testTree("Tree 2", tree2);

        Node tree3 = new Node(1);

        tree3.left = new Node(2);
        tree3.left.left = new Node(3);
        tree3.left.left.left = new Node(4);

        testTree("Tree 3", tree3);

        Node tree4 = new Node(1);

        tree4.left = new Node(2);
        tree4.right = new Node(3);

        tree4.left.left = new Node(4);
        tree4.left.right = new Node(5);

        tree4.right.right = new Node(6);
        tree4.right.right.left = new Node(7);
        tree4.right.right.right = new Node(8);

        testTree("Tree 4", tree4);
    }
}