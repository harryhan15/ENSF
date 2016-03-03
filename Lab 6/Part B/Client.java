import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private PrintWriter socketOut;
	private Socket palinSocket;
	private BufferedReader stdIn;
	private BufferedReader socketIn;

	public Client(String serverName, int portNumber) {
		try {
			palinSocket = new Socket(serverName, portNumber);
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			socketIn = new BufferedReader(new InputStreamReader(
					palinSocket.getInputStream()));
			socketOut = new PrintWriter((palinSocket.getOutputStream()), true);
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}

	public void communicate()  {

		String line = "";
		String response = "";
		while (!line.equals("QUIT")) {
			try {
				response = socketIn.readLine();

				if(response.equals("name")) {
					response = socketIn.readLine();
					System.out.println(response);
					line = stdIn.readLine();
					socketOut.println(line);
					socketOut.flush();
					System.out.println("\nWaiting for opponent...");
				}
				else if(response.equals("first")) {
					System.out.println();

					response = socketIn.readLine();
					System.out.println(response);
					response = socketIn.readLine();
					System.out.println(response);

					line = stdIn.readLine();
					socketOut.println(line);
					socketOut.flush();
				}
				else if(response.equals("game")) {
					while(true){
						getAndPrintBoard();

						response = socketIn.readLine();
						System.out.println(response);
						line = stdIn.readLine();
						socketOut.println(line);
						socketOut.flush();

						response = socketIn.readLine();
						System.out.println(response);
						line = stdIn.readLine();
						socketOut.println(line);
						socketOut.flush();

						response = socketIn.readLine();
						if(response.equals("occupied")) {
							response = socketIn.readLine();
							System.out.println("\n" + response);
						}
						else if(response.equals("good")) {
							getAndPrintBoard();
							break;
						}

						response = socketIn.readLine();
					}
				}
				else if(response.equals("winner")) {
					//getAndPrintBoard();
					
					response = socketIn.readLine();
					System.out.println(response);
					break;
				}
				else if(response.equals("end")) {
					
				}
			} catch (IOException e) {
				System.out.println("Sending error: " + e.getMessage());
			}
		}
		try {
			stdIn.close();
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			System.out.println("Closing error: " + e.getMessage());
		}

	}

	public void getAndPrintBoard() {
		int count = 0;
		String response = "";
		System.out.println();
		while(count < 14) {
			try{
				response = socketIn.readLine();
				System.out.println(response);
				count++;
			} catch (IOException e) {
				System.out.println("Sending error: " + e.getMessage());
			}
		}
		System.out.println();
	}

	public static void main(String[] args) throws IOException  {
		Client aClient = new Client("localhost", 9898);
		aClient.communicate();
	}
}