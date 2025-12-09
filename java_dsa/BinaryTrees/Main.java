public class Main {

    public static boolean exists(TreeNode root, int target) {

        if (root == null) {
            return false; // tree is empty
        }

        if (target == root.value) {
            return true; // found it
        }

        if (target < root.value) {
            return exists(root.left, target); // look in left subtree
        } else {
            return exists(root.right, target); // look in right subtree
        }

    }

    public static void main(String[] args) {

        // Implementing a Binary Search Tree

        TreeNode root;
        TreeNode child1;
        TreeNode child2;
        TreeNode child3;

        root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(20);
        child1 = root.left;
        child1.left = new TreeNode(3);
        child1.right = new TreeNode(7);
        child2 = root.right;
        child2.left = new TreeNode(11);
        child2.right = new TreeNode(25);
        child3 = child1.left;
        child3.left = new TreeNode(1);
        child3.right = new TreeNode(4);

        // Binary Search Tree - search results

        boolean inTree = exists(root, 3);
        System.out.println("is 3 in the tree? " + inTree);
        boolean inTree2 = exists(root, 10);
        System.out.println("How about 10, is it in the tree? " + inTree2);
        System.out.println("What is my root value? " + root.value);
        System.out.println("What are some of the values on this tree? " + root.value + " " + child1.value + " "
                + child2.value + " " + child3.value);

    }

}
