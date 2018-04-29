import java.io.*;
import java.net.*;
import java.util.*;

/**
 * @author Neil Daniel B. Bautista, Jessica Anne M. Manzano
 * @IDNumber 170252, 171429
 * @version April 24, 2018
 *
 * This program is the server class.
 */
public class GameServer {
	private ServerSocket ss;
	private DataInputStream dataIn;
	private DataOutputStream dataOut;
	private int numPlay;
	private ServerSideConnectionOut player1Out;
	private ServerSideConnectionOut player2Out;
	
	/**
	 * This is the constructor for the GameServer class.
	 */
	public GameServer() {
		System.out.println("TheServer has been instantiated");
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
				if(numPlay == 1)
				{
					player1Out = ssco;
				}
				else{
					player2Out = ssco;
				}
				Thread t = new Thread(ssco);
				t.start();
			}
		} catch(IOException ex) {
			System.out.println("IOException from connectPlayers()");
		}
		
	}
	 /**
	 * This is a class to support both player connections made (Output)
	 * @Parameters s: Socket of the player 1 or 2; id: numPlay variable to determine who is player one and two based on who connected first
	 */
	private class ServerSideConnectionOut implements Runnable
	{
		private Socket socket;
		private DataInputStream dataIn;
		private DataOutputStream dataOut;
		private int playerID;

		public ServerSideConnectionOut(Socket s, int id)
		{
			socket = s;
			playerID = id;
			try{
				dataIn = new DataInputStream(socket.getInputStream());
				dataOut = new DataOutputStream(socket.getOutputStream());
			} catch (IOException ex){
				System.out.println("IOException from run() SSCO Constructor");
			}
		}
		public void run()
		{
			try{
				dataOut.writeInt(playerID);
				dataOut.flush();
				while(true){
					

				}

			}catch(IOException ex){
				System.out.println("IOException from run() method SSCO");
			}
		}
	}
	private class ServerSideConnectionIn implements Runnable
	{
		private Socket socket;
		private DataInputStream dataIn;
		private DataOutputStream dataOut;

		public ServerSideConnectionIn
		{
			socket = s;
			try{
				dataIn = new DataInputStream(socket.getInputStream());
				dataOut = new DataOutputStream(socket.getOutputStream());
			} catch(IOException ex){
				System.out.println("IOException from run() SSCI Constructor");
			}
		}
		public void run()
		{
			// try{
			
			// }
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