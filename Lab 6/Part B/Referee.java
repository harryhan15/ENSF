import java.io.*;

/**
 * ENSF 409 - Lab 3 - Winter 2016
 * @author  Harry Han, Yida Xu
 * @version 1.0
 * @since February 2, 2016
 */

public class Referee {
	
	/**
	 * The player with the mark 'X'
	 */
	private Player xPlayer;
	
	/**
	 * The player with the mark 'O'
	 */
	private Player oPlayer;
	
	/**
	 * The board the game is to be played on
	 */
	private Board board;
	
	/**
	 * The default constructor for the class referee
	 */
	public Referee(){
	}
	
	/**
	 * Constructs the referee class with the players and board specified.
	 *
	 * - xPlayer is the player with the mark 'X'
	 * - oPlayer is the player with the mark 'O'
	 * - board is the board that the game is to be played on
	 */
	public Referee(Board board, Player xPlayer, Player oPlayer){
		this.xPlayer = xPlayer;
		this.oPlayer = oPlayer;
		this.board = board;
	}
	
	/**
	 * Responsible for running the game. Sets the opponent of the player
	 *
	 * @throws IOException
	 */
	public void runTheGame() throws IOException {
		xPlayer.setOpponent(oPlayer);
	}
}
