import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ServerSocket;

public class Server {
	PrintWriter socketOut;
	Socket clientSocket; 
	ServerSocket serverSocket; 
	BufferedReader socketIn;
	
	public Server(int portNumber) throws IOException {
		try {
			serverSocket = new ServerSocket(portNumber);
			
			System.out.println("Server is Running....");
			clientSocket = serverSocket.accept();
			socketOut = new PrintWriter(clientSocket.getOutputStream(), true);
    		socketIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			} catch (IOException e) {
				System.err.println(e.getStackTrace());
			}	
		}
		
	public void communicate() throws IOException {
		StringBuffer read = null;
		String palinCheck = "";
		
		while(true) {
			read = new StringBuffer(socketIn.readLine());
			String line = read.toString();
				 
			if(line.equals("QUIT"))
				break;
				
			palinCheck = palindromeCheck(read.toString());
			socketOut.println(palinCheck);	
		}
		
		try {
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			System.out.println("Closing error: " + e.getMessage());
		}
	}
	
	public String palindromeCheck(String line) {
		int len = line.length() - 1;
		String returnString = "";
		
		for(int i = 0; i < line.length()/2; i++){
			if(line.charAt(i) != line.charAt(len)){
				returnString = line + " is not a palindrome.";
				break;
			}
			else
				returnString = line + " is a palindrome.";
			len--;
		}
		
		return returnString;
	}

	public static void main(String[] args) throws IOException  {
		Server aServer = new Server(9898);
		aServer.communicate();
	}
}