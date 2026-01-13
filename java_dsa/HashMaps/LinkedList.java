
// Singly linked list used for HashMap buckets (stores key/value Nodes for separate chaining).

public class LinkedList {

    // First node in the list (null if the list is empty).
    public Node head;
    
// Creates an empty linked list.
    public LinkedList() {
        this.head = null;
    }
// Inserts a new node at the front of the list.
    public void addToHead(String key, String value){
        Node newHead = new Node(key, value);
        Node currentHead = this.head;
        this.head = newHead;
        if ( currentHead != null) {
            this.head.setNextNode(currentHead);

        }
    }
// Appends a new node to the end of the list.
    public void addToTail(String key, String value){
        Node tail = this.head;
        if ( tail == null){
            this.head = new Node (key,value);

        } else {
            while ( tail.getNextNode() != null) {
                tail = tail.getNextNode();

        }

        tail.setNextNode(new Node(key,value));

    }

}
// Removes the first node (head) from the list, if it exists.
    public void removeHead() {
    if (this.head == null) return;
    this.head = this.head.getNextNode();
}
 

// Removes the first node whose key matches; returns true if a node was removed.
 public boolean removeByKey(String key) {
    if (this.head == null) return false;

    // if head matches, remove head
    if (this.head.key != null && this.head.key.equals(key)) {
        this.head = this.head.getNextNode();
        return true;
    }

    Node prev = this.head;
    Node cur = this.head.getNextNode();
// Search for a matching key and unlink the node
    while (cur != null) {
        if (cur.key != null && cur.key.equals(key)) {
            prev.setNextNode(cur.getNextNode());  // unlink
            return true;
        }
        prev = cur;
        cur = cur.getNextNode();
    }

    return false; // key not found
}

}


    

    


    

    