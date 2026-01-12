// A small program to find a node recursively inside a linked list.

public class LinkedList {

    public Node head;

    public LinkedList() {
        this.head = null;
    }

    public void addToHead(String data) {
        Node newHead = new Node(data);
        Node currentHead = this.head;
        this.head = newHead;
        if (currentHead != null) {
            this.head.setNextNode(currentHead);
        }
    }

    public Node findNode(String data, Node currentNode) {
        if (currentNode == null) {
            return null;
        } else if (currentNode.data == data) {
            return currentNode;
        } else {
            return findNode(data, currentNode.getNextNode());
        }
    }

    public static void main(String[] args) {

        LinkedList recursiveList = new LinkedList();

        recursiveList.addToHead("Apples");
        recursiveList.addToHead("Bananas");
        recursiveList.addToHead("Avocados");
        recursiveList.addToHead("Tomatoes");
        recursiveList.addToHead("Cucumbers");

        Node foundNode = recursiveList.findNode("Tomatoes", recursiveList.head);
        Node notFound = recursiveList.findNode("Lettuce", recursiveList.head);

        // Output should be "Tomatoes"

        System.out.println(foundNode.data);

        // This should intentionally cause a NullPointerException.

        System.out.println(notFound.data);

    }

}
