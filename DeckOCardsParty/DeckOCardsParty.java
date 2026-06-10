public class DeckOCardsParty {

    Node head; // The starting point of the deck (the top card)

    class Node {

        String suit;

        String rank;

        Node next;

        Node previous;

        // Node constructor, initializes the node with data, which in the case of a deck
        // of cards are the suit and rank,
        // and sets links to null.

        Node(String suit, String rank) {
            this.suit = suit;

            this.rank = rank;

            this.next = null; // Initially, there is no next node linked

            this.previous = null; // Initially, there is not previous node linked
        }

        // Helper method to easily print the card shuffler and dealer
        public String toString() {
            return rank + " of " + suit;

        }

    }

    public void createDeck() {
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
        String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };

        Node currentTail = null;

        for (String suit : suits) {
            for (String rank : ranks) {
                Node newCard = new Node(suit, rank);
                // Add the card to the deck

                if (head == null) {
                    head = newCard;
                    currentTail = newCard;
                } else {
                    currentTail.next = newCard;
                    newCard.previous = currentTail;
                    currentTail = newCard;
                }
            }

        }
    }

    public DeckOCardsParty() {
        this.head = null; // The deck starts completely empty
    }

    /**
     * A helper method to print an entire deck from top to bottom
     * to test if the createDeck() method works correctly
     */
    public void printDeck() {
        Node current = head;
        int count = 1;
        while (current != null) {
            System.out.println(count + ": " + current); // This will call the toString() method
            current = current.next;
            count++;
        }

    }

}
