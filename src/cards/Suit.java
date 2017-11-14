package cards;
/** 
 * @author Edward Calderon 
 * @version 7.13.2015 
 * This is the enumeration class of Suit. This class represents the suit of a card. Suits are represented as a String.
 * It also includes a Joker suit. Suits are assigned a name and an abbreviation.
 */
public enum Suit {
	
	CLUBS("CLUBS", "C"),
	DIAMOND("DIAMOND", "D"), 
	HEART("HEART", "H"), 
	SPADE("SPADE", "S"), 
	JOKER("JOKER", "JK");
	
	//Represents the name of the Rank.
	private final String name;
	//Represents the abbreviation of the Rank.
	private final String abbr; 
	
	/**
	 * This is a private constructor that is used to assign the name and abbr
	 * properties to the Suit.
	 * @param name Represents the name of the Suit.
	 * @param abbr Represents the abbreviation of the Suit.
	 */
	private Suit(String name, String abbr){
		this.name = name;
		this.abbr = abbr;
	}
	
	/**
	 * Getter method that returns the String representation of the Suit's abbreviation.
	 * @return String representation Suit abbreviation
	 */
	public String getAbbr(){
		return this.abbr;
	}
	
	/**
	 * Getter method that returns the String representation of the Suit's name.
	 * @return String representation Suit name
	 */
	public String toString() {
		return name;
	}
}