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

	private static int count = 0;
	private static int move = -1;
	private static int win = 0;
	private static int end = 0;

	private static int row = -1;
	private static int col = -1;
		
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
	
	public synchronized void setName(){
		try{
			StringBuffer read = null;

			if(count == 0){
				socketOut.println("name");
				socketOut.flush();
				socketOut.println("Please enter the name of the \'X\' player: ");
				socketOut.flush();
				}
			else if(count == 1){
				socketOut.println("name");
				socketOut.flush();
				socketOut.println("Please enter the name of the \'O\' player: ");
				socketOut.flush();
				}

			read = new StringBuffer(socketIn.readLine());

			String name = read.toString();
			this.name = name;

			count++;
			socketOut.println("end");
		} catch (IOException e) {
                System.out.println("I/O error: " + e);
        }
	}
	
	public synchronized void firstMove() throws IOException{
		socketOut.println("first");
		socketOut.flush();
    	socketOut.println("Who will play first?");
		socketOut.flush();
		socketOut.println("Enter '0' for Player 1 (X) or '1' for Player 2 (O): ");
		socketOut.flush();

		StringBuffer read = new StringBuffer(socketIn.readLine());

		move = Integer.parseInt(read.toString());
  		socketOut.println("end");
		socketOut.flush();
	}
	
	public synchronized void move() throws IOException{
		if(mark == 'X' && move == 0){
			socketOut.println("game");
			board.display(client);
			socketOut.println("Please enter a row: ");
			socketOut.flush();
			row = Integer.parseInt(socketIn.readLine());
			socketOut.println("Please enter a col: ");
			socketOut.flush();
			col = Integer.parseInt(socketIn.readLine());

			if (board.getMark(row, col) == 'O' || board.getMark(row, col) == 'X'){
				socketOut.println("occupied");
				socketOut.flush();
				socketOut.println("The space is already occupied.");
				socketOut.flush();
				move();
				}
			else {
				socketOut.println("good");
				board.addMark(row, col, mark);
				board.display(client);
				socketOut.flush();
				move = 1;
			}
		}
		else if(mark == 'O' && move == 1){
			socketOut.println("game");
			board.display(client);
			socketOut.println("Please enter a row: ");
			socketOut.flush();
			row = Integer.parseInt(socketIn.readLine());
			socketOut.println("Please enter a col: ");
			socketOut.flush();
			col = Integer.parseInt(socketIn.readLine());

			if (board.getMark(row, col) == 'O' || board.getMark(row, col) == 'X'){
				socketOut.println("occupied");
				socketOut.flush();
				socketOut.println("The space is already occupied.");
				socketOut.flush();
				move();
				}
			else {
				socketOut.println("good");
				board.addMark(row, col, mark);
				board.display(client);
				socketOut.flush();
				move = 0;
			}
		}
	}

	public synchronized void winner(){
		if(end == 0){
			socketOut.println("winner");
			socketOut.flush();

			if(board.xWins() == 1 || board.oWins() == 1) {
				String result = "The winner of the game is: ";
				if(board.checkWinner(mark) == 1)			
					result += name;
				else
					result += opponent.name;

				socketOut.println(result);
				socketOut.flush();
			}
			else {
				socketOut.println("The game was a tie.");
				socketOut.flush();
			}
		end++;
		}
		else if(end == 1){
			socketOut.println("winner");
			socketOut.flush();

			if(board.xWins() == 1 || board.oWins() == 1) {
				String result = "The winner of the game is: ";
				if(board.checkWinner(mark) == 1)			
					result += name;
				else
					result += opponent.name;

				socketOut.println(result);
				socketOut.flush();
			}
			else {
				socketOut.println("The game was a tie.");
				socketOut.flush();
			}
		end++;
		}
	}

	public synchronized void finalBoard(){
		if(win == 0) {
			socketOut.println("update");
			socketOut.flush();

			board.display(client);
			win++;
		}
		else if(win == 1) {
			socketOut.println("update");
			socketOut.flush();

			board.display(client);
			win++;
		}
	}

	public void run(){
		try{
			//Player 1 gets to chose who goes first
			if(mark == 'X' && move == -1) 
				firstMove();

			while(board.xWins() != 1 && board.oWins() != 1 && board.isFull() !=true) {
				move();
			}

		} catch (IOException e) { 
				System.out.println("I/O error: " + e);
		}
	}
	
}