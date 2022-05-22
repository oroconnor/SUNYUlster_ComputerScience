
package cardgame;

/**
 * Defines Card class for CardGame.java
 * @author owenoconnor
 * @since 01-25-21
 * 
 * 
 */
public class Card {
	//variables for the two qualities of the Card: face and suit 
	private final String face;
	private final String suit;
	
/**
 * 	Class constructor
 * @param cardFace identifies the face title for the Card, for example "2" or "Jack"
 * @param cardSuit identities the suit for the Card, for example "spades" or "diamonds"
 */
	public Card(String cardFace,
			    String cardSuit) {
		this.face = cardFace;
		this.suit = cardSuit;
	}

/** Provides a format for when the Card is called as a string
 * @return string represenation of card
 */
	public String toString() {
		return face + " of " + suit;
	}

}

