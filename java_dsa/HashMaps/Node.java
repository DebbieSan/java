
//Node for a singly linked list used in HashMap buckets.
//Stores one key/value pair plus a pointer to the next node.


public class Node {

// Key for this map entry
  
    public String key;
  
// Value for this map entry.
 
    public String value;
  
// Reference to the next node in the chain (null if this is the last node).
 
    private Node next;
 
// Creates a node holding the given key/value pair (next starts as null).
 
    public Node (String key,String value){
        this.key = key;
        this.value = value;
        this.next = null;
    } 
// Sets the link to the next node in the list.

    public void setNextNode(Node node) {
        this.next = node;
    }

// Returns the next node in the list (or null if none).
    public Node getNextNode() {
        return this.next;
    }
// Updates this node's key/value pair (used when a key already exists).
        
    public void setKeyValue(String key, String value){
        this.key = key;
        this.value = value;
    }

    
}

