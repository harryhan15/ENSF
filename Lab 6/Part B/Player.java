import java.io.*;
import java.text.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

/**
 * ENSF 409 - Lab 3 - Winter 2016
 * @author  Harry Han, Yida Xu
 * @version 1.0
 * @since February 2, 2015
 */

public class Player implements Constants, Runnable {

	private PrintWriter socketOut;
	private Socket clientSocket; 
	private ServerSocket serverSocket; 
	private BufferedReader socketIn;

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
	
	private Socket client;

	private Lock gameLock;
		
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
	public Player(String name, char mark, Board board, Socket client){
		this.name = name;
		this.mark = mark;
		this.board = board;
		this.client = client;
		gameLock = new ReentrantLock();

		try{
			socketOut = new PrintWriter(client.getOutputStream(), true);
    		socketIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
		} catch (IOException e) {
                System.out.println("I/O error: " + e);
        }
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
	
	private int check = -1;
	private static int count = 0;
	
	public synchronized void setName(){
		try{
			StringBuffer read = null;

			if(count == 0){
				socketOut.println("\nPlease enter the name of the \'X\' player: ");
				}
			else if(count == 1){
				socketOut.println("\nPlease enter the name of the \'O\' player: ");
				}

			read = new StringBuffer(socketIn.readLine());
			socketOut.flush();

			String name = read.toString();
			this.name = name;

			count++;
		} catch (IOException e) {
                System.out.println("I/O error: " + e);
        }
	}
	
	private static int move = 1;
	private static int xStatus = -1;
	private static int oStatus = -1;
	
	public synchronized void firstMove() throws IOException{
    	socketOut.println("Who will play first? Enter '0' for Player 1 (X) or '1' for Player 2 (O): ");
		socketOut.flush();

		StringBuffer read = new StringBuffer(socketIn.readLine());

		int move = Integer.parseInt(read.toString());
  
	}

	public int c = 0;
	
	public synchronized void move() throws IOException{
		if(mark == 'X'){
			move = 0;
			}
		else if(mark == 'O'){
			move = 1;
			}
		c++;
	}


	public void run(){
		
		try{
			setName();

			//Player 1 gets to chose who goes first
			/*try {
				if(mark == 'X' && xMove == -1 && oMove == -1) 
					firstMove();
			} finally {
				gameLock.unlock();
			}*/

			while(c < 8){
				gameLock.lock();
				try {
					move();
					socketOut.println("Move: " + move);
					socketOut.flush();
				} finally {
					gameLock.unlock();
				}

			}

		} catch (IOException e) { 
				System.out.println("I/O error: " + e);
		}
	}
	
	/**
	 * The method where the game will be played. The method ensures the turns go back and forth
	 * between the opponent and the player. Also responsible for printing out the result of the 
	 * game. Calls method makeMove() to draw and print the moves made in the game.
	 *
	 * @throws IOException
	 */
	public void play() throws IOException {
		Integer move = -1;

			//move = parseInt(input);
			//if (move == 1 || move == 0) {
			//	break;
			//	}

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

		int col, row;
		
		while(true) {
			System.out.print("\nPlease enter a row: ");
			String input = stdin.readLine().trim();

			while (input.isEmpty() || parseInt(input) == null) {
				System.out.println("\nPlease try again.");
				System.out.print("\nPlease enter a row: ");
				input = stdin.readLine();
			}

			row = Integer.parseInt(input);

			if (row >=0 && row <=2) {
				break;
			}
		}
		
		while(true) {
			System.out.print("\nPlease enter a column: ");
			String input = stdin.readLine().trim();

			while (input.isEmpty() || parseInt(input) == null) {
				System.out.println("\nPlease try again.");
				System.out.print("\nPlease enter a column: ");
				input = stdin.readLine();
			}

			col = Integer.parseInt(input);

			if (col >=0 && col <=2) {
				break;
			}
		}

		System.out.println();
		
		if (board.getMark(row, col) == 'O' || board.getMark(row, col) == 'X'){
			System.out.println("\nThe space is already occupied.\n");
			board.display();
			
			makeMove();
		}
		else {
			board.addMark(row, col, mark);
			board.display();
		}
	}

	/**
	 * Responsible for parsing the input and ensuring the input is an integer
	 */
	public Integer parseInt(String data) {
		Integer val = null;
		try {
			val = Integer.parseInt(data);
			} catch (NumberFormatException nfe) {}

		return val;
		}
	}