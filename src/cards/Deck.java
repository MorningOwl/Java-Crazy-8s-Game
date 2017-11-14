package cards;

import java.util.Collections;
import java.util.Stack;

/** 
 * @author Edward Calderon 
 * @version 11.9.2017
 * This is the Deck class the represents a deck of cards. It contains typical functionalities of
 * a deck such as shuffling, drawing, or insertions.
 */
public class Deck {
	private Stack<Card> deck;
	private final Suit[] suits = {Suit.CLUBS, Suit.DIAMOND, Suit.HEART,
			Suit.SPADE};
	private final Rank[] ranks = {Rank.ACE, Rank.TWO, Rank.THREE, Rank.FOUR,
			Rank.FIVE, Rank.SIX, Rank.SEVEN, Rank.EIGHT, Rank.NINE, Rank.TEN,
			Rank.JACK, Rank.QUEEN, Rank.KING };

	/**
	 * Constructor for deck object.
	 */
	public Deck() {
		deck = new Stack<Card>();
	}
	
	/**
	 * Creates a new deck with cards. Parameter can be set to add joker.
	 * @param leaveJoker Boolean value that dictates if jokers should be included if true.
	 */
	public void createNewDeck(boolean includeJoker) {
		deck.clear();
		int suitCount = 0;
		int rankCount = 0;
		int size = suits.length * ranks.length;
		for (int i = 0; i < size; i++) {
			if (suitCount >= suits.length) {
				rankCount = 0;
			}
			if (rankCount >= ranks.length) {
				rankCount = 0;
				suitCount++;
			}
			rankCount++;
			deck.push(new Card(suits[suitCount], ranks[rankCount-1], rankCount + 1));
		}
		if(includeJoker) {
			deck.push(new Card(Suit.JOKER, Rank.JOKER, 0));
			deck.push(new Card(Suit.JOKER, Rank.JOKER, 0));
		}		
	}
	
	/**
	 * Shuffles the deck.
	 */
	public void shuffle(){
		Collections.shuffle(deck);
	}
	
	/**
	 * Returns the number of cards remaining in deck
	 * @return int value of deck size
	 */
	public int getCount(){
		return deck.size();
	}
	
	/**
	 * Draws a card from the top of the deck.
	 * @return Card object from the deck or null if empty
	 */
	public Card draw(){
		if(deck.isEmpty()) {
			return null;
		}
		return deck.pop();
	}
	
	/**
	 * Checks if the deck is empty.
	 * @return True if deck empty, false if not
	 */
	public boolean isEmpty() {
		return deck.isEmpty();
	}
	
	/**
	 * Returns a String representation of this deck
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Card c : deck) {
			sb.append(c);
		}
		return sb.toString();
	}
}
