import java.io.*;
import java.net.*;
import java.util.*;

/**
 * @author Neil Daniel B. Bautista, Jessica Anne M. Manzano
 * @IDNumber 170252, 171429
 * @version April 24, 2018
 * Bautista, Neil Daniel - 170252
 *
 *We have not discussed the Java language code 
 *in our program with anyone
 *other than our instructor or the teaching
 *assistants assigned to this course.
 *
 *We have not used Java language code 
 *obtained from another student, or
 *any other unauthorized source, either 
 *modified or unmodified.
 *
 *If any Java language
 *code or documentation used in our program was
 *obtained from another source, such as a text
 *book or course notes, those have been clearly
 *noted with a proper citation in the 
 *comments of our code. 
 *
 * This program is the server class.
 */

public class GameServer {
	private ServerSocket ss;
	private DataInputStream dataIn;
	private DataOutputStream dataOut;
	private int numPlay,shotsFired;
	private ServerSideConnectionOut player1Out;
	private ServerSideConnectionOut player2Out;
	private ServerSideConnectionIn player1In;
	private ServerSideConnectionIn player2In;
	private int playerNum;
	private int player1HP;
	private int player2HP;
	/**
	 * This is the constructor for the GameServer class.
	 *
	 */
	public GameServer() {
		System.out.println("TheServer has been instantiated");
		playerNum = 0;
		try {
			ss = new ServerSocket(1842);
		} 

		catch(IOException ex) {
			System.out.println("IOException from server Constructor");	
		}
	}

	/**
	 * This is a method that accepts clients.
	 */
	public void connectPlayers() {
		 try {
			System.out.println("Waiting for connection...");
			
			while(numPlay < 2) {
				Socket s = ss.accept();
				numPlay++;
				System.out.println("Player#" + numPlay + " has connected");
				ServerSideConnectionOut ssco = new ServerSideConnectionOut(s, numPlay);
				ServerSideConnectionIn ssci = new ServerSideConnectionIn(s);
				 
				
				if(numPlay == 1) {
					player1Out = ssco;
					player1In = ssci;
				}

				else {
					player2Out = ssco;
					player2In = ssci;

				}

				Thread t = new Thread(ssco);
				Thread t1 = new Thread(ssci);
				t.start();
				t1.start();
			}
		} 

		catch(IOException ex) {
			System.out.println("IOException from connectPlayers()");
		}
		
	}
	 /**
	  * This is a class to support both player connections made (Output)
	  * @param Socket s: holds the socket of the player it will handle, int id: holds the ID number of the player
	  * 
	  */
	private class ServerSideConnectionOut implements Runnable {
		private Socket socket;
		private DataInputStream dataIn;
		private DataOutputStream dataOut;
		private int playerID;

		/**
		 * @param s socket of player 1 or 2
		 * @param id numPlay variable to determine who is player one and two based on who connected first
		 */
		public ServerSideConnectionOut(Socket s, int id) {
			socket = s;
			playerID = id;
			try {
				dataIn = new DataInputStream(socket.getInputStream());
				dataOut = new DataOutputStream(socket.getOutputStream());
			}

			catch (IOException ex) {
				System.out.println("IOException from run() SSCO Constructor of Player " + playerID);
			}
		}

		public void run() {
			try {

				dataOut.writeInt(playerID);
				dataOut.flush();
				if(playerID == 2){
					sendStartMessage();
					player1Out.sendStartMessage();
				}
				
				// while(true) {
				// 	int playerHit = dataIn.readInt();
				// 	if(playerHit == 1){
				// 		System.out.println("player 1 hit");
				// 		player1HP--;
				// 	}
				// 	else if(playerHit == 2){
				// 		System.out.println("player 2 hit");
				// 		player2HP--;
				// 	}
				// 	if (player1HP == 0){
				// 		dataOut.writeInt(1);
				// 		dataOut.flush();
				// 	}
				// 	else if(player2HP == 0){
				// 		dataOut.writeInt(2);
				// 		dataOut.flush();
				// 	}
				// }

			}

			catch(IOException ex) {
				System.out.println("IOException from run() method SSCO of Player " + playerID);
			}
		}
		public void sendStartMessage(){
			try{
				dataOut.writeUTF("start");
				dataOut.flush();
			}catch(IOException e){
				sendStartMessage();
			}
		}
		public void sendShot(Double x){
			try{
				System.out.println("player " + playerID + " was shot at " + x);
				dataOut.writeDouble(x);	
				dataOut.writeInt(0);
				dataOut.flush();
			}catch(IOException e){
				System.out.println("sendShot() error at SSCO of Player " + playerID);
			}	
		}
		public void sendHP(int i) throws IOException{ 
			System.out.println("hp sent");
			dataOut.writeDouble(-1000);
			dataOut.writeInt(i);
			dataOut.flush();
		}

	}

	/**
	* This Private Class facilitates most of the interactions between the two players and the server
	* It will call on the methods of the SSO class to output necessary data to the clients
	*
	*/

	private class ServerSideConnectionIn implements Runnable {
		private Socket socket;
		private DataInputStream dataIn;
		private DataOutputStream dataOut;
		

		public ServerSideConnectionIn(Socket s) {
			socket = s;
			try {
				dataIn = new DataInputStream(socket.getInputStream());
				dataOut = new DataOutputStream(socket.getOutputStream());
			} 

			catch(IOException ex){
				System.out.println("IOException from run() SSCI Constructor");
			}
		}
		public void run() {

			try {
				while(true) {
					playerNum = dataIn.readInt();
					System.out.println(playerNum);
					Double x = dataIn.readDouble();
					if(playerNum == 1){
						player2Out.sendShot(x);
					}
					else if(playerNum == 2){
						player1Out.sendShot(x);
					}
					int playerHit = dataIn.readInt();

					if(playerHit == 1){
						player1HP--;
						System.out.println("player 1 is hit");
						player1Out.sendHP(1);
						player2Out.sendHP(1);
					}
					else if(playerHit == 2){
						player2HP--;
						System.out.println("player 2 is hit");
						player1Out.sendHP(2);
						player2Out.sendHP(2);

					}

				}
			}
			catch(IOException e) {
				System.out.println("Error in run() method of SSCI ");
			}
		}
	}

	/**
	 * This is the main method for the GameServer class.
	 */
	public static void main(String args[]) {
		GameServer gs = new GameServer();
		gs.connectPlayers();
	}
}