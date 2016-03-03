import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ServerSocket;

// Board.java 
// ENSF 409 - LAB 6 - Ex. C
// This file was originally written for ENGG 335 in fall 2001, and was 
// adapted for ENSF 409 in 2014
//

/** 
 * Modified by: Harry Han, Yida Xu
 *
 * @author Dr. Moussavi
 * @version 1.0
 * @since February 2, 2016
 */
public class Board implements Constants {
	/**
	 * The board for the Tic Tac Toe game to be played on
	 */
	private char theBoard[][];
	
	/**
	 * The number of marks on the board
	 */
	private int markCount;
	private Socket client;

	private PrintWriter socketOut;
	private BufferedReader socketIn;
	/**
	 * Constructs a Board ID with the specific values for markCount and the construction of the board.
	 * - markCount = 0
	 * - Board will be constructed as a 3x3 multidimensional array
	 */
	public Board() {
		markCount = 0;
		theBoard = new char[3][];
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}

	/**
	 * Returns the existence of a mark at a specified row and column.
	 *
	 * @param row the row to check
	 * @param col the column to check
	 *
	 * @return theBoard
	 */
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}

	/**
	 * A boolean method to see if the board game is full. Returns true if full, false if not.
	 *
	 * @return markCount
	 */
	public boolean isFull() {
		return markCount == 9;
	}

	/**
	 * A method that checks if the xPlayer is the winner. Returns 1 if true, 0 if false.
	 *
	 * @return checkWinner
	 */
	public int xWins() {
		return checkWinner(LETTER_X);
	}
	
	/**
	 * A method that checks if the oPlayer is the winner. Returns 1 if true, 0 if false.
	 *
	 * @return checkWinner
	 */
	public int oWins() {
		return checkWinner(LETTER_O);
	}

	/**
	 * Prints the board to the command window
	 */
	public void display(Socket client) {
		try{
			socketOut = new PrintWriter(client.getOutputStream(), true);
    		socketIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
		} catch (IOException e) {
                System.out.println("I/O error: " + e);
        }

		displayColumnHeaders();
		addHyphens();
		for (int row = 0; row < 3; row++) {
			addSpaces();
			socketOut.print("    row " + row + ' ');
			socketOut.flush();
			for (int col = 0; col < 3; col++){
				socketOut.print("|  " + getMark(row, col) + "  ");
				socketOut.flush();
			}
			socketOut.println("|");
			socketOut.flush();
			addSpaces();
			addHyphens();
		}

		socketOut.flush();
	}

	/**
	 * Adds a mark on the specified row, column
	 *
	 * @param row the row to add the mark to
	 * @param col the column to add the mark to
	 * @param mark the type of mark to add on to the board
	 */
	public void addMark(int row, int col, char mark) {
		theBoard[row][col] = mark;
		markCount++;
	}

	/**
	 * Clears and resets the board
	 */
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;
	}

	/**
	 * Checks the winner by verifying that there are 3 of the same marks in a row
	 *
	 * @param mark the mark to verify
	 *
	 * @return checkWinner
	 */
	int checkWinner(char mark) {
		int row, col;
		int result = 0;

		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (theBoard[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}

		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}

		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}
		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (theBoard[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}

	/**
	 * Displays the column headers to the drawing of the board
	 */
	void displayColumnHeaders() {
		socketOut.print("          ");
		socketOut.flush();
		for (int j = 0; j < 3; j++)
			socketOut.print("|col " + j);
			socketOut.flush();
		socketOut.println();
		socketOut.flush();
	}

	/**
	 * Adds hyphens to the drawing of the board
	 */
	void addHyphens() {
		socketOut.print("          ");
		socketOut.flush();
		for (int j = 0; j < 3; j++)
			socketOut.print("+-----");
			socketOut.flush();
		socketOut.println("+");
		socketOut.flush();
	}

	/** 
	 * Adds spaces to the drawing of the board
	 */
	void addSpaces() {
		socketOut.print("          ");
		socketOut.flush();
		for (int j = 0; j < 3; j++)
			socketOut.print("|     ");
			socketOut.flush();
		socketOut.println("|");
		socketOut.flush();
	}
}
