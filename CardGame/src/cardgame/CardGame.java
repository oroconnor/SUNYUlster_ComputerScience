package cardgame;



import java.util.Scanner;

/**
 * The Card game itself
 * @author owenoconnor
 * @since 01-25-21
 * 
 */
public class CardGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Card firstCard = new Card("Two","Hearts");
		Scanner input = new Scanner (System.in);
		//System.out.println(firstCard);
		//generates the deck
		Deck deck = new Deck();
		//shuffles the deck
		deck.shuffle();
		// Deal a Hand of five Cards
		System.out.println("Dealer's Hand:");
		System.out.println(deck.getCard(0));
		System.out.println(deck.getCard(1));
		System.out.println(deck.getCard(2));
		System.out.println("Player's Hand:");
		System.out.println(deck.getCard(3));
		System.out.println(deck.getCard(4));
		System.out.println("Make a Bet!");
		String s = input.next();
		System.out.println("Dealer's Card:");
		System.out.println(deck.getCard(5));
		System.out.println("Make a Bet!");
		s = input.next();
		System.out.println("Dealer's Card:");
		System.out.println(deck.getCard(6));
		//System.out.println(deck);
	}

}