import java.io.*;
import java.text.*;

/**
 * ENSF 409 - Lab 3 - Winter 2016
 * @author  Harry Han, Yida Xu
 * @version 1.0
 * @since February 2, 2015
 */

abstract class Player implements Constants {

	abstract protected void play() throws IOException;
	abstract protected void makeMove() throws IOException;

	/**
	 * The name of the player
	 */ 
	protected String name;
	
	/**
	 * The board the game is to be played on
	 */
	protected Board board;
	
	/**
	 * The opponent of the player
	 */
	protected Player opponent;
	
	/**
	 * The mark the player has
	 */
	protected char mark;
		
	/**
	 * The default constructor of the class Player.
	 * - name is empty
	 * - mark is empty
	 * - board is a 3x3 empty board
	 */
	protected Player() {
	}
	
	/**
	 * Constructs the player class to have a name, mark and board
	 *
	 * @param name the name of the player
	 * @param mark the mark that the player will use
	 * @param board the board for the player to play on
	 */ 
	protected Player(String name, char mark, Board board){
		this.name = name;
		this.mark = mark;
		this.board = board;
	}
	
	/**
	 * Sets the opponent to be played against the player
	 *
	 * @param other the opponent
	 */
	protected void setOpponent(Player other) {
		opponent = other;
	}
	
	protected String name(){
		return name;
	}
	
	protected char mark(){
		return mark;
	}

	}