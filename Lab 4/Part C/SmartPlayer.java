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
public class SmartPlayer extends BlockingPlayer{
	public SmartPlayer(){
	}
	
	public SmartPlayer(String name, char mark, Board board) {
		super(name, mark, board);
	}
					
	protected void makeMove(){
			int col, row;
			RandomGenerator x = new RandomGenerator();
		
			int[] picker = new int [2];
			picker = x.discrete(0,2);
		
			col = picker[0];
			row = picker[1];
			
			int block = 0;
			int win = 0;
			int i = 0;
			int j = 0;
			int m = 0;
			int n = 0;
			
			for (i = 0; i < 3; i++){
				for (j = 0; j < 3; j++){
					if (testForBlocking(i, j) == true){
						block = 1;
						break;
					}
				}
				if (block == 1){
					break;
				}
			}
			
			for (m = 0; m < 3; m++){
				for (n = 0; n < 3; n++){
					if (testForWinning(m, n) == true){
						block = 0;
						win = 1;
						break;
					}
				}
				if (win == 1){
					break;
				}
			}
			
			if (win == 0 && block == 0){
				//do nothing
			}
			else if(block == 1){
				row = i;
				col = j;
			}
			else if(win == 1){
				row = m;
				col = n;
			}
		
			if ((board.getMark(row, col) != 'O' && board.getMark(row, col) != 'X') && block == 1 || win == 1) {
				board.addMark(row, col, mark);
				board.display();
			}
			else if ((board.getMark(row, col) != 'O' && board.getMark(row, col) != 'X') && block == 0 && win == 0){
				board.addMark(row, col, mark);
				board.display();
			}
			else if ((board.getMark(row, col) == 'O' || board.getMark(row, col) == 'X') && block == 0 && win == 0) {
				makeMove();
			}
			else if ((board.getMark(row, col) == 'O' || board.getMark(row, col) == 'X') && block == 1 && win == 0) {
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
	
	public boolean testForWinning(int row, int col){
		boolean result = false;
		char oppo;
		
		if (mark == 'X'){
			oppo = 'O';
			}
		else
			oppo = 'X';
		
		//test for two same marks in each row
		if(board.getMark(row, 0) == mark && board.getMark(row, 1) == mark && col == 2) {
			if (board.getMark(row, 2) == oppo){
				result = false;
			}
			else
				result = true;
		}
		else if(board.getMark(row, 0) == mark && board.getMark(row, 2) == mark && col == 1){
			if (board.getMark(row, 1) == oppo){
				result = false;
			}
			else
				result = true;
		}
		else if(board.getMark(row, 1) == mark && board.getMark(row, 2) == mark && col == 0) {
		   	if (board.getMark(row, 0) == oppo){
				result = false;
			}
			else
				result = true;
		}
		
		//test for two same marks in each column
		if(board.getMark(0, col) == mark && board.getMark(1, col) == mark && row == 2) {
			if (board.getMark(2, col) == oppo){
				result = false;
			}
			else
				result = true;
		}
		else if(board.getMark(0, col) == mark && board.getMark(2, col) == mark && row == 1){
			if (board.getMark(1, col) == oppo){
				result = false;
			}
			else
				result = true;
		}
		else if(board.getMark(1, col) == mark && board.getMark(2, col) == mark && row == 0) {
			if (board.getMark(0, col) == oppo){
				result = false;
			}
			else
				result = true;
		}
		
		//check diagonal 1
		if(row == col){
			if ((board.getMark(0, 0) == mark && board.getMark(1, 1) == mark && row == 2) ||
				(board.getMark(0, 0) == mark && board.getMark(2, 2) == mark && row == 1) ||
				(board.getMark(1, 1) == mark && board.getMark(2, 2) == mark && row == 0)){
					result = true;
			}
		}
			//check diagonal 2
		if((row == 2 && col == 0) || (row == 0 && col == 2) || (row == 1 && col == 1))
			if ((board.getMark(2, 0) == mark && board.getMark(1, 1) == mark && row == 0) ||
				(board.getMark(2, 0) == mark && board.getMark(0, 2) == mark && row == 1) ||
				(board.getMark(1, 1) == mark && board.getMark(0, 2) == mark && row == 2)){
					result = true;
			}
		
		return result;
	}	
}
