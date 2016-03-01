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
	private Thread t1, t2;
	
	private static int status = -1;
	
	/**
	 * creates a board for the game
	 */
    public Game(int portNumber) throws IOException {        
		try {
		    theBoard  = new Board();
			serverSocket = new ServerSocket(portNumber);
			
			System.out.println("Server is Running....");

			} catch (IOException e) {
				System.err.println(e.getStackTrace());
			}
	}
	
	public void communicate() {
		String name = "";
		while (true) {
            try {
            	if(status == -1){
					xPlayer = new Player(name, LETTER_X, this.theBoard, serverSocket.accept());
					xPlayer.setName();
					status = 0;
				}
				else if(status == 0){
					oPlayer = new Player(name, LETTER_O, this.theBoard, serverSocket.accept());
					oPlayer.setName();
					status = 1;
				}
				else if(status == 1){
					Referee theRef = new Referee(this.theBoard, xPlayer, oPlayer);
   		 			this.appointReferee(theRef);
   		 			status = 2;
				}
				else if(status == 2) {
					t1 = new Thread(xPlayer);
					t1.start();
					status = 3;
				}
				else if(status == 3) {
					t2 = new Thread(oPlayer);
					t2.start();
					status = 4;
				}
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
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