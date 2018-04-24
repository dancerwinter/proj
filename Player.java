import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.ArrayList;

import java.io.*;
import java.net.*;

/**
 * @author Neil Daniel B. Bautista, Jessica Anne M. Manzano
 * @IDNumber 170252, 171429
 * @version April 24, 2018
 *
 */

public class Player extends JFrame {
	
	private int width, height;
	private Container container;
	private DrawingComponent dc;
	private MyKeyListener mkl;
	private ClientSideConnection csc;
	private PlayerShip ps;

	/**
	 *@Constructor
	 * param: w = width of frame h = height of frame
	 *
	 */
	public Player(int w, int h) {
		width = w;
		height = h;
		container = this.getContentPane();
		dc = new DrawingComponent();
		ps = new PlayerShip(Color.RED);
		mkl = new MyKeyListener();
		this.addKeyListener(mkl);
	}

	public void setUpGUI() {
		this.setSize(width, height);
		this.setTitle("SPACE WARS");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		container.setLayout(new BorderLayout());
		container.add(dc);

		this.setVisible(true);
	}
	/**
	* @method
	* void sets up connection to GameServer
	*/
	public void connectToServer()
	{
		csc = new ClientSideConnection();
		
	}

	private class MyKeyListener implements KeyListener {

		public void keyTyped(KeyEvent ke) {

		}

		public void keyPressed(KeyEvent ke) {
			int keyCode = ke.getKeyCode();
			int speed = 5;

			switch (keyCode) {
				case KeyEvent.VK_UP: 
					System.out.println("^");
					break;
                case KeyEvent.VK_DOWN: 
                	System.out.println("V"); 
                	break;
                case KeyEvent.VK_LEFT: 
                	System.out.println("<");
                	ps.moveLeft(speed);
                	dc.repaint();
                	break;
                case KeyEvent.VK_RIGHT: 
                	System.out.println(">"); 
                	ps.moveRight(speed);
                	dc.repaint();
                	break;
                case KeyEvent.VK_SPACE:
                	System.out.println("SPACE");
                	break;
                default: 
                	System.out.println("Other key was pressed"); 
                	break;
			}
		}

		public void keyReleased(KeyEvent ke) {
			
		}
	}

	/**
	 * This private class is for the client side connection.
	 */
	private class ClientSideConnection
	{
			private Socket socket;
			private DataInputStream dataIn;
			private DataOutputStream dataOut;
			
		public ClientSideConnection()
		{
			try
			{
				socket = new Socket("localhost",1842);
				dataIn = new DataInputStream(socket.getInputStream());
				dataOut = new DataOutputStream(socket.getOutputStream());
			}
			catch(IOException ex)
			{
				System.out.println("IOException from CSC Constructor");
			}
		}
	}

	public static void main (String[] args) {
		Player p = new Player(900, 650);
		p.setUpGUI();
		
	}
}