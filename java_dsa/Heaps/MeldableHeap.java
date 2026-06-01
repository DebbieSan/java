import java.util.Random;
/*
An implementation of a meldable heap that implements the remove(u) method.
 */

/*A meldable heap is a heap where the most important operation is merge. The merge method takes two heaps
 and combines them into one heap. Since this is a min-heap, the smaller root must stay above the larger root. 
 If the root of h2 is smaller than the root of h1, the two roots are swapped. 
 Then the method randomly chooses whether to merge into the left child or the right child of the smaller root.
The remove(Node u) method removes a specific node from the heap. 
First, it saves the value of u, because that is the value we want to return. 
Then it takes the left subtree of u and the right subtree of u. 
Both of these subtrees are already valid meldable heaps. 
To remove u, we merge those two subheaps together. 
The result is a new heap containing all the nodes that were below u, but not u itself.
After merging u.left and u.right, the method puts the merged heap in the same place where u used to be. 
If u was the root, then the merged heap becomes the new root. If u was the left child of its parent, 
then the merged heap becomes the new left child. If u was the right child of its parent, 
then the merged heap becomes the new right child. The parent pointers are also updated so the heap remains connected correctly.
The method runs in expected O(log n) time because it only performs one important expensive operation: merge(u.left, u.right). 
In a randomized meldable heap, merge randomly chooses to go left or right at each step. 
Because of this random choice, the expected length of the path followed during merging is logarithmic in the number of nodes. 
All the other steps in remove(u), such as checking the parent, changing pointers, and decreasing the size,
take constant time. Therefore, the total expected running time of remove(u) is O(log n).
 */

public class MeldableHeap<T extends Comparable<T>> {

    protected static class Node<T> {
        T x;
        Node<T> left;
        Node<T> right;
        Node<T> parent;

        Node(T x) {
            this.x = x;
        }
    }

    protected Node<T> root;
    protected int n;
    protected Random rand = new Random();

    public int size() {
        return n;
    }

    public Node<T> add(T x) {
        Node<T> u = new Node<>(x);
        root = merge(root, u);

        if (root != null) {
            root.parent = null;
        }

        n++;
        return u;
    }

    public T remove() {
        if (root == null) {
            return null;
        }

        return remove(root);
    }

    protected Node<T> merge(Node<T> h1, Node<T> h2) {
        if (h1 == null) {
            return h2;
        }

        if (h2 == null) {
            return h1;
        }

        if (h2.x.compareTo(h1.x) < 0) {
            Node<T> temp = h1;
            h1 = h2;
            h2 = temp;
        }

        if (rand.nextBoolean()) {
            h1.left = merge(h1.left, h2);

            if (h1.left != null) {
                h1.left.parent = h1;
            }
        } else {
            h1.right = merge(h1.right, h2);

            if (h1.right != null) {
                h1.right.parent = h1;
            }
        }

        return h1;
    }

    public T remove(Node<T> u) {
        if (u == null) {
            return null;
        }

        T removedValue = u.x;

        Node<T> parent = u.parent;
        Node<T> leftHeap = u.left;
        Node<T> rightHeap = u.right;

        if (leftHeap != null) {
            leftHeap.parent = null;
        }

        if (rightHeap != null) {
            rightHeap.parent = null;
        }

        Node<T> mergedHeap = merge(leftHeap, rightHeap);

        if (parent == null) {
            root = mergedHeap;
        } else if (parent.left == u) {
            parent.left = mergedHeap;
        } else {
            parent.right = mergedHeap;
        }

        if (mergedHeap != null) {
            mergedHeap.parent = parent;
        }

        u.left = null;
        u.right = null;
        u.parent = null;

        n--;
        return removedValue;
    }

    public void printHeap() {
        printHeap(root, 0);
    }

    private void printHeap(Node<T> u, int depth) {
        if (u == null) {
            return;
        }

        printHeap(u.right, depth + 1);

        for (int i = 0; i < depth; i++) {
            System.out.print("    ");
        }

        System.out.println(u.x);

        printHeap(u.left, depth + 1);
    }

    public static void main(String[] args) {
        MeldableHeap<Integer> heap = new MeldableHeap<>();

        Node<Integer> n10 = heap.add(10);
        Node<Integer> n4 = heap.add(4);
        Node<Integer> n15 = heap.add(15);
        Node<Integer> n2 = heap.add(2);
        Node<Integer> n8 = heap.add(8);
        Node<Integer> n20 = heap.add(20);
        Node<Integer> n6 = heap.add(6);

        System.out.println("Original heap:");
        heap.printHeap();
        System.out.println("Size: " + heap.size());

        System.out.println("\nRemoving specific node with value 10:");
        heap.remove(n10);
        heap.printHeap();
        System.out.println("Size: " + heap.size());

        System.out.println("\nRemoving specific node with value 4:");
        heap.remove(n4);
        heap.printHeap();
        System.out.println("Size: " + heap.size());

        System.out.println("\nRemoving root values one by one:");
        while (heap.size() > 0) {
            Integer removed = heap.remove();
            System.out.println("Removed: " + removed);
        }

        System.out.println("Final size: " + heap.size());
    }
}
