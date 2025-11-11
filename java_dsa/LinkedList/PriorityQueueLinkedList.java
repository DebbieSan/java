/**
 * Class Description:
 * -------------------
 * 
 * PriorityQueueLinkedList
 * ------------------------
 * A simple priority queue implementation using a linked list.
 * Includes the remove() method that finds the minimum node, in this case a node
 * with the smallest number of Strings and
 * removes the node accordingly.The remove() methods functions as a deleteMin()
 * method in which the
 * smallest element is removed from the priority list.
 * 
 */

public class PriorityQueueLinkedList extends SinglyLinkedList {

    // A different add method from the SinglyLinkedList adapted for Priority Queue -
    // it basically adds to the tail.

    public void add(String data) {
        addToTail(data);

    }

    public String remove() {
        // Check if the list is empty
        if (head == null) {
            System.out.println("Priority queue is empty.");
            return null;
        }

        Node current = head; // current node for traversal
        Node previous = null; // previous node of current
        Node minNode = head; // node with smallest data
        Node minPrev = null; // node before minNode

        // Traverse the list to find the minimum node

        while (current != null) {
            if (current.data != null) { // only compare if current data is not null
                if (minNode.data == null || current.data.compareTo(minNode.data) < 0) {
                    minNode = current;
                    minPrev = previous;
                }
            }
            previous = current;
            current = current.getNextNode();
        }

        // Remove the minimum node
        if (minNode == null) {
            // Safety check: should never happen, but just in case
            return null;
        }

        if (minPrev == null) {
            // minNode is head
            head = head.getNextNode();
        } else {
            // bypass minNode
            minPrev.setNextNode(minNode.getNextNode());
        }

        size--;

        // Step 3: Return the data of the removed node
        return minNode.data;
    }

}
