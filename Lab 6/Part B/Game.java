//Game.java
import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ServerSocket;

/**
 * @author Started by: M. Moussavi
 * Completed by: Harry Han, Yida Xu
 * Asks the user to select a player type, creates the player, creates the board,
 * assigns a referee to the game, then initiates the game.
 *
 * @version 1.0
 * @since February 2, 2016
 */
public class Game implements Constants{

	PrintWriter socketOut;
	Socket clientSocket; 
	ServerSocket serverSocket; 
	BufferedReader socketIn;

	private Board theBoard;
	private Referee theRef;
	private Player xPlayer, oPlayer;
	//private Thread t1, t2;
	
	private static int status = -1;
	
	/**
	 * creates a board for the game
	 */
    public Game(int portNumber) {        
		try {
			theBoard  = new Board();
			serverSocket = new ServerSocket(portNumber);
			
			System.out.println("Server is Running....");

			} catch (IOException e) {
				System.err.println(e.getStackTrace());
			}
	}
	
	public void communicate(){
		while (true) {
			try{
				String name = "";

				xPlayer = new Player(name, LETTER_X, this.theBoard, serverSocket.accept());
				xPlayer.setName();

				oPlayer = new Player(name, LETTER_O, this.theBoard, serverSocket.accept());
				oPlayer.setName();

				Referee theRef = new Referee(this.theBoard, xPlayer, oPlayer);
				this.appointReferee(theRef);

				Thread t1 = new Thread(xPlayer);
				Thread t2 = new Thread(oPlayer);

				t1.start();
				t2.start();

				try{
					t1.join();
					t2.join();
				} catch(InterruptedException e){
					System.err.println(e.getStackTrace());
				}

				xPlayer.finalBoard();
				oPlayer.finalBoard();

				xPlayer.winner();
				oPlayer.winner();

			} catch (IOException e) {
				System.err.println(e.getStackTrace());
			}
        }
	}

    
    /**
     * calls the referee method runTheGame
     * @param r refers to the appointed referee for the game 
     * @throws IOException
     */
    public void appointReferee(Referee r) throws IOException {
        theRef = r;
    	theRef.runTheGame();
    }
	
	public static void main(String[] args) throws IOException {
		Game theGame = new Game(9898);
		theGame.communicate();
	}
}