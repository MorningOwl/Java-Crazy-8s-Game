package cards;
/** 
 * @author Edward Calderon 
 * @version 7.13.2015 
 * This is the class of Card and implements Comparable. This class contains a Suit and a Rank.
 * It also has a value that can be used for sorting, calculations, or other things. The comparable interface
 * compares cards by values.
 * @version 9.11.2015 
 * Removed Comparable interface. Card will no longer be handling comparison. Added new constructor public Card(Suit suit, Rank rank)
 */

public class Card{

	//The suit of the card represented by a Suit enum.
	private Suit suit;
	//The rank of the card represented by a Rank enum.
	private Rank rank;
	//The positive integer value of the card.
	private int value;
	
	/**
	 * The constructor of the card. 
	 * @param suit The suit of the card represented by a Suit enum.
	 * @param rank The rank of the card represented by a Rank enum.
	 * @param value The integer value of the card.
	 * @throws NullPointerException Throws if suit is null.
	 * @throws NullPointerException Throws if rank is null.
	 * @throws IllegalArgumentException Throws if value < 0.
	 */
	public Card(Suit suit, Rank rank, int value){
		if(suit == null){
			throw new NullPointerException ("Suit cannot be Null.");	
		}
		if(rank == null){
			throw new NullPointerException ("Rank cannot be Null.");
		}
		if(value < 0){
			throw new IllegalArgumentException ("Value cannot be less than 0.");
		}
		
		this.suit = suit;
		this.rank = rank;
		this.value = value;
	}
	
	/**
	 * The constructor of the card. 
	 * @param suit The suit of the card represented by a Suit enum.
	 * @param rank The rank of the card represented by a Rank enum.
	 * @param value The integer value of the card.
	 * @throws NullPointerException Throws if suit is null.
	 * @throws NullPointerException Throws if rank is null.
	 */
	public Card(Suit suit, Rank rank){
		if(suit == null){
			throw new NullPointerException ("Suit cannot be Null.");	
		}
		if(rank == null){
			throw new NullPointerException ("Rank cannot be Null.");
		}		
		this.suit = suit;
		this.rank = rank;
		this.value = 0;
	}
	
	/**
	 * Getter method that returns the suit of this card.
	 * @return The Suit of the card.
	 */
	public Suit getSuit(){
		return this.suit;
	}
	
	/**
	 * Getter method that returns the rank of this card.
	 * @return The Rank of the card.
	 */
	public Rank getRank(){
		return this.rank;
	}
	
	/**
	 * Getter method that returns the value of this card.
	 * @return A integer value of the card.
	 */
	public int getValue(){
		return this.value;
	}
	
	/**
	 * Precondition: Value in param must be greater than 0.
	 * Setter method that sets a new value of this card. 
	 * Postcondition: New value is set.
	 * @throws IllegalArgumentException Throws if value < 0.
	 * @param value The new value for this card.
	 */
	public void setValue(int value){
		if(value < 0){
			throw new IllegalArgumentException ("Value cannot be less than 0.");
		}
		
		this.value = value;
	}

	/**
	 * Getter method that returns the abbreviated representation of this card. This 
	 * combines the suit's abbreviation and the rank's abbreviation in that order. For example, the Ace of Hearts will
	 * be represented as "[AH]". 
	 * @return A String representation of the card's abbreviation. 
	 */
	public String getAbbr(){
		return "[" + getRank().getAbbr() + getSuit().getAbbr() + "]";
	}
	
	/**
	 * Getter method that returns the full name representation of this card. This
	 * combines the suit's name and the rank's name in that order. For example, the Ace of Hearts will
	 * be represented as "[ACE of HEARTS]".  		
	 */
	public String toString(){
		return "[" + getRank() + " of " + getSuit() + "]";
	}
}
