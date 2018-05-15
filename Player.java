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
	private int shotCounter;
	private PlayerShip ps;
	private Background bg;
	private HealthBar hb;
	private ReloadText reloadText;
	private double shotCoordinates;
	private boolean isShot;
	private Projectile bullet1, bullet2, bullet3, bullet4, bullet5, b1, b2, b3, b4, b5;
	private int one,two,three,four,five;
	private Projectile testBullet;
	private static int player1HP = 5;
	private static int player2HP = 5;

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
		one = 0;//
		two = 0;//This block of code is for the counters that help us shoot the bullets
		three = 0;//
		four = 0;//
		five = 0;//
		container = this.getContentPane();
		shotsMade = 0;
		ps = new PlayerShip();
		hb = new HealthBar();
		reloadText = new ReloadText();
		bg = new Background();
		b1 = new Projectile(1000,1000);
		b2 = new Projectile(1000,1000);
		b3 = new Projectile(1000,1000);
		b4 = new Projectile(1000,1000);
		b5 = new Projectile(1000,1000);

		// testBullet = new Projectile(100, 300);
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
			b1.draw(g2d);
			b2.draw(g2d);
			b3.draw(g2d);
			b4.draw(g2d);
			b5.draw(g2d);
			reloadText.draw(g2d);

			bullet1.draw(g2d);
			bullet2.draw(g2d);
			bullet3.draw(g2d);
			bullet4.draw(g2d);
			bullet5.draw(g2d);
			

			// testBullet.draw(g2d);

			hb.draw(g2d);

			ps.draw(g2d);
			
		}
	}

	private class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
				
			double speed = 10;

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

			/*
			 * Collisions
			 */

			if (b1.isColliding(ps)) {
				ps.damageShip();
				hb.updateHP(ps.getHealth());
				b1.loadBullet(ps.getX(), 700);
				if (playerID == 1){
					player1HP--;
					System.out.println(player1HP + "health left for player 1");
				} else if(playerID == 2){
					player2HP--;
					System.out.println(player2HP + "health left for player 2");
				}
				// dc.repaint();
			}

			else if (b2.isColliding(ps)) {
				ps.damageShip();
				hb.updateHP(ps.getHealth());
				b2.loadBullet(ps.getX(), 700);
				if (playerID == 1){
					player1HP--;
					System.out.println(player1HP + "health left for player 1");
				} else if(playerID == 2){
					player2HP--;
					System.out.println(player2HP + "health left for player 2");
				}
				// dc.repaint();
			}

			else if (b3.isColliding(ps)) {
				ps.damageShip();
				hb.updateHP(ps.getHealth());
				b3.loadBullet(ps.getX(), 700);
				if (playerID == 1){
					player1HP--;
					System.out.println(player1HP + "health left for player 1");
				} else if(playerID == 2){
					player2HP--;
					System.out.println(player2HP + "health left for player 2");
				}
				// dc.repaint();
			}

			else if (b4.isColliding(ps)) {
				ps.damageShip();
				hb.updateHP(ps.getHealth());
				b4.loadBullet(ps.getX(), 700);
				if (playerID == 1){
					player1HP--;
					System.out.println(player1HP + "health left for player 1");
				} else if(playerID == 2){
					player2HP--;
					System.out.println(player2HP + "health left for player 2");
				}
				// dc.repaint();
			}

			else if (b5.isColliding(ps)) {
				ps.damageShip();
				hb.updateHP(ps.getHealth());
				b5.loadBullet(ps.getX(), 700);
				if (playerID == 1){
					player1HP--;
					System.out.println(player1HP + "health left for player 1");
				} else if(playerID == 2){
					player2HP--;
					System.out.println(player2HP + "health left for player 2");
				}
				// dc.repaint();
			}

			if (reload) {
				bullet1.loadBullet(690, 550);
				bullet2.loadBullet(730, 550);
				bullet3.loadBullet(770, 550);
				bullet4.loadBullet(810, 550);
				bullet5.loadBullet(850, 550);

				dc.repaint();
			}
			/*
			* Firing Animation
			*/
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
			/*
			* shooting animation
			*/
			if(shotCounter >= 1){
				
				if (one < 1){
					b1.loadBullet(shotCoordinates, -40);
					one++;
				}
				
				b1.shoot();
				dc.repaint();
				// if(b1.isOutOfFrame()){
				// 	one = 0;
				// }
			}
			if(shotCounter >= 2){
			
				if (two < 1){
					b2.loadBullet(shotCoordinates, -40);
					two++;
				}
				b2.shoot();
				dc.repaint();
				// if(b2.isOutOfFrame()){
				// 	two = 0;
				// }
			}
			if(shotCounter >= 3){
				
				if (three < 1){
					b3.loadBullet(shotCoordinates, -40);
					three++;
				}
				b3.shoot();
				dc.repaint();
				// if(b3.isOutOfFrame()){
				// 	three = 0;
				// }
			}
			if(shotCounter >= 4){
				
				if (four < 1){
					b4.loadBullet(shotCoordinates, -40);
					four++;
				}
				b4.shoot();
				dc.repaint();
				// if(b4.isOutOfFrame()){
				// 	four = 0;
				// }
			}
			if(shotCounter >= 5){
				
				if (five < 1){
					b5.loadBullet(shotCoordinates, -40);
					five++;
				}
				b5.shoot();
				dc.repaint();
				// if(b5.isOutOfFrame()){
				// 	five = 0;
				// }
			}

			if(player1HP == 0){
				
				if(playerID == 1){
					tm.stop();
					System.out.println("You Lose!");
				}
				if(playerID == 2){
					tm.stop();
					System.out.println("You Win!");
				}
			}
			if(player2HP == 0){
				
				if(playerID == 1){
					tm.stop();
					System.out.println("You Win!");
				}
				if(playerID == 2){
					tm.stop();
					System.out.println("You Lose!");
				}
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
                	Thread t = new Thread(csc);
                	t.start();
                	break;

                case KeyEvent.VK_CONTROL:
                	if (bulletsFired >= 5) {
	                	reload = true;
	                	bulletsFired = 0;	
                		reloadText.animate(bulletsFired);
                	}

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

	// private class ClientSideConnectionIn implements Runnable{
	// 	private Socket socket;
	// 	private DataInputStream dataIn;
	// 	private DataOutputStream dataOut;

	// 	public class ClientSideConnectionIn(){

	// 		try
	// 	}
	// }
	
	/**
	 * This private class is for the client side connection.
	 */
	/* I was thinking na
	If the bulletsFired is 1
	Then then it'll check if it's out of frame constantly
	Once outOfFrame is true
	It'll writeUTF to the server the string true*/
	private class ClientSideConnection implements Runnable {
			private Socket socket;
			private DataInputStream dataIn;
			private DataOutputStream dataOut;
			private Player p;
			
		public ClientSideConnection(Player x) {
			System.out.println("ClientSideConnectionMade");
			try {
				
				socket = new Socket("localhost", 1842);
				dataIn = new DataInputStream(socket.getInputStream());
				dataOut = new DataOutputStream(socket.getOutputStream());
				playerID = dataIn.readInt();
				System.out.println("Connected to server as player number" + playerID);	
				x.setUpGUI();	
				Thread t = new Thread(new ClientSideReader());
				t.start();
				
			}

			catch(IOException ex) {
				System.out.println("IOException from CSC Constructor of Player " + playerID);
			}
		}



		public void run(){
			try{
				// only works when there are bullets to fire.
				if(bulletsFired <= 5) {					
					// System.out.println("checkFireCalled" + " " + bulletsFired);
					if(bulletsFired == 1) {
						do{
							System.out.println("bullet1 not out of frame");
						}while(!bullet1.isOutOfFrame());
						if (bullet1.isOutOfFrame()) {
							
							System.out.println("bullet1 outofFrame");
							dataOut.writeInt(playerID);
							dataOut.writeDouble(900 - bullet1.getPositionX());
							dataOut.flush();
						}
					}
					else if(bulletsFired == 2){
						do{
							System.out.println("bullet2 not out of frame");
						}while(!bullet2.isOutOfFrame());							
						if (bullet2.isOutOfFrame()){
							System.out.println("bullet2 outofFrame");
							dataOut.writeInt(playerID);
							dataOut.writeDouble(900 - bullet2.getPositionX());
							dataOut.flush();

						}					
					}
					else if(bulletsFired == 3){
						do{
							System.out.println("bullet3 not out of frame");
						}while(!bullet3.isOutOfFrame());
						if (bullet3.isOutOfFrame()){
							System.out.println("bullet3 outofFrame");
							dataOut.writeInt(playerID);
							dataOut.writeDouble(900 - bullet3.getPositionX());
							dataOut.flush();
						}
					}
					else if(bulletsFired == 4){
						do{
							System.out.println("bullet4 not out of frame");
						}while(!bullet4.isOutOfFrame());
						if (bullet4.isOutOfFrame()){
							System.out.println("bullet4 outofFrame");
							dataOut.writeInt(playerID);
							dataOut.writeDouble(900 - bullet4.getPositionX());
							dataOut.flush();
						}
					}
					else if(bulletsFired == 5){
						do{
							System.out.println("bullet5 not out of frame");
						}while(!bullet5.isOutOfFrame());
						if (bullet5.isOutOfFrame()){
							System.out.println("bullet5 outofFrame");
							dataOut.writeInt(playerID);
							dataOut.writeDouble(900 - bullet5.getPositionX());
							dataOut.flush();
						}
					}					
				}
			}
			catch(IOException e){
				System.out.println("IOException in run() method from CSC of Player " + playerID);
			}
		}
		private class ClientSideReader implements Runnable{
				private DataInputStream dataI;
				
				private ClientSideReader(){
					dataI = dataIn;
					shotCounter = 0;
				}
				public void run(){
					try{
						while (true){
							shotCoordinates = dataIn.readDouble();
							System.out.println(shotCoordinates);
							if(shotCounter != 5){
								shotCounter++;
							} else{
								shotCounter = 0;
								shotCounter++;
								one = 0;
								two = 0;
								three = 0;
								four = 0;
								five = 0;
							}
						}
					}
					catch(IOException e){
							System.out.println("IOException from CSR run() method");
					}
				}
		}
			
	}

	public void connectToServer(){
		csc = new ClientSideConnection(this);	
	}
	public static void main(String[] args) {
		Player p = new Player(900, 650);
		// p.setUpGUI();
		p.connectToServer();
			
	}

}