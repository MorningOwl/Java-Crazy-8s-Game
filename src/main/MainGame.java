package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import cards.*;
import players.*;

public class MainGame {

	private static int passes;
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Welcome to a game of Crazy 8's.");
		System.out.println("\nThe game is simple. Each player will start with 5 cards. Starting from you, each player "
				+ "\nmust place one card face up on the starter pile. Each card played (other than an eight) must "
				+ "\nmatch the card showing on the starter pile, either in suit or in rank (denomination).  If the Q of "
				+ "\nClubs is the starter, any club may be played on it or any Queen. If unable to play, cards are drawn "
				+ "\nfrom the top of the deck until a play is possible, or until the stock is exhausted. If unable to play when "
				+ "\nthe deck is exhausted, the player must pass. A player may draw from the deck, even though there may be a "
				+ "\nplayable card in the player’s hand. All eights are wild! That is, an eight may be played at any time in turn, "
				+ "\nand the player need only specify a suit for it (but never a number). The next player must play either "
				+ "\na card of the specified suit or an eight. \n\n"
				+ "The player who is the first to have no cards left wins the game. The winning player collects from each "
				+ "\nother player the value of the cards remaining in that player’s hand as follows:\r\n"
				+ "+ Each eight = 50 points\r\n" + "+ Each K, Q, J or 10 = 10 points\r\n" + "+ Each ace = 1 point\r\n"
				+ "+ Each other card is the rank value"
				+ "\nIf no players won the round - no playable cards and the deck is empty - no points are rewarded. The"
				+ "\nfirst person to reach 200 points wins the game.");
		System.out.print("\nTo begin, please enter your name: ");
		String name = scan.nextLine();
		System.out.println("Hello, " + name + "!");
		Game game = new Game(name, 200);
		Player[] cpuPlayers = game.getCPUPlayers();

		game.startNewGame();
		passes = 0;
		System.out.println("New game started.");
		do {
			game.startNewRound();
			System.out.println("New round started.");
			do {
				promptHumanPlayer(game);
				if (game.isRoundDone()) {
					System.out.println(game.getWinner().getName() + " wins the round!");
					break;
				}
				for (int i = 0; i < cpuPlayers.length; i++) {
					promptCPUPlayer(game, (CPUPlayer) cpuPlayers[i]);
					if (game.isRoundDone()) {
						System.out.println(game.getWinner().getName() + " wins the round!");
						break;
					}
				}

				if (!game.canPlayersStillPlay() && game.getDeck().isEmpty()) {
					System.out.println("The deck is empty and players can no longer play a card.");
					break;
				}

			} while (true);
			printLeaderBoard(game);
		} while (!game.isGameDone());
	}

	/**
	 * Prompts the human player for input
	 */
	private static void promptHumanPlayer(Game game) {
		boolean invalidInput = false;
		int input = -999;
		Card card = null;
		HumanPlayer human = game.getHumanPlayer();
		do {
			System.out.println("Top card on play: " + game.getTopCard());
			System.out.println(human);
			System.out.print("Your turn. Enter card to play or press -1 to draw or -2 to pass: ");
			
			do {
				while (!scan.hasNextInt()) {
					System.out.print("Your turn. Enter card to play or press -1 to draw or -2 to pass: ");
					scan.nextLine();
				}
				input = scan.nextInt();
				scan.nextLine();
				
				invalidInput = input < -2 || input >= human.getHand().size();
				if(invalidInput){
					System.out.print("Your turn. Enter card to play or press -1 to draw or -2 to pass: ");
				}
				
			}while(invalidInput);
			

			if (input == -1) {
				human.draw(game.getDeck());
				continue;
			} else if (input == -2) {
				if (game.getDeck().isEmpty()) {
					System.out.println("Passed.");
					break;
				} else {
					System.out.println("You cannot pass, deck is not empty.");
					continue;
				}
			}

			card = human.getHand().get(input);

			if (card.getRank() == Rank.EIGHT) {
				String suitChoice = "";
				do {
					System.out.println("Please choose a suit: " + "\nC - Clubs" + "\nD - Diamonds" + "\nH - Hearts"
							+ "\nS - Spades");
					suitChoice = scan.next().trim();
					suitChoice = suitChoice.toUpperCase();
				} while (!suitChoice.equals("C") && !suitChoice.equals("D") && !suitChoice.equals("H")
						&& !suitChoice.equals("S"));

				switch (suitChoice) {
				case "C":
					game.setTopCard(human.playCard(input));
					game.setTopSuit(Suit.CLUBS);
					break;
				case "D":
					game.setTopCard(human.playCard(input));
					game.setTopSuit(Suit.DIAMOND);
					break;
				case "H":
					game.setTopCard(human.playCard(input));
					game.setTopSuit(Suit.HEART);
					break;
				default:
					game.setTopCard(human.playCard(input));
					game.setTopSuit(Suit.SPADE);
					break;
				}
				System.out.println("\nSuit has been changed to " + game.getTopSuit() + ".");
			} else if (card.getRank() == game.getTopCard().getRank() || card.getSuit() == game.getTopSuit()) {
				game.setTopCard(human.playCard(input));
			} else {
				card = null;
			}
		} while (card == null);

		if (human.getHand().isEmpty()) {
			if (game.updatePointsAndCheckWinner(human)) {
				game.setGameDone(true);
			} else {
				game.setRoundDone(true);
			}
		}
	}

	/**
	 * Prompts the CPU players for input
	 */
	private static void promptCPUPlayer(Game game, CPUPlayer player) {
		Deck deck = game.getDeck();
		System.out.println(player.getName() + "'s turn.");

		Card cardPlayed = player.decide(game.getTopRank(), game.getTopSuit());
		while (cardPlayed == null && !deck.isEmpty()) {
			System.out.println(player.getName() + " draws a card.");
			player.draw(deck);
			cardPlayed = player.decide(game.getTopRank(), game.getTopSuit());
		}

		if (cardPlayed != null) {
			System.out.println(player.getName() + " plays " + cardPlayed + ".");
			game.setTopCard(cardPlayed);
			System.out.println("Top card on play: " + game.getTopCard());
			if (player.getHand().isEmpty()) {
				if (game.updatePointsAndCheckWinner(player)) {
					game.setGameDone(true);
				} else {
					game.setRoundDone(true);
				}
				return;
			} else if (cardPlayed.getRank() == Rank.EIGHT) {
				game.setTopSuit(player.decideSuitToPlay());
				System.out.println("\nSuit has been changed to " + game.getTopSuit() + ".");
			}
		} else {
			System.out.println(player.getName() + " passes.");
		}
	}

	/**
	 * Prints the leader board
	 */
	private static void printLeaderBoard(Game game) {
		StringBuffer sb = new StringBuffer("=============================\nScore \n=============================");
		Map<Player, Integer> lb = game.getLeaderboard();

		for (Player p : lb.keySet()) {
			sb.append("\n" + p.getName() + ": " + lb.get(p) + " points");
		}
		sb.append("\n=============================");
		System.out.println(sb.toString());
	}

}

class Game {
	// The deck to be used in the game.
	private Deck deck;
	private Card topCard;
	private Rank topRank;
	private Suit topSuit;

	// The players
	private HumanPlayer human;
	private CPUPlayer cpu1;
	private CPUPlayer cpu2;
	private CPUPlayer cpu3;
	private CPUPlayer[] cpuPlayers;
	private final int INIT_HAND_DRAW = 5;

	// Game status
	private boolean isRoundDone;
	private boolean isGameDone;
	private int maxPoints;
	private Player winner;
	private Map<Player, Integer> leaderboard;

	public Game(String name, int maxPoints) {
		deck = new Deck();
		human = new HumanPlayer(name);
		cpu1 = new CPUPlayer("CPU 1");
		cpu2 = new CPUPlayer("CPU 2");
		cpu3 = new CPUPlayer("CPU 3");
		cpuPlayers = new CPUPlayer[3];
		cpuPlayers[0] = cpu1;
		cpuPlayers[1] = cpu2;
		cpuPlayers[2] = cpu3;
		isRoundDone = false;
		isGameDone = false;
		this.maxPoints = maxPoints;
	}

	/**
	 * Starts a new game of Crazy 8's.
	 */
	public void startNewGame() {
		winner = null;
		leaderboard = new HashMap<Player, Integer>();
		leaderboard.put(human, 0);
		leaderboard.put(cpu1, 0);
		leaderboard.put(cpu2, 0);
		leaderboard.put(cpu3, 0);
		isGameDone = false;
	}

	public void startNewRound() {
		Card card;
		winner = null;
		isRoundDone = false;
		deck.createNewDeck(false);
		deck.shuffle();

		for (Player p : leaderboard.keySet()) {
			p.clearHand();
		}
		for (int i = 0; i < INIT_HAND_DRAW; i++) {
			human.draw(deck);
			cpu1.draw(deck);
			cpu2.draw(deck);
			cpu3.draw(deck);
		}
		do {
			card = deck.draw();
		} while (card.getRank() == Rank.EIGHT);
		setTopCard(card);
	}

	/**
	 * Sets the top card.
	 * 
	 * @param card
	 *            Card to be set
	 */
	public void setTopCard(Card card) {
		topCard = card;
		topRank = card.getRank();
		topSuit = card.getSuit();
	}

	/**
	 * getter method for topCard
	 * 
	 * @return Card object on play
	 */
	public Card getTopCard() {
		return topCard;
	}

	/**
	 * Setter method for top suit
	 * 
	 * @param suit
	 *            The suit to change the top suit (for 8 plays)
	 */
	public void setTopSuit(Suit suit) {
		topSuit = suit;
	}

	/**
	 * Getter method for top suit
	 * 
	 * @return The Suit of top card
	 */
	public Suit getTopSuit() {
		return topSuit;
	}

	/**
	 * Getter method for top rank
	 * 
	 * @return The Rank of top card
	 */
	public Rank getTopRank() {
		return topRank;
	}

	/**
	 * Getter method for the game's deck
	 * 
	 * @return Deck object
	 */
	public Deck getDeck() {
		return deck;
	}

	/**
	 * Returns the human player.
	 * 
	 * @return
	 */
	public HumanPlayer getHumanPlayer() {
		return human;
	}

	/**
	 * Returns the CPUPlayer from the cpuplayers list
	 * 
	 * @param i
	 * @return
	 */
	public CPUPlayer getCPUPlayer(int i) {
		return cpuPlayers[i];
	}

	/**
	 * Returns an array of cpu players
	 * 
	 * @return CPUPlayer[] array
	 */
	public CPUPlayer[] getCPUPlayers() {
		return cpuPlayers;
	}

	/**
	 * Returns a boolean that checks if a round has been finished.
	 * 
	 * @return True if round is done, false otherwise
	 */
	public boolean isRoundDone() {
		return isRoundDone;
	}

	/**
	 * Returns a boolean that checks if game is finished.
	 * 
	 * @return True if round is done, false otherwise
	 */
	public boolean isGameDone() {
		return isGameDone;
	}

	/**
	 * Sets the game status
	 * 
	 * @param isGameDone
	 */
	public void setGameDone(boolean isGameDone) {
		this.isGameDone = isGameDone;
	}

	/**
	 * Sets the round status
	 * 
	 * @param isGameDone
	 */
	public void setRoundDone(boolean isRoundDone) {
		this.isRoundDone = isRoundDone;
	}

	/**
	 * Updates the round's winner points in the leaderboards. It returns true if the
	 * player won the game or false otherwise. Sets the winner valuable when true.
	 * 
	 * @param player
	 *            The player that won the round
	 * @return boolean value if the player won the game
	 */
	public boolean updatePointsAndCheckWinner(Player player) {
		winner = player;
		int totalPoints = leaderboard.get(player);
		Rank rank = null;
		for (Player p : leaderboard.keySet()) {
			if (p != player) {
				for (Card c : p.getHand()) {
					rank = c.getRank();
					if (rank == Rank.JACK || rank == Rank.QUEEN || rank == Rank.KING) {
						totalPoints += 10;
					} else if (rank == Rank.EIGHT) {
						totalPoints += 50;
					} else {
						totalPoints += c.getValue();
					}
				}
			}
		}
		leaderboard.put(player, totalPoints);

		if (totalPoints >= maxPoints) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the winner of the round/game.
	 * 
	 * @return Returns Player object that wins the round/game or null if no winners
	 */
	public Player getWinner() {
		return winner;
	}

	/**
	 * Returns the leader board
	 * 
	 * @return Returns a Map<Player, Integer> object that represents the leader
	 *         board
	 */
	public Map<Player, Integer> getLeaderboard() {
		return leaderboard;
	}

	/**
	 * Checks if all players can still play a card.
	 * 
	 * @return True if a player can still play, false if no one else can play
	 */
	public boolean canPlayersStillPlay() {
		if (human.canPlay(topRank, topSuit)) {
			return true;
		}

		for (int i = 0; i < cpuPlayers.length; i++) {
			if (cpuPlayers[i].canPlay(topRank, topSuit)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * toString representation of this class
	 * 
	 * @return returns a string that contains the card on the top and the suit if it
	 *         was 8
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer("Top card: " + topCard);
		if (topRank == Rank.EIGHT) {
			sb.append("\nSuit has been changed to " + topSuit + ".");
		}
		return sb.toString();
	}

}
