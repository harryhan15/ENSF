import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RandomPlayer extends HumanPlayer{
	
	public RandomPlayer(){
	}

	public RandomPlayer(String name, char mark, Board board) {
		super(name, mark, board);
	}
	
	public void makeMove(){
		
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


	public void play() throws IOException {
		Integer move = -1;
		
		while(true) {
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Who will play first?\nEnter '0' for Player 1 (X) or '1' for Player 2 (O): ");
			String input = stdin.readLine().trim();

			while (input.isEmpty() || parseInt(input) == null) {
				System.out.println("\nPlease try again.");
				System.out.print("Who will play first?\nEnter '0' for Player 1 (X) or '1' for Player 2 (O): ");
				input = stdin.readLine();
			}

			move = parseInt(input);
			if (move == 1 || move == 0) {
				break;
				}
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
	

}
