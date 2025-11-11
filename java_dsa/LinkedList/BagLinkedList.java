
/**
 * BagLinkedList
 * --------------
 * Implements the Bag interface using a linked list as the underlying structure.
 * Allows duplicate elements and provides methods to add, remove,
 * find a single element, or find all matching elements.
 */

import java.util.ArrayList;
import java.util.List;

public class BagLinkedList extends DoublyLinkedList implements Bag {

    @Override
    public void add(String x) {
        // Add element to tail
        this.addToTail(x);
    }

    @Override
    public boolean remove(String x) {
        DoubleNode current = (DoubleNode) this.head;

        while (current != null) {
            if (current.data.equals(x)) {
                DoubleNode prev = current.prev;
                DoubleNode next = (DoubleNode) current.next;

                if (prev != null) {
                    prev.next = next;
                } else {
                    head = next; // removing head
                }

                if (next != null) {
                    next.prev = prev;
                } else {
                    tail = prev; // removing tail
                }

                size--;
                return true; // removed one occurrence
            }
            current = (DoubleNode) current.next;
        }
        return false; // element not found
    }

    @Override
    public String find(String x) {
        DoubleNode current = (DoubleNode) this.head;

        while (current != null) {
            if (current.data.equals(x)) {
                return current.data; // first occurrence
            }
            current = (DoubleNode) current.next;
        }
        return null; // not found
    }

    @Override
    public List<String> findAll(String x) {
        List<String> all = new ArrayList<>();
        DoubleNode current = (DoubleNode) this.head;

        while (current != null) {
            if (current.data.equals(x)) {
                all.add(current.data);
            }
            current = (DoubleNode) current.next;
        }
        return all;
    }

    @Override
    public int size() {
        return this.size;
    }
}
