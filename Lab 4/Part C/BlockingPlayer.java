import java.io.*;
import java.text.*;

/**
 * ENSF 409 - Lab 3 - Winter 2016
 * @author  Harry Han, Yida Xu
 * @version 1.0
 * @since February 2, 2015
 */
 
 	/**
	 * A class constructor that inherits Players private members for human players
	 */ 
public class BlockingPlayer extends RandomPlayer{
	protected BlockingPlayer(){
	}
	
	protected BlockingPlayer(String name, char mark, Board board) {
		super(name, mark, board);
	}
		
	protected void makeMove(){
			int col, row;
			RandomGenerator x = new RandomGenerator();
		
			int[] picker = new int [2];
			picker = x.discrete(0,2);
		
			col = picker[0];
			row = picker[1];
			
			int result = 0;
			int i = 0;
			int j = 0;
			
			for (i = 0; i < 3; i++){
				for (j = 0; j < 3; j++){
					if (testForBlocking(i, j) == true){
						result = 1;
						break;
					}
				}
				if (result == 1){
					break;
				}
			}
			
			if (result == 1){
				row = i;
				col = j;
			}
		
			if ((board.getMark(row, col) != 'O' && board.getMark(row, col) != 'X') && result == 1) {
				board.addMark(row, col, mark);
				board.display();
			}
			else if ((board.getMark(row, col) != 'O' && board.getMark(row, col) != 'X') && result == 0){
				board.addMark(row, col, mark);
				board.display();
			}
			else if ((board.getMark(row, col) == 'O' || board.getMark(row, col) == 'X') && result == 0) {
				makeMove();
			}
			else if ((board.getMark(row, col) == 'O' || board.getMark(row, col) == 'X') && result == 1) {
				if(row == 2){
					row = 0;
					col++;
					if(col == 2){
						makeMoveWithoutBlocking();
					}
					else {
						if (testForBlocking(row, col) == true){
							row = i;
							col = j;
						}
						board.addMark(row, col, mark);
						board.display();
					}
				}
				else if(col == 2){
					col = 0;
					row++;
					if(row == 2){
						makeMoveWithoutBlocking();
					}
					else {
						if (testForBlocking(row, col) == true){
							row = i;
							col = j;
						}
						board.addMark(row, col, mark);
						board.display();
					}
				}
				
			}
	}
	
	public boolean testForBlocking(int row, int col){
		boolean result = false;
		char oppo;
		
		if (mark == 'X'){
			oppo = 'O';
			}
		else
			oppo = 'X';
		
		//test for two same marks in each row
		if(board.getMark(row, 0) == oppo && board.getMark(row, 1) == oppo && col == 2) {
			if (board.getMark(row, 2) == mark){
				result = false;
			}
			else
				result = true;
		}
		else if(board.getMark(row, 0) == oppo && board.getMark(row, 2) == oppo && col == 1){
			if (board.getMark(row, 1) == mark){
				result = false;
			}
			else
				result = true;
		}
		else if(board.getMark(row, 1) == oppo && board.getMark(row, 2) == oppo && col == 0) {
		   	if (board.getMark(row, 0) == mark){
				result = false;
			}
			else
				result = true;
		}
		
		//test for two same marks in each column
		if(board.getMark(0, col) == oppo && board.getMark(1, col) == oppo && row == 2) {
			if (board.getMark(2, col) == mark){
				result = false;
			}
			else
				result = true;
		}
		else if(board.getMark(0, col) == oppo && board.getMark(2, col) == oppo && row == 1){
			if (board.getMark(1, col) == mark){
				result = false;
			}
			else
				result = true;
		}
		else if(board.getMark(1, col) == oppo && board.getMark(2, col) == oppo && row == 0) {
			if (board.getMark(0, col) == mark){
				result = false;
			}
			else
				result = true;
		}
		
		//check diagonal 1
		if(row == col){
			if ((board.getMark(0, 0) == oppo && board.getMark(1, 1) == oppo && row == 2) ||
				(board.getMark(0, 0) == oppo && board.getMark(2, 2) == oppo && row == 1) ||
				(board.getMark(1, 1) == oppo && board.getMark(2, 2) == oppo && row == 0)){
					result = true;
			}
		}
			//check diagonal 2
		if((row == 2 && col == 0) || (row == 0 && col == 2) || (row == 1 && col == 1))
			if ((board.getMark(2, 0) == oppo && board.getMark(1, 1) == oppo && row == 0) ||
				(board.getMark(2, 0) == oppo && board.getMark(0, 2) == oppo && row == 1) ||
				(board.getMark(1, 1) == oppo && board.getMark(0, 2) == oppo && row == 2)){
					result = true;
			}
		
		return result;
	}

	protected void makeMoveWithoutBlocking(){
		
		int col, row;
		RandomGenerator x = new RandomGenerator();
		
		int[] picker = new int [2];
		picker = x.discrete(0,2);
		
		col = picker[0];
		row = picker[1];
		
		if (board.getMark(row, col) == 'O' || board.getMark(row, col) == 'X'){
			makeMove();
		}
		else {
			board.addMark(row, col, mark);
			board.display();
		}	
	}
}

