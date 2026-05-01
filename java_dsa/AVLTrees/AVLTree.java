//Source: https://www.geeksforgeeks.org/java/avl-tree-program-in-java/

class TreeNode {
    int value;
    int height;
    String color;

    TreeNode left;
    TreeNode right;

    TreeNode(int value) {
        this.value = value;
        this.height = 1;
        this.color = "BLACK"; // default color
    }

}

public class AVLTree {

    TreeNode root;

    // Returns the height of a node.
    // Empty nodes have height 0.
    int getHeight(TreeNode current) {
        if (current == null) {
            return 0;
        }
        return current.height;
    }

    // Returns whichever number is larger.
    int bigger(int first, int second) {
        return (first > second) ? first : second;
    }

    // Fixes a left-heavy subtree by rotating it to the right.
    TreeNode rotateRight(TreeNode current) {
        TreeNode newRoot = current.left;
        TreeNode movedSubtree = newRoot.right;

        // Move nodes into their new positions
        newRoot.right = current;
        current.left = movedSubtree;

        // Recalculate heights after the rotation
        current.height = bigger(getHeight(current.left), getHeight(current.right)) + 1;
        newRoot.height = bigger(getHeight(newRoot.left), getHeight(newRoot.right)) + 1;

        return newRoot;
    }

    // Fixes a right-heavy subtree by rotating it to the left.
    TreeNode rotateLeft(TreeNode current) {
        TreeNode newRoot = current.right;
        TreeNode movedSubtree = newRoot.left;

        // Move nodes into their new positions
        newRoot.left = current;
        current.right = movedSubtree;

        // Recalculate heights after the rotation
        current.height = bigger(getHeight(current.left), getHeight(current.right)) + 1;
        newRoot.height = bigger(getHeight(newRoot.left), getHeight(newRoot.right)) + 1;

        return newRoot;
    }

    // Balance factor = height of left subtree - height of right subtree.
    int balanceFactor(TreeNode current) {
        if (current == null) {
            return 0;
        }
        return getHeight(current.left) - getHeight(current.right);
    }

    // Adds a value to the AVL tree and keeps the tree balanced.
    TreeNode add(TreeNode current, int value) {
        if (current == null) {
            return new TreeNode(value);
        }

        if (value < current.value) {
            current.left = add(current.left, value);
        } else if (value > current.value) {
            current.right = add(current.right, value);
        } else {
            return current; // Duplicate values are ignored
        }

        // Update this node's height
        current.height = bigger(getHeight(current.left), getHeight(current.right)) + 1;

        // Check whether this node became unbalanced
        int balance = balanceFactor(current);

        // Case 1: inserted into the left side of the left child
        if (balance > 1 && value < current.left.value) {
            return rotateRight(current);
        }

        // Case 2: inserted into the right side of the right child
        if (balance < -1 && value > current.right.value) {
            return rotateLeft(current);
        }

        // Case 3: inserted into the right side of the left child
        if (balance > 1 && value > current.left.value) {
            current.left = rotateLeft(current.left);
            return rotateRight(current);
        }

        // Case 4: inserted into the left side of the right child
        if (balance < -1 && value < current.right.value) {
            current.right = rotateRight(current.right);
            return rotateLeft(current);
        }

        return current;
    }

    // Prints the root first, then left subtree, then right subtree.
    void printPreOrder(TreeNode current) {
        if (current != null) {
            System.out.print(current.value + " ");
            printPreOrder(current.left);
            printPreOrder(current.right);
        }
    }

    // Prints values from smallest to largest.
    void printInOrder(TreeNode current) {
        if (current != null) {
            printInOrder(current.left);
            System.out.print(current.value + " ");
            printInOrder(current.right);
        }
    }

    // Prints children before their parent.
    void printPostOrder(TreeNode current) {
        if (current != null) {
            printPostOrder(current.left);
            printPostOrder(current.right);
            System.out.print(current.value + " ");
        }
    }

    // Red-black tree demonstration

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

    // Find possible valid red-black colorings for a subtree
    java.util.ArrayList<ColorChoice> findColorChoices(TreeNode current) {
        java.util.ArrayList<ColorChoice> choices = new java.util.ArrayList<>();

        // Null children are counted as black leaves
        if (current == null) {
            choices.add(new ColorChoice("BLACK", 0, null, null));
            return choices;
        }

        java.util.ArrayList<ColorChoice> leftChoices = findColorChoices(current.left);
        java.util.ArrayList<ColorChoice> rightChoices = findColorChoices(current.right);

        for (ColorChoice left : leftChoices) {
            for (ColorChoice right : rightChoices) {

                // Both sides must have the same black height
                if (left.blackHeight == right.blackHeight) {

                    // Option 1: make current node black
                    choices.add(new ColorChoice(
                            "BLACK",
                            left.blackHeight + 1,
                            left,
                            right));

                    // Option 2: make current node red
                    // Red nodes are only allowed to have black children
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

    // Apply one selected coloring to the actual AVL tree nodes
    void applyColors(TreeNode current, ColorChoice choice) {
        if (current == null || choice == null) {
            return;
        }

        current.color = choice.color;

        applyColors(current.left, choice.leftChoice);
        applyColors(current.right, choice.rightChoice);
    }

    /**
     * Color the AVL tree using red-black rules
     * This method demonstrates that an AVL tree can be colored
     * as a red-black tree by choosing colors that satisfy:
     * 1. The root is black.
     * 2. No red node has a red child.
     * 3. Every path from a node to a null child has the same black height.
     * Since AVL trees are height-balanced, this method shows that we can assign red
     * and black colors to the AVL tree nodes while keeping the red-black
     * properties. The tree structure does not change; only the node colors are
     * assigned.
     */

    void colorAsRedBlackTree() {
        java.util.ArrayList<ColorChoice> choices = findColorChoices(root);

        for (ColorChoice choice : choices) {
            // The root of a red-black tree must be black
            if (choice.color.equals("BLACK")) {
                applyColors(root, choice);
                return;
            }
        }

        System.out.println("No valid red-black coloring was found.");
    }

    // Print values with their assigned colors
    void printInOrderWithColors(TreeNode current) {
        if (current != null) {
            printInOrderWithColors(current.left);
            System.out.println("Node " + current.value + " is " + current.color);
            printInOrderWithColors(current.right);
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        tree.root = tree.add(tree.root, 15);
        tree.root = tree.add(tree.root, 35);
        tree.root = tree.add(tree.root, 45);
        tree.root = tree.add(tree.root, 60);
        tree.root = tree.add(tree.root, 75);
        tree.root = tree.add(tree.root, 40);

        System.out.println("Preorder traversal:");
        tree.printPreOrder(tree.root);
        System.out.println();

        System.out.println("Inorder traversal:");
        tree.printInOrder(tree.root);
        System.out.println();

        System.out.println("Postorder traversal:");
        tree.printPostOrder(tree.root);
        System.out.println();

        tree.colorAsRedBlackTree();

        System.out.println("AVL tree colored as a red-black tree:");
        tree.printInOrderWithColors(tree.root);
    }
}
