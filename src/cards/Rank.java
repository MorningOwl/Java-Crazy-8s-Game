/** 
 * @author Edward Calderon 
 * @version 7.13.2015 
 * This is the enumeration class of Rank. Rank is the "value" of a Suit. Ranks are represented as a String and does not have any true value.
 * For example, TWO does not equal the number 2, but is equal to the String Two or "2". The true value of the rank can be set in the Card class.  
 * This allows more flexibility in defining the value of a Rank for games.
 */
package cards;
public enum Rank {
	
	ACE("ACE", "A"),
	TWO("TWO", "2"),
	THREE("THREE", "3"),
	FOUR("FOUR", "4"),
	FIVE("FIVE", "5"),
	SIX("SIX", "6"),
	SEVEN("SEVEN", "7"),
	EIGHT("EIGHT", "8"),
	NINE("NINE", "9"),
	TEN("TEN", "10"),
	JACK("JACK", "J"),
	QUEEN("QUEEN", "Q"),
	KING("KING", "K"),
	JOKER("JOKER", "JK");
	
	//Represents the name of the Rank.
	private final String name;
	//Represents the abbreviation of the Rank.
	private final String abbr; 
	
	/**
	 * This is a private constructor that is used to assign the name and abbr properties to the Rank.
	 * @param name Represents the name of the Rank.
	 * @param abbr Represents the abbreviation of the Rank.
	 */
	private Rank(String name, String abbr){
		this.name = name;
		this.abbr = abbr;
	}
	
	/**
	 * Getter method that returns the String representation of the Rank's abbreviation.
	 * @return String representation Rank abbreviation
	 */
	public String getAbbr(){
		return this.abbr;
	}
	
	/**
	 * Getter method that returns the String representation of the Rank's name.
	 * @return String representation Rank name
	 */
	public String toString() {
		return name;
	}
}
