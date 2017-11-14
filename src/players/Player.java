package players;

import java.util.List;
import cards.Card;
import cards.Deck;
import cards.Rank;
import cards.Suit;

/**
 * @author Edward Calderon 
 * @version 9.11.2017
 * This is an abstract class for Player that provides a template for all player classes.
 */
public abstract class Player {

	protected List<Card> hand;
	protected String name;
	
	/**
	 * Returns the name of the player.
	 * @return String value of name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Draws a card from the given deck and added into hand.
	 * @param deck Deck object to draw from.
	 */
	public abstract void draw(Deck deck);
	
	/**
	 * Returns a card from the given index and removed from hand
	 * or null if index out of bounds
	 * @param i The index of the card.
	 * @return Card object from the hand.
	 */
	public Card playCard(int i) {
		if(i >= hand.size() || i < 0) {
			return null;
		}
		Card card = hand.get(i);
		hand.remove(i);
		return card;
	}
	
	/**
	 * Adds a card object in the hand.
	 * @param card The Card object to add to hand
	 */
	public void addCardToHand(Card card) {
		hand.add(card);
	}
	/**
	 * Getter method for hand.
	 * @return Returns all the cards in hand
	 */
	public List<Card> getHand() {
		return hand;
	}
	
	/**
	 * Returns if a true or false if the player can play a card given the rank or suit.
	 * @param rank The rank given to play.
	 * @param suit The suit given to play.
	 * @return boolean value if the player can play a card.
	 */
	public boolean canPlay(Rank rank, Suit suit) {
		for(Card c: hand) {
			if(c.getRank() == Rank.EIGHT || c.getSuit() == suit || c.getRank() == rank) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Clears the hand.
	 */
	public abstract void clearHand();
	
	/**
	 * Returns representation of this player.
	 */
	public abstract String toString();
}
