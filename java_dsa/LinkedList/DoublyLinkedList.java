/**
 * Class Description:
 * ------------------
 * 
 * DoublyLinkedList
 * ----------------
 * Extends SinglyLinkedList to include links in both directions (next and prev).
 * Allows efficient traversal forward and backward.
 * Includes operations such as addToHead, addToTail, swapNext() and reverse().
 *
 * Inner Class: DoubleNode
 * -----------------------
 * Represents a node in a doubly linked list, containing links to both
 * the previous and next nodes, in addition to storing the data.
 */

public class DoublyLinkedList extends SinglyLinkedList {

    // redefine Node type for doubly-linked structure
    public static class DoubleNode extends Node {
        DoubleNode prev;

        DoubleNode(String data) {
            super(data);
            this.prev = null;
        }
    }

    public DoubleNode tail;

    public DoublyLinkedList() {
        super();
        this.tail = null;
    }

    @Override
    public void addToHead(String data) {
        DoubleNode newNode = new DoubleNode(data);
        newNode.next = head;
        if (head != null && head instanceof DoubleNode) {
            ((DoubleNode) head).prev = newNode;
        }
        head = newNode;
        if (tail == null) {
            tail = newNode;
        }
        size++;
    }

    @Override
    public void addToTail(String data) {
        DoubleNode newNode = new DoubleNode(data);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void printDoublyLinkedList() {
        DoubleNode current = (DoubleNode) head;
        String result = "<head> ";
        while (current != null) {
            result += current.data + " ";
            current = (DoubleNode) current.next;
        }
        result += "<tail>";
        System.out.println(result);
    }

    public static void swapNext(DoublyLinkedList list, String data1, String data2) {
        if (list.head == null || list.head.next == null) {
            System.out.println("There isn't enough nodes to swap.");
            return;
        }

        // covert the head from a generic Node to a Double Node
        DoubleNode current = (DoubleNode) list.head;

        // Find the first node where data1 and data2 are adjacent
        while (current != null && current.next != null) {
            if (current.data.equals(data1) && ((DoubleNode) current.next).data.equals(data2)) {
                break;
            }
            current = (DoubleNode) current.next;
        }

        // If not found, stop
        if (current == null || current.next == null) {
            System.out.println("Adjacent nodes not found.");
            return;
        }

        DoubleNode first = current;
        DoubleNode second = (DoubleNode) first.next;
        DoubleNode before = first.prev;
        DoubleNode after = (DoubleNode) second.next;

        // link 'before' to 'second'
        if (before != null) {
            before.next = second;
        } else {
            // if swapping involves the head
            list.head = second;
        }

        // link 'after' back to 'first'
        if (after != null) {
            after.prev = first;
        } else {
            // if swapping involves the tail
            list.tail = first;
        }

        // swap the pair
        second.prev = before;
        first.prev = second;
        first.next = after;
        second.next = first;

        System.out.println("Swapped adjacent nodes " + data1 + " and " + data2);
    }

    public void reverse() {
        DoubleNode current = (DoubleNode) head;
        DoubleNode temp = null;

        // Traverse the list and swap next and prev for each node
        while (current != null) {
            // Swap the next and prev references
            temp = current.prev;
            current.prev = (DoubleNode) current.next;
            current.next = temp;

            // Move to the next node (which is the previous node after swap)
            current = current.prev;
        }

        // After traversal, adjust the head of the list
        if (temp != null) {
            head = temp.prev; // temp will be at the old tail after the loop
        }
    }

}
