import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import java.util.ArrayList;

import javax.swing.*;
import javax.swing.Timer;

import java.io.*;
import java.net.*;

/**
 * @author Neil Daniel B. Bautista, Jessica Anne M. Manzano
 * @IDNumber 170252, 171429
 * @version April 24, 2018
 *
 */

/**
 *
 *
 */
public class PlayerGUI extends JFrame {
	
	private int width, height;
	private int bulletsFired, bulletsLeft;
	private Container container;
	private DrawingComponent dc;
	private MyActionListener mal;
	private MyKeyListener mkl;
	private ClientSideConnection csc;
	private PlayerShip ps;
	private Projectile bullet1, bullet2, bullet3, bullet4, bullet5;
	private boolean up, down, left, right, spacebar;
	private Timer tm;

	/**
	 * @Constructor
	 * param: w = width of frame h = height of frame
	 *
	 */
	public PlayerGUI(int w, int h) {
		width = w;
		height = h;
		container = this.getContentPane();
		ps = new PlayerShip(Color.RED);
		bullet1 = new Projectile(950, 650);
		bullet2 = new Projectile(950, 650);
		bullet3 = new Projectile(950, 650);
		bullet4 = new Projectile(950, 650);
		bullet5 = new Projectile(950, 650);

		dc = new DrawingComponent();	
		mkl = new MyKeyListener();
		tm = new Timer(10, new MyActionListener());
		tm.start();

		bulletsFired = 0;
		bulletsLeft = 5;

		this.addKeyListener(mkl);
	}

	public void setUpGUI() {
		this.setSize(width, height);
		this.setTitle("SPACE WARS");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		container.setLayout(new BorderLayout());

		container.add(dc);

		dc.revalidate();
		
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

	private class DrawingComponent extends JComponent{

		public DrawingComponent() {
			System.out.println("DrawingComponent instantiated");
		}

		protected void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setRenderingHints(rh);

			
			bullet1.draw(g2d);
			bullet2.draw(g2d);
			bullet3.draw(g2d);
			bullet4.draw(g2d);
			bullet5.draw(g2d);

			ps.draw(g2d);
			
		}
	}

	private class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
				
			double speed = 5;

			if (up) {
				ps.moveUp(-speed);
				dc.repaint();
			}
			else if (down) {
				ps.moveDown(speed);
				dc.repaint();
			}
			else if (left) {
				ps.moveLeft(-speed);
				dc.repaint();
			}
			else if (right) {
				ps.moveRight(speed);
				dc.repaint();
			}

			if (spacebar && bulletsFired < 6) {
				if (bulletsFired == 1) {
					// bullet1.reloadBullet(ps.getPositionX(), ps.getPositionY());

					bullet1.fireBullet(speed * 5, ps.getPositionX(), ps.getPositionY());
					dc.repaint();

				}

				else if (bulletsFired == 2) {
					bullet2.fireBullet(speed * 5, ps.getPositionX(), ps.getPositionY());
					dc.repaint();
				}

				else if (bulletsFired == 3) {
					bullet3.fireBullet(speed * 5, ps.getPositionX(), ps.getPositionY());
					dc.repaint();
				}

				else if (bulletsFired == 4) {
					bullet4.fireBullet(speed * 5, ps.getPositionX(), ps.getPositionY());
					dc.repaint();
				}

				else if (bulletsFired == 5) {
					bullet5.fireBullet(speed * 5, ps.getPositionX(), ps.getPositionY());
					dc.repaint();

					/*if (bulletsLeft == 0) {
					bulletsFired = 0;
					}*/
				}

				System.out.println(bulletsFired);

				
			}
		}
	}

	/**
	 * This private class implements KeyListener.
	 * 
	 */
	private class MyKeyListener implements KeyListener {

		public void keyTyped(KeyEvent ke) {

		}

		public void keyPressed(KeyEvent ke) {
			int keyCode = ke.getKeyCode();
			
			switch (keyCode) {
				case KeyEvent.VK_UP: 
					up = true;
					down = false;
					left = false;
					right = false;
					break;

                case KeyEvent.VK_DOWN: 
                	up = false;
					down = true;
					left = false;
					right = false;
                	break;

                case KeyEvent.VK_LEFT: 
                	up = false;
					down = false;
					left = true;
					right = false;
                	break;

                case KeyEvent.VK_RIGHT: 
                	up = false;
					down = false;
					left = false;
					right = true;
                	break;

                case KeyEvent.VK_SPACE:
                	spacebar = true;
                	break;
                	
                default:
                	System.out.println("Other key was pressed"); 
                	break;
			}
		}

		public void keyReleased(KeyEvent ke) {
			int keyCode = ke.getKeyCode();
			
			switch (keyCode) {
				case KeyEvent.VK_UP: 
					up = false;
					break;

                case KeyEvent.VK_DOWN: 
                	down = false;
                	break;

                case KeyEvent.VK_LEFT: 
                	left = false;
					break;

                case KeyEvent.VK_RIGHT: 
					right = false;
                	break;

                case KeyEvent.VK_SPACE:
                	bulletsFired++;
                	bulletsLeft--;
                	break;
                }
		}
	}

	/**
	* @method
	* void sets up connection to GameServer
	*/
	public void connectToServer()
	{
		csc = new ClientSideConnection();
		
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
			System.out.println("ClientSideConnectionMade");
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
		PlayerGUI p = new PlayerGUI(900, 650);
		
		p.setUpGUI();
		
		
	}
}