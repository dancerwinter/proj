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
 * This program is a game.
 */

public class Player extends JFrame{
	
	private int width, height;
	private int bulletsFired, bulletsLeft, remainingHealth;
	private Container container;
	private DrawingComponent dc;
	private MyActionListener mal;
	private MyKeyListener mkl;
	private ClientSideConnection csc;

	private PlayerShip ps;
	private Background bg;
	private HealthBar hb;
	private ReloadText reloadText;

	private boolean isShot;
	private Projectile bullet1, bullet2, bullet3, bullet4, bullet5, b;

	private Projectile testBullet;

	private boolean up, down, left, right, spacebar, reload;
	private Timer tm;
	private int playerID;
	private int otherPlayer;
	private int shotsMade;

	/**
	 * This is the constructor for the Player class.
	 * @param w This is the width of frame
	 * @param h This is the height of frame
	 */
	public Player(int w, int h) {
		width = w;
		height = h;
		container = this.getContentPane();
		shotsMade = 0;
		ps = new PlayerShip();
		hb = new HealthBar();
		reloadText = new ReloadText();
		bg = new Background();
		b = new Projectile(1000,1000);
		testBullet = new Projectile(100, 300);
		bullet1 = new Projectile(690, 550);
		bullet2 = new Projectile(730, 550);
		bullet3 = new Projectile(770, 550);
		bullet4 = new Projectile(810, 550);
		bullet5 = new Projectile(850, 550);
		isShot = false;
		dc = new DrawingComponent();	
		mkl = new MyKeyListener();
		tm = new Timer(10, new MyActionListener());
		tm.start();

		remainingHealth = 5;
		bulletsFired = 0;
		bulletsLeft = 5;
		this.addKeyListener(mkl);
	}

	public void setUpGUI() {
		this.setSize(width, height);
		this.setTitle("SPACE WARS" + playerID);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		container.setLayout(new BorderLayout());
		container.add(dc);

		// dc.revalidate();
		
		this.setVisible(true);
	}

	private class DrawingComponent extends JComponent{

		public DrawingComponent() {
			System.out.println("DrawingComponent instantiated");
		}

		protected void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setRenderingHints(rh);
			
			bg.draw(g2d);

			reloadText.draw(g2d);

			bullet1.draw(g2d);
			bullet2.draw(g2d);
			bullet3.draw(g2d);
			bullet4.draw(g2d);
			bullet5.draw(g2d);
			b.draw(g2d);

			testBullet.draw(g2d);

			hb.draw(g2d);

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

			if (testBullet.isColliding(ps)) {
				int i = 0;
				System.out.println("OUCH " + i);
				i++;
			}

			if (reload) {
				bullet1.loadBullet(690, 550);
				bullet2.loadBullet(730, 550);
				bullet3.loadBullet(770, 550);
				bullet4.loadBullet(810, 550);
				bullet5.loadBullet(850, 550);
				hb.updateHP(ps.getHealth());

				dc.repaint();
			}

			if (bulletsFired >= 1) {
				bullet1.fireBullet();
				dc.repaint();
			}

			if (bulletsFired >= 2) {
				bullet2.fireBullet();
				dc.repaint();
			}

			if (bulletsFired >= 3) {
				bullet3.fireBullet();
				dc.repaint();
			}

			if (bulletsFired >= 4) {
				bullet4.fireBullet();
				dc.repaint();
			}

			if (bulletsFired >= 5) {
				bullet5.fireBullet();
				reloadText.animate(bulletsFired);
				dc.repaint();
			}
		}
	}

	/**
	 * This private class implements KeyListener.
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
                	bulletsFired++;

                	if (bulletsFired == 1) {
                		bullet1.loadBullet(ps.getCenterX() - 10, ps.getCenterY());
                	}

                	else if (bulletsFired == 2) {
                		bullet2.loadBullet(ps.getCenterX() - 10, ps.getCenterY());
                	}

                	else if (bulletsFired == 3) {
                		bullet3.loadBullet(ps.getCenterX() - 10, ps.getCenterY());
                	}

                	else if (bulletsFired == 4) {
                		bullet4.loadBullet(ps.getCenterX() - 10, ps.getCenterY());
                	}

                	else if (bulletsFired == 5) {
                		bullet5.loadBullet(ps.getCenterX() - 10, ps.getCenterY());
                	}
                	csc.checkFire();
                	break;

                case KeyEvent.VK_CONTROL:
                	if (bulletsFired >= 5) {
	                	reload = true;
	                	bulletsFired = 0;	
                	}

                	remainingHealth--;
                	reloadText.animate(bulletsFired);

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
                	spacebar = false;
                	break;

                case KeyEvent.VK_CONTROL:
                	reload = false;
                	break;
                }
            }
		}
	
	/**
	 * This private class is for the client side connection.
	 */
	/* I was thinking na
	If the bulletsFired is 1
	Then then it'll check if it's out of frame constantly
	Once outOfFrame is true
	It'll writeUTF to the server the string true*/
	private class ClientSideConnection {
			private Socket socket;
			private DataInputStream dataIn;
			private DataOutputStream dataOut;
			private Player p;
			
		public ClientSideConnection(Player x) {
			System.out.println("ClientSideConnectionMade");
			try {
				x.setUpGUI();
				socket = new Socket("localhost", 1842);
				dataIn = new DataInputStream(socket.getInputStream());
				dataOut = new DataOutputStream(socket.getOutputStream());
				playerID = dataIn.readInt();
				System.out.println("Connected to server as player number" + playerID);		
			}

			catch(IOException ex) {
				System.out.println("IOException from CSC Constructor");
			}
		}


		public void checkFire(){
			try{
				// only works when there are bullets to fire.
				if(bulletsFired <= 5){
					System.out.println("checkFireCalled" + " " + bulletsFired);
					if(bulletsFired == 1){
						// do{
						// 	System.out.println("bullet1 not out of frame");
						// }while(!bullet1.isOutOfFrame());
						if (bullet1.isOutOfFrame()){
							System.out.println("bullet1 outofFrame");
							dataOut.writeInt(bulletsFired);
							dataOut.flush();
						}
						
					}
				}
				
				
		public void checkFire() {
			// try{

				// if (bulletsFired == 1){
				// 	dataOut.writeInt(bulletsFired);
				// }
				// else if(bulletsFired == 2){
				// 	dataOut.writeInt(bulletsFired);
				// }
				// else if(bulletsFired == 3){
				// 	dataOut.writeInt(bulletsFired);
				// }
				// else if(bulletsFired == 4){
				// 	dataOut.writeInt(bulletsFired);
				// }
				// else if(bulletsFired == 5){
				// 	dataOut.writeInt(bulletsFired);
				
				// }
			} catch(IOException e){
				System.out.println("Error on checkFire() method in CSC of player" + playerID);
			}
		}
	}

	public void connectToServer(){
		csc = new ClientSideConnection(this);	
	}
	// public void spacebarPressed(){
	// 	return spacebar;
	// }

	public static void main(String[] args) {
		Player p = new Player(900, 650);
		// p.setUpGUI();
		p.connectToServer();
			
	}

}