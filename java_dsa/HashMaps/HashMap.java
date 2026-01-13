/*
A HashMap exercise that implements a basic hash map using separate chaining with linked lists. 
The program uses a bird census demo to store, retrieve, and delete bird-location entries.
 */

public class HashMap {

    // Creates a hash map with a fixed number of buckets (separate chaining using
    // linked lists).
    public LinkedList[] hashmap;

    public HashMap(int size) {
        this.hashmap = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            this.hashmap[i] = new LinkedList();

        }
    }

    // Computes the bucket index for a key by summing character codes and taking mod
    // table size.
    public int hash(String key) {
        int hashCode = 0;
        for (int i = 0; i < key.length(); i++) {
            hashCode = hashCode + Character.codePointAt(key, i);

        }

        hashCode = hashCode % this.hashmap.length;

        return hashCode;
    }

    // Inserts a new key/value pair or updates the value if the key already exists
    // in its bucket.
    public void assign(String key, String value) {
        int arrayIndex = this.hash(key);
        LinkedList list = this.hashmap[arrayIndex];
        if (list.head == null) {
            list.addToHead(key, value);
            return;
        }
        Node current = list.head;
        while (current != null) {
            if (current.key != null && current.key.equals(key)) {
                current.setKeyValue(key, value);
                return;
            }
            if (current.getNextNode() == null) {
                current.setNextNode(new Node(key, value));
                break;
            }
            current = current.getNextNode();
        }
    }

    // Looks up a key and returns its value, or null if the key is not found.
    public String retrieve(String key) {
        int arrayIndex = this.hash(key);
        Node current = this.hashmap[arrayIndex].head;
        while (current != null) {
            if (current.key != null && current.key.equals(key)) {
                return current.value;
            }
            current = current.getNextNode();
        }
        return null;
    }

    // Removes the key/value pair for the given key; returns true if something was
    // removed.
    public boolean delete(String key) {
        int arrayIndex = this.hash(key);
        LinkedList list = this.hashmap[arrayIndex];
        return list.removeByKey(key);
    }

    public static void main(String[] args) {

        // Demo: adds sample birds, prints their locations, then deletes one and shows
        // it is gone.

        HashMap birdCensus = new HashMap(15);

        // assigning birds to their location
        birdCensus.assign("mandarin duck", "Central Park Pond");
        birdCensus.assign("monk parakeet", "Brooklyn College");
        birdCensus.assign("horned owl", "Pelham Bay Park");

        // intro to bird census

        System.out.println("-------------------------------------------------------");
        System.out.println("Welcome to the Bird Census Map List!");
        System.out.println("-------------------------------------------------------");

        System.out.println(
                "Currently we have the following birds in our census: Mandarin Duck, Monk Parakeet and Horned owl.");

        System.out.println("");

        System.out.println("Here are their locations");

        System.out.println("");

        // demo of the birds locations based on the key
        System.out.println(birdCensus.retrieve("mandarin duck"));
        System.out.println(birdCensus.retrieve("monk parakeet"));
        System.out.println(birdCensus.retrieve("horned owl"));

        System.out.println("");

        // checking to see if a bird location is still available and deleting key/value
        // from hashmap afterwards

        System.out.println("Where is the Monk parakeet from again?");
        System.out.println("");

        System.out.println(birdCensus.retrieve("monk parakeet")); // Brooklyn College
        System.out.println("");

        System.out.println("Deleting Monk Parakeet from the census...");
        System.out.println("The Monk Parakeet has been removed. Is it true?");
        System.out.println(birdCensus.delete("monk parakeet")); // true

        System.out.println("");
        System.out.println("Is there a Monk Parakeet in this map still?");
        System.out.println("No, the Monk Parakeet space is " + birdCensus.retrieve("monk parakeet") + ""); // null

    }

}
