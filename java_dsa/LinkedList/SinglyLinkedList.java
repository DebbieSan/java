/**
 * Class Description:
 * -------------------
 * 
 * SinglyLinkedList
 * ----------------
 * A basic linked list implementation where each node points only to the next
 * node.
 * Supports standard list operations such as add, remove, and traversal as well
 * as added methods to
 * swapNext() and swapElements() in which on can swap adjacent elements and any
 * elements inside a
 * SinglyLinkedList.
 * Used as a base class for other linked structures like queues and priority
 * lists.
 */

public class SinglyLinkedList {

    protected Node head;
    protected int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;

    }

    // add method to add to the head and increase size to ensure the size of the
    // list stays up to date

    public void addToHead(String data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    // add method to add to the tail and increase size to ensure the size of the
    // list stays up to date

    public void addToTail(String data) {
        Node tail = this.head;
        if (tail == null) {
            this.head = new Node(data);
        } else {
            while (tail.getNextNode() != null) {
                tail = tail.getNextNode();
            }
            tail.setNextNode(new Node(data));
        }
        size++;
    }

    // remove the head and returns the first element and reduces size to ensure the
    // list size is kept up to date

    public String removeHead() {
        Node removedHead = this.head;
        if (removedHead == null) {
            System.out.println("There are no elements in the list.");
            return null;
        }
        this.head = removedHead.getNextNode();
        size--;
        return removedHead.data;

    }

    public boolean isEmpty() {
        return head == null;
    }

    // this method returns the size of the list

    public int size() {
        System.out.println("This size of this list is " + size);
        return size;
    }

    // prints the elements inside the linked list

    public String printLinkedList() {
        String result = "<head> ";
        Node current = head;

        while (current != null) {
            result += current.data + " ";
            current = current.next;
        }

        result += "<tail>";
        System.out.println(result);
        return result;
    }

    // Method to swap any element in a SinglyLinkedList

    public static void swapElements(SinglyLinkedList list, String data1, String data2) {
        System.out.println("Swapping " + data1 + " with " + data2);

        if (data1.equals(data2)) {
            System.out.println("Elements are the same, no swap needed.");
            return;
        }

        Node prev1 = null, prev2 = null;
        Node node1 = list.head, node2 = list.head;

        // Find node1 and its previous node
        while (node1 != null && !node1.data.equals(data1)) {
            prev1 = node1;
            node1 = node1.next;
        }

        // Find node2 and its previous node
        while (node2 != null && !node2.data.equals(data2)) {
            prev2 = node2;
            node2 = node2.next;
        }

        // If either node isn't found, stop
        if (node1 == null || node2 == null) {
            System.out.println("One or both elements not found. Swap aborted.");
            return;
        }

        // If node1 is not head
        if (prev1 != null) {
            prev1.next = node2;
        } else {
            list.head = node2;
        }

        // If node2 is not head
        if (prev2 != null) {
            prev2.next = node1;
        } else {
            list.head = node1;
        }

        // Swap the next pointers
        Node temp = node1.next;
        node1.next = node2.next;
        node2.next = temp;
    }

    // Method to swap adjacent elements in a SinglyLinkedList - this only works for
    // data1 immediately before data2-

    public static void swapNext(SinglyLinkedList list, String data1, String data2) {
        if (list.head == null || list.head.next == null) {
            System.out.println("List too short to swap.");
            return;
        }

        Node prev = null;
        Node current = list.head;

        // Find the first of the two adjacent nodes
        while (current != null && current.next != null) {
            if (current.data.equals(data1) && current.next.data.equals(data2)) {
                break;
            }
            prev = current;
            current = current.next;
        }

        // If not found, stop
        if (current == null || current.next == null) {
            System.out.println("Adjacent nodes not found.");
            return;
        }

        Node next = current.next;
        Node after = next.next;

        // connecting nodes
        if (prev != null) {
            prev.next = next;
        } else {
            // If swapping involves the head
            list.head = next;
        }

        next.next = current;
        current.next = after;

        System.out.println("Swapped adjacent nodes " + data1 + " and " + data2);
    }

}
