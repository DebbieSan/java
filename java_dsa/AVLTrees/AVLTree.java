// Source idea adapted from:
// https://www.geeksforgeeks.org/java/avl-tree-program-in-java/

import java.util.ArrayList;

class TreeNode {
    int value;
    int height;
    String color;

    TreeNode left;
    TreeNode right;

    TreeNode(int value) {
        this.value = value;
        this.height = 1;
        this.color = "BLACK";
    }
}

public class AVLTree {

    TreeNode root;

    // Basic AVL methods

    int getHeight(TreeNode current) {
        if (current == null) {
            return 0;
        }
        return current.height;
    }

    int max(int first, int second) {
        return (first > second) ? first : second;
    }

    int getBalanceFactor(TreeNode current) {
        if (current == null) {
            return 0;
        }
        return getHeight(current.left) - getHeight(current.right);
    }

    TreeNode rotateRight(TreeNode current) {
        TreeNode newRoot = current.left;
        TreeNode movedSubtree = newRoot.right;

        /*
         * Before right rotation:
         *
         * current
         * /
         * newRoot
         * \
         * movedSubtree
         *
         * After right rotation:
         *
         * newRoot
         * \
         * current
         * /
         * movedSubtree
         */

        newRoot.right = current;
        current.left = movedSubtree;

        current.height = max(getHeight(current.left), getHeight(current.right)) + 1;
        newRoot.height = max(getHeight(newRoot.left), getHeight(newRoot.right)) + 1;

        return newRoot;
    }

    TreeNode rotateLeft(TreeNode current) {
        TreeNode newRoot = current.right;
        TreeNode movedSubtree = newRoot.left;

        /*
         * Before left rotation:
         *
         * current
         * \
         * newRoot
         * /
         * movedSubtree
         *
         * After left rotation:
         *
         * newRoot
         * /
         * current
         * \
         * movedSubtree
         */

        newRoot.left = current;
        current.right = movedSubtree;

        current.height = max(getHeight(current.left), getHeight(current.right)) + 1;
        newRoot.height = max(getHeight(newRoot.left), getHeight(newRoot.right)) + 1;

        return newRoot;
    }

    TreeNode addAVL(TreeNode current, int value) {
        if (current == null) {
            return new TreeNode(value);
        }

        if (value < current.value) {
            current.left = addAVL(current.left, value);
        } else if (value > current.value) {
            current.right = addAVL(current.right, value);
        } else {
            return current; // Ignore duplicate values
        }

        current.height = max(getHeight(current.left), getHeight(current.right)) + 1;

        int balance = getBalanceFactor(current);

        // Left Left case
        if (balance > 1 && value < current.left.value) {
            return rotateRight(current);
        }

        // Right Right case
        if (balance < -1 && value > current.right.value) {
            return rotateLeft(current);
        }

        // Left Right case
        if (balance > 1 && value > current.left.value) {
            current.left = rotateLeft(current.left);
            return rotateRight(current);
        }

        // Right Left case
        if (balance < -1 && value < current.right.value) {
            current.right = rotateRight(current.right);
            return rotateLeft(current);
        }

        return current;
    }

    // Normal BST methods

    TreeNode addBST(TreeNode current, int value) {
        if (current == null) {
            return new TreeNode(value);
        }

        if (value < current.value) {
            current.left = addBST(current.left, value);
        } else if (value > current.value) {
            current.right = addBST(current.right, value);
        }

        return current;
    }

    boolean searchBST(TreeNode current, int value) {
        if (current == null) {
            return false;
        }

        if (value == current.value) {
            return true;
        } else if (value < current.value) {
            return searchBST(current.left, value);
        } else {
            return searchBST(current.right, value);
        }
    }

    // Traversal and printing methods

    void printInOrder(TreeNode current) {
        if (current != null) {
            printInOrder(current.left);
            System.out.print(current.value + " ");
            printInOrder(current.right);
        }
    }

    void printPreOrder(TreeNode current) {
        if (current != null) {
            System.out.print(current.value + " ");
            printPreOrder(current.left);
            printPreOrder(current.right);
        }
    }

    void printPostOrder(TreeNode current) {
        if (current != null) {
            printPostOrder(current.left);
            printPostOrder(current.right);
            System.out.print(current.value + " ");
        }
    }

    void printInOrderWithColors(TreeNode current) {
        if (current != null) {
            printInOrderWithColors(current.left);
            System.out.println("Node " + current.value + " is " + current.color);
            printInOrderWithColors(current.right);
        }
    }

    void printTreeShape(TreeNode current, int spaces) {
        if (current == null) {
            return;
        }

        spaces += 5;

        printTreeShape(current.right, spaces);

        System.out.println();
        for (int i = 5; i < spaces; i++) {
            System.out.print(" ");
        }
        System.out.println(current.value + "(" + current.color.charAt(0) + ")");

        printTreeShape(current.left, spaces);
    }

    // Part 1: Coloring an AVL tree as a red-black tree

    /*
     * Red-black tree properties:
     *
     * 1. Every node is either red or black.
     * 2. The root is black.
     * 3. All null leaves are considered black.
     * 4. A red node cannot have a red child.
     * 5. Every path from a node to a null leaf must contain the same
     * number of black nodes.
     *
     * Since an AVL tree is already height-balanced, it can be colored
     * so that it satisfies the red-black tree rules.
     *
     * The code below demonstrates this by trying valid color choices
     * for each subtree.
     */

    class ColorChoice {
        String color;
        int blackHeight;
        ColorChoice leftChoice;
        ColorChoice rightChoice;

        ColorChoice(String color, int blackHeight, ColorChoice leftChoice, ColorChoice rightChoice) {
            this.color = color;
            this.blackHeight = blackHeight;
            this.leftChoice = leftChoice;
            this.rightChoice = rightChoice;
        }
    }

    ArrayList<ColorChoice> findColorChoices(TreeNode current) {
        ArrayList<ColorChoice> choices = new ArrayList<>();

        /*
         * A null child is treated as a black leaf.
         * In this program, its black height starts at 0.
         */
        if (current == null) {
            choices.add(new ColorChoice("BLACK", 0, null, null));
            return choices;
        }

        ArrayList<ColorChoice> leftChoices = findColorChoices(current.left);
        ArrayList<ColorChoice> rightChoices = findColorChoices(current.right);

        for (ColorChoice left : leftChoices) {
            for (ColorChoice right : rightChoices) {

                /*
                 * Both subtrees need to have the same black height.
                 */
                if (left.blackHeight == right.blackHeight) {

                    /*
                     * Choice 1:
                     * Make this node black.
                     */
                    choices.add(new ColorChoice(
                            "BLACK",
                            left.blackHeight + 1,
                            left,
                            right));

                    /*
                     * Choice 2:
                     * Make this node red.
                     * This is only allowed when both children are black.
                     */
                    if (left.color.equals("BLACK") && right.color.equals("BLACK")) {
                        choices.add(new ColorChoice(
                                "RED",
                                left.blackHeight,
                                left,
                                right));
                    }
                }
            }
        }

        return choices;
    }

    void applyColors(TreeNode current, ColorChoice choice) {
        if (current == null || choice == null) {
            return;
        }

        current.color = choice.color;
        applyColors(current.left, choice.leftChoice);
        applyColors(current.right, choice.rightChoice);
    }

    void colorAVLAsRedBlackTree() {
        ArrayList<ColorChoice> choices = findColorChoices(root);

        for (ColorChoice choice : choices) {
            if (choice.color.equals("BLACK")) {
                applyColors(root, choice);
                return;
            }
        }

        System.out.println("No valid red-black coloring was found.");
    }

    void demonstrateAVLCanBeColoredAsRedBlackTree() {
        System.out.println();
        System.out.println("Part 1: Coloring an AVL tree as a red-black tree");

        System.out.println();
        System.out.println("Red-black tree properties:");
        System.out.println("1. Every node is red or black.");
        System.out.println("2. The root is black.");
        System.out.println("3. Null leaves are black.");
        System.out.println("4. A red node cannot have a red child.");
        System.out.println("5. Every path from a node to a null leaf has the same black height.");

        System.out.println();
        System.out.println("Since an AVL tree is already height-balanced, it can be colored");
        System.out.println("so that it satisfies the red-black tree rules.");
        System.out.println("The program below demonstrates this by assigning colors recursively.");

        System.out.println();
        System.out.println("AVL tree before coloring:");
        printTreeShape(root, 0);

        colorAVLAsRedBlackTree();

        System.out.println();
        System.out.println("AVL tree after red-black coloring:");
        printTreeShape(root, 0);

        System.out.println();
        System.out.println("Inorder traversal with colors:");
        printInOrderWithColors(root);

        System.out.println();
    }

    // Part 2: Showing that a single rotation keeps the BST property

    void demonstrateSingleRotationTransformsBST() {
        System.out.println();
        System.out.println("Part 2: Single AVL rotation transforms BST T1 into BST T2");

        /*
         * T1:
         *
         * 30
         * /
         * 20
         * /
         * 10
         *
         * This is a valid BST because 10 < 20 < 30.
         */

        TreeNode t1 = new TreeNode(30);
        t1.left = new TreeNode(20);
        t1.left.left = new TreeNode(10);

        t1.left.left.height = 1;
        t1.left.height = 2;
        t1.height = 3;

        System.out.println();
        System.out.println("BST T1 before rotation:");
        printTreeShape(t1, 0);

        System.out.print("Inorder traversal of T1: ");
        printInOrder(t1);
        System.out.println();

        /*
         * Apply a single right rotation at 30.
         *
         * T2:
         *
         * 20
         * / \
         * 10 30
         *
         * T2 is also a valid BST.
         */

        TreeNode t2 = rotateRight(t1);

        System.out.println();
        System.out.println("BST T2 after one right rotation:");
        printTreeShape(t2, 0);

        System.out.print("Inorder traversal of T2: ");
        printInOrder(t2);
        System.out.println();

        System.out.println();
        System.out.println("Explanation:");
        System.out.println("The inorder traversal before and after the rotation is the same.");
        System.out.println("This shows that the rotation keeps the BST ordering.");
        System.out.println("The rotation changes the shape of the tree, but not the stored values.");

        System.out.println();
    }

    // Part 3: Building T2 from T1 using AVL insertions

    void storeInOrder(TreeNode current, ArrayList<Integer> values) {
        if (current != null) {
            storeInOrder(current.left, values);
            values.add(current.value);
            storeInOrder(current.right, values);
        }
    }

    /*
     * This algorithm builds another BST, called T2, using the same values as T1.
     *
     * First, it does an inorder traversal of T1.
     * Since T1 is a BST, this gives the values in sorted order.
     *
     * Then, each value is inserted into a new AVL tree.
     * AVL insertion uses rotations when the tree becomes unbalanced.
     *
     * Note:
     * This version creates a new tree T2.
     * It does not rotate the original T1 directly.
     *
     * Time complexity:
     * - The inorder traversal takes O(N).
     * - Each AVL insertion takes O(log N).
     * - N insertions take O(N log N).
     * - So the total time is O(N log N).
     */
    TreeNode transformT1ToT2UsingAVLInsertions(TreeNode t1) {
        ArrayList<Integer> values = new ArrayList<>();

        storeInOrder(t1, values);

        TreeNode t2 = null;

        for (int value : values) {
            t2 = addAVL(t2, value);
        }

        return t2;
    }

    void demonstrateT1ToT2Algorithm() {
        System.out.println();
        System.out.println("Part 3: Algorithm to transform BST T1 into BST T2");

        TreeNode t1 = null;

        /*
         * Build an unbalanced BST T1.
         */
        t1 = addBST(t1, 15);
        t1 = addBST(t1, 35);
        t1 = addBST(t1, 45);
        t1 = addBST(t1, 60);
        t1 = addBST(t1, 75);
        t1 = addBST(t1, 40);

        System.out.println();
        System.out.println("BST T1 before transformation:");
        printTreeShape(t1, 0);

        System.out.print("Items in T1 using inorder traversal: ");
        printInOrder(t1);
        System.out.println();

        TreeNode t2 = transformT1ToT2UsingAVLInsertions(t1);

        System.out.println();
        System.out.println("BST T2 after using AVL insertions:");
        printTreeShape(t2, 0);

        System.out.print("Items in T2 using inorder traversal: ");
        printInOrder(t2);
        System.out.println();

        System.out.println();
        System.out.println("Algorithm:");
        System.out.println("1. Do an inorder traversal of T1.");
        System.out.println("2. Store the values from T1 in an ArrayList.");
        System.out.println("3. Create an empty tree T2.");
        System.out.println("4. Insert each value into T2 using AVL insertion.");
        System.out.println("5. When T2 becomes unbalanced, AVL rotations are used.");

        System.out.println();
        System.out.println("Time complexity:");
        System.out.println("The inorder traversal takes O(N) time.");
        System.out.println("Each AVL insertion takes O(log N) time.");
        System.out.println("Since there are N insertions, this gives O(N log N) time overall.");

        System.out.println();
        System.out.println("Note:");
        System.out.println("This version creates a new tree T2 using the same values from T1.");
        System.out.println("It does not rotate the original T1 directly.");
        System.out.println("AVL rotations still happen while T2 is being built.");

        System.out.println();
    }

    // Main method

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        /*
         * Build an AVL tree for Part 1.
         */
        tree.root = tree.addAVL(tree.root, 15);
        tree.root = tree.addAVL(tree.root, 35);
        tree.root = tree.addAVL(tree.root, 45);
        tree.root = tree.addAVL(tree.root, 60);
        tree.root = tree.addAVL(tree.root, 75);
        tree.root = tree.addAVL(tree.root, 40);

        System.out.println("AVL preorder traversal:");
        tree.printPreOrder(tree.root);
        System.out.println();

        System.out.println("AVL inorder traversal:");
        tree.printInOrder(tree.root);
        System.out.println();

        System.out.println("AVL postorder traversal:");
        tree.printPostOrder(tree.root);
        System.out.println();

        tree.demonstrateAVLCanBeColoredAsRedBlackTree();

        tree.demonstrateSingleRotationTransformsBST();

        tree.demonstrateT1ToT2Algorithm();
    }
}
