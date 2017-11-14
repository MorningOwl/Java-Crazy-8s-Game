package players;

import java.util.LinkedList;

import cards.Card;
import cards.Deck;

public class HumanPlayer extends Player {
	
	
	/**
	 * Constructor for HumanPlayer class.
	 * @param name Name for the player
	 */
	public HumanPlayer(String name) {
		this.name = name;
		hand = new LinkedList<Card>();
	}
	
	/**
	 * Draws a card from the given deck and added into hand.
	 * @param deck Deck object to draw from.
	 */
	@Override
	public void draw(Deck deck) {
		Card card = deck.draw();
		if(card != null){
			hand.add(card);
		}
	}
	
	/**
	 * Clears the hand.
	 */
	@Override
	public void clearHand() {
		hand.clear();
	}
	
	/**
	 * Returns String representation of HumanPlayer class
	 * @return returns the name of the player with all the cards in their hand
	 * 		   and their indexes
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(name);
		for(int i = 0; i < hand.size(); i++) {
			sb.append("\n" + i + " - " + hand.get(i));
		}
		return sb.toString();
	}
}
