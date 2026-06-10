public class DeckOCardsParty {

    Node head; // The starting point of the list, initially set to null

    class Node {

        String suit;

        String rank;

        Node next;

        Node previous;

        // Node constructor, initializes the node with data and sets links to null.

        Node(String suit, String rank) {
            this.suit = suit;

            this.rank = rank;

            this.next = null; // Initially, there is no next node linked

            this.previous = null; // Initially, there is not previous node linked
        }

    }

    public DeckOCardsParty() {
        this.head = null; // At the start , the list is empty so the head is null
    }

}
