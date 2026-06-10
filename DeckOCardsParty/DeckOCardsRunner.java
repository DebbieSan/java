
// Testing ground

public class DeckOCardsRunner {

    public static void main(String[] args) {

<<<<<<< Updated upstream
=======
        // Start the party and build a fresh deck
>>>>>>> Stashed changes
        DeckOCardsParty deck = new DeckOCardsParty();

        deck.createDeck();

<<<<<<< Updated upstream
        // deck.printDeck(); // Should print the 52 cards in the deck in order

        System.out.println("Dealing the first card...");
        DeckOCardsParty.Node card1 = deck.dealCard();
        System.out.println("Dealt card: " + card1);// Should be Ace of hearts

        System.out.println("\nDealing the second card...");
        DeckOCardsParty.Node card2 = deck.dealCard();
        System.out.println("Dealt: " + card2); // Should be 2 of Hearts

        System.out.println("\nRemaining deck:");
        deck.printDeck(); // Ace and 2 should be gone, list should start at 3 of Hearts

        System.out.println("--- Deck Before Shuffle ---");
        deck.printDeck();

        deck.shuffleDeck();

        System.out.println("\n--- Deck After Shuffle ---");
        deck.printDeck(); // Deck is now Randomized

=======
        // Shuffle it up!
        System.out.println("Shuffling the deck...");
        deck.shuffleDeck();

        // Deal a 5-card Poker hand
        System.out.println("\n--- Dealing a 5-card hand to Player 1 ---");
        DeckOCardsParty.Node[] playerHand = deck.dealHand(5);

        // Print out the player's hand
        for (int i = 0; i < playerHand.length; i++) {
            if (playerHand[i] != null) {
                System.out.println("Card " + (i + 1) + ": " + playerHand[i]);
            }
        }

        // See how many cards are left in the deck
        System.out.println("\n--- Remaining cards in the deck ---");
        deck.printDeck(); // Should show 47 cards left!
>>>>>>> Stashed changes
    }

}
