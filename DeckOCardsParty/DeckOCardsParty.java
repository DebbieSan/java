public class DeckOCardsParty {

    Node head; // The starting point of the deck (the top card)

    public static class Node {

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

    /**
     * This method removes and returns the top card of the deck
     * 
     * @return
     */

    public Node dealCard() {
        // Check if there are any cards left to deal
        if (head == null) {
            System.out.println("The deck is empty. No cards to deal.");
            return null; // Deck is empty
        }

        // Remembers the card that has been dealt
        Node dealtCard = head;

        // Shifts the pointer so the next card becomes the new top of the deck
        head = head.next;
        // if there is still a card at the top, its link to the old head needs to be cut
        if (head != null) {
            // set the new head previous pointer to null
            head.previous = null;
        }
        // Cleaning the dealt card's pointer so it isn't still tethered to the deck
        dealtCard.next = null;

        // Return the dealt card
        return dealtCard;
    }

    // "Shuffles the deck" by swapping the data (suit and rank) of random cards.
    // This is a solution to using a doubly linked list since
    // we can't just access a random index like an array

    public void shuffleDeck() {
        if (head == null || head.next == null) {
            return; // Can't shuffle an empty deck or a deck with 1 card
        }
        // Count how many cards are left in the deck
        int totalCards = 0;
        Node temp = head;
        while (temp != null) {
            totalCards++;
            temp = temp.next;
        }

        // Loop through every card and swap it with a random card

        java.util.Random random = new java.util.Random();
        Node current = head;

        while (current != null) {
            // Pick a random position in the deck
            int randomIndex = random.nextInt(totalCards);

            // Travel to that random card Node

            Node randomCard = head;

            for (int i = 0; i < randomIndex; i++) {
                randomCard = randomCard.next;
            }

            // ----- Swapping the cards -----

            // Saving the current data
            String tempSuit = current.suit;
            String tempRank = current.rank;

            // Copying random card data to current data
            current.suit = randomCard.suit;
            current.rank = randomCard.rank;

            // Copy saved current data to random card
            randomCard.suit = tempSuit;
            randomCard.rank = tempRank;

            // Move to the next card in the deck to repeat the process
            current = current.next;

        }

    }

}
