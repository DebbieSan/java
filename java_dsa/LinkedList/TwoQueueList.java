/**
 * Class Description
 * -------------------
 * 
 * TwoQueueLinkedList
 * -------------------
 * Implements a queue-based data structure using two singly linked lists.
 * Used to simulate stack behaviour by applying the methods push(), to add an
 * element to the list and
 * and pop() to remove the element from the list as a LIFO stack.
 * by transferring elements between two internal queues.
 */

public class TwoQueueList {

    private SinglyLinkedList q1 = new SinglyLinkedList();
    private SinglyLinkedList q2 = new SinglyLinkedList();

    // Push method to add element to queue as if it is a stack
    public void push(String data) {
        q1.addToTail(data);
    }

    // Pop method to remove Last In First Off style to simulate a stack
    public String pop() {
        if (q1.isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }

        // Move all elements except the last from q1 to q2
        while (q1.head != null && q1.head.getNextNode() != null) {
            String temp = q1.removeHead();
            q2.addToTail(temp);
        }

        // The last remaining element in q1 is the "top" of the stack
        String popped = q1.removeHead();

        // Swap q1 and q2 so q1 is the main queue again
        SinglyLinkedList tempList = q1;
        q1 = q2;
        q2 = tempList;

        return popped;
    }

    public boolean isEmpty() {
        return q1.isEmpty();
    }

    public void printStack() {
        q1.printLinkedList();
    }
}
