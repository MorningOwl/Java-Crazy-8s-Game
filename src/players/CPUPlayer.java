package players;

import java.util.LinkedList;

import cards.Card;
import cards.Deck;
import cards.Rank;
import cards.Suit;

public class CPUPlayer extends Player {

	private int heartsCount, diamondsCount, spadesCount, cloversCount;

	/**
	 * Constructor for CPUPlayer class.
	 * 
	 * @param name
	 *            Name for the player
	 */
	public CPUPlayer(String name) {
		this.name = name;
		heartsCount = 0;
		diamondsCount = 0;
		spadesCount = 0;
		cloversCount = 0;
		hand = new LinkedList<Card>();
	}

	/**
	 * Draws a card from the given deck and added into hand.
	 * 
	 * @param deck
	 *            Deck object to draw from.
	 */
	@Override
	public void draw(Deck deck) {
		Card card = deck.draw();

		if (card != null) {
			switch (card.getSuit()) {
			case CLUBS:
				cloversCount++;
				break;
			case DIAMOND:
				diamondsCount++;
				break;
			case HEART:
				heartsCount++;
				break;
			case SPADE:
				spadesCount++;
				break;
			default:
				break;
			}
			hand.add(card);
		}
	}

	/**
	 * CPU decides what to play given the rank and suit .
	 * 
	 * @param card
	 *            The card that the CPUPlayer decides to play upon
	 * @return Returns a card to play or null if no cards can be played
	 */
	public Card decide(Rank rank, Suit suit) {
		int indexWithSameRank = -1;
		int indexWithSameSuit = -1;
		int indexWithRankEight = -1;
		Card currentCard = null;
		for (int i = 0; i < hand.size(); i++) {
			currentCard = hand.get(i);
			if (rank != Rank.EIGHT && currentCard.getRank() == rank) {
				indexWithSameRank = i;
			}
			if (currentCard.getRank() != Rank.EIGHT && currentCard.getSuit() == suit) {
				indexWithSameSuit = i;
			}
			if (currentCard.getRank() == Rank.EIGHT) {
				indexWithRankEight = i;
			}
			if (indexWithSameRank > 0 && indexWithSameSuit > 0 && indexWithRankEight > 0) {
				break;
			}
		}

		if (indexWithSameRank == -1 && indexWithSameSuit == -1) {
			if (indexWithRankEight > 0) {
				return playCard(indexWithRankEight);
			} else {
				return null;
			}
		} else if (indexWithSameRank >= 0 && indexWithSameSuit == -1) {
			return playCard(indexWithSameRank);
		} else if (indexWithSameRank == -1 && indexWithSameSuit >= 0) {
			return playCard(indexWithSameSuit);
		} else {
			// there is a rank and suit match so pick the card with the most suit in hand
			int numberOfSuitsGivenSameSuit = 0;
			int numberOfSuitsGivenSameRank = 0;
			switch (hand.get(indexWithSameSuit).getSuit()) {
			case CLUBS:
				numberOfSuitsGivenSameSuit = cloversCount;
				break;
			case DIAMOND:
				numberOfSuitsGivenSameSuit = diamondsCount;
				break;
			case HEART:
				numberOfSuitsGivenSameSuit = heartsCount;
				break;
			case SPADE:
				numberOfSuitsGivenSameSuit = spadesCount;
				break;
			default:
				break;
			}
			switch (hand.get(indexWithSameRank).getSuit()) {
			case CLUBS:
				numberOfSuitsGivenSameRank = cloversCount;
				break;
			case DIAMOND:
				numberOfSuitsGivenSameRank = diamondsCount;
				break;
			case HEART:
				numberOfSuitsGivenSameRank = heartsCount;
				break;
			case SPADE:
				numberOfSuitsGivenSameRank = spadesCount;
				break;
			default:
				break;
			}
			if (numberOfSuitsGivenSameSuit < numberOfSuitsGivenSameRank) {
				return playCard(numberOfSuitsGivenSameRank);
			} else {
				return playCard(numberOfSuitsGivenSameSuit);
			}
		}
	}

	/**
	 * Returns a card from the given index and removed from hand or null if index
	 * out of bounds
	 * 
	 * @param i
	 *            The index of the card.
	 * @return Card object from the hand.
	 */
	@Override
	public Card playCard(int i) {
		if (i >= hand.size() || i < 0) {
			return null;
		}
		Card card = hand.get(i);
		if (card != null) {
			switch (card.getSuit()) {
			case CLUBS:
				cloversCount--;
				break;
			case DIAMOND:
				diamondsCount--;
				break;
			case HEART:
				heartsCount--;
				break;
			case SPADE:
				spadesCount--;
				break;
			default:
				break;
			}
		}
		hand.remove(i);
		return card;
	}

	/**
	 * Decides which suit to play
	 * 
	 * @return Suit to play
	 */
	public Suit decideSuitToPlay() {
		if (cloversCount > diamondsCount && cloversCount > heartsCount && cloversCount > spadesCount) {
			return Suit.CLUBS;
		} else if (diamondsCount > cloversCount && diamondsCount > heartsCount && diamondsCount > spadesCount) {
			return Suit.DIAMOND;
		} else if (heartsCount > diamondsCount && heartsCount > cloversCount && heartsCount > spadesCount) {
			return Suit.HEART;
		} else {
			return Suit.SPADE;
		}
	}

	/**
	 * Clears the hand.
	 */
	@Override
	public void clearHand() {
		heartsCount = 0;
		diamondsCount = 0;
		spadesCount = 0;
		cloversCount = 0;
		hand.clear();
	}

	/**
	 * Returns String representation of HumanPlayer class
	 * 
	 * @return returns the name of the player with remaining cards
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(name + "\n");
		for (int i = 0; i < hand.size(); i++) {
			sb.append(i + " - " + hand.get(i) + "\n");
		}
		return sb.toString();
	}

}
