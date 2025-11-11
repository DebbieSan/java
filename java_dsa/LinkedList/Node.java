/**
 * Class Description:
 * -------------------
 * 
 * Node
 * ----
 * Represents a single element in a singly linked list.
 * Contains the stored data and a reference to the next node in the list.
 * Used as the building block for SinglyLinkedList and other classes in this
 * package.
 */

public class Node {

    String data;
    Node next;
    Node previous;

    public Node(String data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    // Setters
    public void setNextNode(Node node) {
        this.next = node;
    }

    public void setPreviousNode(Node node) {
        this.previous = node;
    }

    // Getters
    public Node getNextNode() {
        return this.next;
    }

    public Node getPreviousNode() {
        return this.previous;
    }

    public String getData() {
        return this.data;
    }

}
