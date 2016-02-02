/**
 * ENSF 409 - Lab 3 - Winter 2011
 * @author  Harry Han, Yida Xu
 * January 22, 2015
 */

import java.io.*;

public class Player implements Constants {

	private String name;
	private Board board;
	private Player opponent;
	private char mark;
		
	public Player() {
	}
	
	public Player(String name, char mark, Board board){
		this.name = name;
		this.mark = mark;
		this.board = board;
	}
	
	public void setOpponent(Player other) {
		opponent = other;
	}
	
	public String name(){
		return name;
	}
	
	public char mark(){
		return mark;
	}
	
	public void play() throws IOException {
		
		int move = 0;
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

	class HumanPlayer extends Player{
		public HumanPlayer(){
		}
	
		public HumanPlayer(String name, char mark, Board board) {
			super(name, mark, board);
		}
	}
	
	class RandomPlayer extends HumanPlayer{
		public RandomPlayer(){
		}
	
		public RandomPlayer(String name, char mark, Board board) {
			super(name, mark, board);
		}
	}
	
	class BlockingPlayer extends RandomPlayer{
		public BlockingPlayer(){
		}
	
		public BlockingPlayer(String name, char mark, Board board) {
			super(name, mark, board);
		}
	}
	
	class SmartPlayer extends BlockingPlayer{
		public SmartPlayer(){
		}
	
		public SmartPlayer(String name, char mark, Board board) {
			super(name, mark, board);
		}
	}