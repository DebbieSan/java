
// Testing ground

public class DeckOCardsRunner {

    public static void main(String[] args) {

        DeckOCardsParty deck = new DeckOCardsParty();

        deck.createDeck();

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

    }

}
