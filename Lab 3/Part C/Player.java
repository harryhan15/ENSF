import java.io.*;

/**
 * ENSF 409 - Lab 3 - Winter 2016
 * @author  Harry Han, Yida Xu
 * @version 1.0
 * @since February 2, 2015
 */

public class Player implements Constants {

	/**
	 * The name of the player
	 */ 
	private String name;
	
	/**
	 * The board the game is to be played on
	 */
	private Board board;
	
	/**
	 * The opponent of the player
	 */
	private Player opponent;
	
	/**
	 * The mark the player has
	 */
	private char mark;
		
	/**
	 * The default constructor of the class Player.
	 * - name is empty
	 * - mark is empty
	 * - board is a 3x3 empty board
	 */
	public Player() {
	}
	
	/**
	 * Constructs the player class to have a name, mark and board
	 *
	 * @param name the name of the player
	 * @param mark the mark that the player will use
	 * @param board the board for the player to play on
	 */ 
	public Player(String name, char mark, Board board){
		this.name = name;
		this.mark = mark;
		this.board = board;
	}
	
	/**
	 * Sets the opponent to be played against the player
	 *
	 * @param other the opponent
	 */
	public void setOpponent(Player other) {
		opponent = other;
	}
	
	public String name(){
		return name;
	}
	
	public char mark(){
		return mark;
	}
	
	/**
	 * The method where the game will be played. The method ensures the turns go back and forth
	 * between the opponent and the player. Also responsible for printing out the result of the 
	 * game. Calls method makeMove() to draw and print the moves made in the game.
	 *
	 * @throws IOException
	 */
	public void play() throws IOException {
		int move = -1;
		
		while(move != 0 || move != 1) {
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Who will play first?\n\nEnter '0' for Player 1 (X) or '1' for Player 2 (O).\n");
			String input = stdin.readLine();
			move = Integer.parseInt(input);
			}
		
		while(board.xWins() != 1 && board.oWins() != 1 && board.isFull() !=true) {
			if(move == 0){
				makeMove();
				move = 1;
			}
			else {
				opponent.makeMove();
				move = 0;
			}
		}
		
		if(board.xWins() == 1 || board.oWins() == 1) {
			System.out.print("\nThe winner of the game is: ");
			if(board.checkWinner(mark) == 1){
				System.out.println(name + "\n");
			}
			else
				System.out.println(opponent.name + "\n");
		}
		else
			System.out.println("\nThe game resulted in a tie.\n");
	}
	
	/**
	 * Responsible for making the player perform a move. Expects an input of a row and column
	 * and verifies that the space is empty.
	 *
	 * @throws IOException
	 */
	public void makeMove() throws IOException {
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("\nPlease enter a row: ");
		String input = stdin.readLine();
		int row = Integer.parseInt(input);
		
		System.out.print("Please enter a column: ");
		input = stdin.readLine();
		int col = Integer.parseInt(input);
		System.out.println();
		
		if (board.getMark(row, col) == 'O' || board.getMark(row, col) == 'X'){
			System.out.println("\nThe space is already occupied.\n");
			board.display();
			
			makeMove();
		}
		else {
			board.addMark(row, col, mark);
		}
		
		board.display();
		}
	}

	/**
	 * A class constructor that inherits Players private members for human players
	 */ 
	class HumanPlayer extends Player{
		public HumanPlayer(){
		}
	
		public HumanPlayer(String name, char mark, Board board) {
			super(name, mark, board);
		}
	}
	
	/**
	 * A class constructor that inherits HumanPlayers private members for random players
	 */ 
	class RandomPlayer extends HumanPlayer{
		public RandomPlayer(){
		}
	
		public RandomPlayer(String name, char mark, Board board) {
			super(name, mark, board);
		}
	}
	
	/**
	 * A class constructor that inherits RandomPlayers private members for blocking players
	 */ 
	class BlockingPlayer extends RandomPlayer{
		public BlockingPlayer(){
		}
	
		public BlockingPlayer(String name, char mark, Board board) {
			super(name, mark, board);
		}
	}
	
	/**
	 * A class constructor that inherits BlockingPlayers private members for smart players
	 */ 
	class SmartPlayer extends BlockingPlayer{
		public SmartPlayer(){
		}
	
		public SmartPlayer(String name, char mark, Board board) {
			super(name, mark, board);
		}
	}