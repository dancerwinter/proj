import java.io.*;
import java.net.*;
import java.util.*;

public class GameServer {
	private ServerSocket ss;
	private DataInputStream dataIn;
	private DataOutputStream dataOut;
	private int numPlay;
	
	public GameServer() {
		System.out.println("TheServer has been instantiated");
		try {
			ss = new ServerSocket(1842);
		} 

		catch(IOException ex) {
			System.out.println("IOException from server Constructor");	
		}
	}

	public void connectPlayers() {
		 try {
			System.out.println("Waiting for connection...");
			while(numPlay < 2) {
				Socket s = ss.accept();
				numPlay++;
				System.out.println("Player#" + numPlay + " has connected");
			}
		}

		catch(IOException ex) {
			System.out.println("IOException from connectPlayers()");
		}
		
	}

	public static void main(String args[]) {
		GameServer gs = new GameServer();
		gs.connectPlayers();
	}
}