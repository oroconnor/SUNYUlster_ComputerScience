package cardgame;



import java.security.SecureRandom;

/**
 * Defines Deck class for CardGame.java
 * @author owenoconnor
 * @since 01-25-21 
 */
public class Deck {
	private static final SecureRandom 
	   randomNumbers = new SecureRandom();
	private static final int NUMBER_OF_CARDS=52;
	private Card[] deck = 
			new Card[NUMBER_OF_CARDS];	
	private int currentCard = 0;

	
	/** Class constructor
	 */
	public Deck() {
		//Creates array of all possible card faces as strings
		String faces[] = {"Ace","2","3","4","5",
				"6","7","8","9","10","J","Q","K"};
		//Creates array of all possible card suits as strings
		String suits[] = {"Hearts","Dimonds",
				"Clubs","Spades"};
		/*loops through the deck of size NUMBER_OF_CARDS and populates the deck with cards of
		 * standard faces and suits
		 */
		for (int count = 0; count <deck.length;count++)
			deck[count]= new Card(faces[count % 13 ],
								  suits[count / 13]);
	}
	
	/** Gets the card of a certain index
	 * 
	 * @param index - index of card
	 * @return face and suit of card
	 */
	public Card getCard(int index) {
		return deck[index];
	}
	
	/** Provides a format for when the Deck is called as a string
	 * @return string representation of deck
	 */
	public String toString() {
		String str = "";
		for (int count =0; count < deck.length; count++)
			str = str + deck[count].toString() + " ";
		return str;
	}
	
	/** Shuffles the "deck" 
	 * 
	 */
	public void shuffle() {
		currentCard = 0;
		
		for (int first=0; first < deck.length; first++) {
			// pick a random card
			int second = randomNumbers.nextInt(NUMBER_OF_CARDS);
			// swap current card with the random card
			Card temp = deck[first];  // temp copy
			deck[first] = deck[second];
			deck[second] = temp;
		}
		
	}

}

