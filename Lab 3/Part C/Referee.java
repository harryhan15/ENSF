/**
 * ENSF 409 - Lab 3 - Winter 2011
 * @author  Harry Han, Yida Xu
 * January 22, 2015
 */

import java.io.*;

public class Referee {
	
	private Player xPlayer;
	private Player oPlayer;
	private Board board;
	
	public Referee(){
	}
	
	public Referee(Board board, Player xPlayer, Player oPlayer){
		this.xPlayer = xPlayer;
		this.oPlayer = oPlayer;
		this.board = board;
	}
	
	public void runTheGame() throws IOException {
		xPlayer.setOpponent(oPlayer);
		xPlayer.play();
		
	}
}
