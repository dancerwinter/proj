import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;
import javax.swing.Timer;

import java.io.*;
import java.net.*;

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
 * This program is the Player class
 */
public class Player extends JFrame{
	
	private int width, height;
	private int bulletsFired, bulletsLeft, remainingHealth;
	private Container container;
	private DrawingComponent dc;
	private MyActionListener mal;
	private MyKeyListener mkl;
	private ClientSideConnection csc;
	// private ClientSideOutput cso;
	private int shotCounter;
	private PlayerShip ps;
	private Background bg;
	private HealthBar hb;
	private ReloadText reloadText;
	private EndGameText endGameText;
	private double shotCoordinates;
	private boolean isShot;
	private Projectile bullet1, bullet2, bullet3, bullet4, bullet5, b1, b2, b3, b4, b5;
	private int one,two,three,four,five;
	private int playerHP,enemyHP;
	private boolean up, down, left, right, spacebar, reload;
	private Timer tm;
	private int playerID;
	private int otherPlayer;
	private int shotsMade;
	private boolean isHit;
	private int playerHit;

	/**
	 * This is the constructor for the Player class.
	 * @param w This is the width of frame
	 * @param h This is the height of frame
	 */
	public Player(int w, int h) {
		width = w;
		height = h;

		/*
		 * This block of code is for the counters that help us shoot the bullets
		 */
		one = 0;
		two = 0;
		three = 0;
		four = 0;
		five = 0;
		/***/
		playerHP = 5;
		enemyHP = 5;
		container = this.getContentPane();

		shotsMade = 0;

		ps = new PlayerShip();
		hb = new HealthBar();
		reloadText = new ReloadText();
		bg = new Background();
		endGameText = new EndGameText();
		b1 = new Projectile(1000,1000);
		b2 = new Projectile(1000,1000);
		b3 = new Projectile(1000,1000);
		b4 = new Projectile(1000,1000);
		b5 = new Projectile(1000,1000);

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
/**
* This is our method to set up the JFrame of the game, this will only be called after player 2 has connected
*
*/
	public void setUpGUI() {
		this.setSize(width, height);
		this.setTitle("SPACE WARS" + playerID);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		container.setLayout(new BorderLayout());
		container.add(dc);

		// dc.revalidate();
		
		this.setVisible(true);
	}
/**
* Draws player sprites into the JFrame
*
*
*/
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

			hb.draw(g2d);
			reloadText.draw(g2d);
			endGameText.draw(g2d);
			bullet1.draw(g2d);
			bullet2.draw(g2d);
			bullet3.draw(g2d);
			bullet4.draw(g2d);
			bullet5.draw(g2d);		

			ps.draw(g2d);
			
		}
	}
/**
* handles animation, and constantly checks for win/lose conditions on both player instances. Timer refers to this
* note: shot is a bullet that came from the top of the frame, representing a bullet fired by the enemy player
* fire is the bullet that came from the player itself
*/
	private class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
				
			double speed = 10;
			/**
			* Movement animation
			*/
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

			/**
			 * Collision conditions
			 * if a shot collides with a player
			 *it will send to the server that it has been shot, server updates its data on how much health each players has, and sends to both players the health of both players
			 *also updates the HP bar drawing to show how much health is left
			 */
			if (b1.isColliding(ps)) {
				ps.damageShip();
				hb.updateHP(ps.getHealth());
				b1.loadBullet(ps.getX(), 700);
				isHit = true;
				Thread t = new Thread(csc);
                t.start();

			}

			else if (b2.isColliding(ps)) {
				ps.damageShip();
				hb.updateHP(ps.getHealth());
				b2.loadBullet(ps.getX(), 700);			
				isHit = true;
				Thread t = new Thread(csc);
                t.start();
				
			}

			else if (b3.isColliding(ps)) {
				ps.damageShip();
				hb.updateHP(ps.getHealth());
				b3.loadBullet(ps.getX(), 700);
				isHit = true;
				Thread t = new Thread(csc);
                t.start();
			}

			else if (b4.isColliding(ps)) {
				ps.damageShip();
				hb.updateHP(ps.getHealth());
				b4.loadBullet(ps.getX(), 700);
				isHit = true;
				Thread t = new Thread(csc);
                t.start();
			}

			else if (b5.isColliding(ps)) {
				ps.damageShip();
				hb.updateHP(ps.getHealth());
				b5.loadBullet(ps.getX(), 700);
				isHit = true;
				Thread t = new Thread(csc);
                t.start();
			}

			/**
			 * Reload animation
			 * can only be called when there are no more bullets to fire
			 */
			if (reload) {
				bullet1.loadBullet(690, 550);
				bullet2.loadBullet(730, 550);
				bullet3.loadBullet(770, 550);
				bullet4.loadBullet(810, 550);
				bullet5.loadBullet(850, 550);
				dc.repaint();
			}

			/**
			 * Firing Animation
			 * handles animation for each bullet whenever spacebar is pressed
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

			if (bulletsFired >= 5 && (playerHP != 0 || enemyHP != 0)) {
				bullet5.fireBullet();
				reloadText.animate(bulletsFired);
				dc.repaint();
			}

			/**
			 * shooting animation
			 * shots coming from the top of of the frame.
			 */
			if (shotCounter >= 1){
				
				if (one < 1){
					b1.loadBullet(shotCoordinates, -40);
					one++;
				}
				
				b1.shoot();
				dc.repaint();
			}

			if (shotCounter >= 2){
			
				if (two < 1){
					b2.loadBullet(shotCoordinates, -40);
					two++;
				}

				b2.shoot();
				dc.repaint();
			}

			if (shotCounter >= 3){
				
				if (three < 1){
					b3.loadBullet(shotCoordinates, -40);
					three++;
				}

				b3.shoot();
				dc.repaint();
			}

			if (shotCounter >= 4){
				
				if (four < 1){
					b4.loadBullet(shotCoordinates, -40);
					four++;
				}

				b4.shoot();
				dc.repaint();
			}

			if (shotCounter >= 5){
				
				if (five < 1){
					b5.loadBullet(shotCoordinates, -40);
					five++;
				}

				b5.shoot();
				dc.repaint();
			}

			/**
			 * Win lose conditions
			 * checks constantly if anyone has an HP of 0
			 */
			if(playerHP == 0 || enemyHP == 0){
				reloadText.animate(0);
				
				if(playerHP == 0){
					System.out.println("You Lose");
					endGameText.animate("lose");

				}
				else if(enemyHP == 0){
					System.out.println("You Win");
					endGameText.animate("win");
				}
				tm.stop();
			}
			

		}
	}

	/**
	 * This private class implements KeyListener.
	 * Allows player to use the keyboard to control the player sprite.
	 * 
	 */
	private class MyKeyListener implements KeyListener {

		public void keyTyped(KeyEvent ke) {

		}

		public void keyPressed(KeyEvent ke) {
			int keyCode = ke.getKeyCode();
			
			switch (keyCode) {
				/**
				*Movement keys
				*
				*/
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
                /**
                *
                *Shooting key
                */
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
                /**
                *Reload key
                *
                */
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

	
	
	/**
	 * This private class is for the client side connection.
	 * most of the server/client interaction happens here
	 */
	/* 
	If the bulletsFired is 1
	Then then it'll check if it's out of frame constantly
	Once outOfFrame is true
	It'll writeUTF to the server the string true*/
	private class ClientSideConnection implements Runnable {
			private Socket socket;
			private DataInputStream dataIn;
			private DataOutputStream dataOut;
			private Player p;
			private String ip;
		/**
		*Constructor
		*@param player x: to setupGUI() once player 2 has connected, String i: to be able to input needed IP address
		*
		*/
		public ClientSideConnection(Player x, String i) {
			System.out.println("ClientSideConnectionMade");
			try {
				ip = i;
				socket = new Socket(ip, 1842);
				dataIn = new DataInputStream(socket.getInputStream());
				dataOut = new DataOutputStream(socket.getOutputStream());
				playerID = dataIn.readInt();
				System.out.println("Connected to server as player number" + playerID);	
				waitStartMessage();
				x.setUpGUI();	
				Thread t = new Thread(new ClientSideReader());
				t.start();
			}

			catch(IOException ex) {
				System.out.println("IOException from CSC Constructor of Player " + playerID);
			}
		}
		/**
		*	Method to start the game only when player two has connected
		*/

		public void waitStartMessage(){
			try{
				String s = dataIn.readUTF();
			}catch (IOException e){

			}
		}
		@Override
		/**
		* run method
		* handles data transferring from client to server
		* thread is started once spacebar is pressed, ends when the bullet is out of frame.
		*/
		public void run(){
			try{
				if(bulletsFired <= 5) {					
					/**
					* connstantly checks if bullet is still within a frame, once out of the frame it will proceed to send to the server 900 - the x coordinate of the bullet when it leaves the frame, this is to simulate the two computers being in front of each other.
					*/
					if(bulletsFired == 1 && spacebar) {
						do{
							System.out.println("bullet1 not out of frame");
						}while(!bullet1.isOutOfFrame());
						if (bullet1.isOutOfFrame()) {
							System.out.println("bullet1 outofFrame");
							dataOut.writeInt(playerID);
							dataOut.writeDouble(900 - bullet1.getPositionX());
							dataOut.flush();
							dataOut.writeInt(0);
							dataOut.flush();
						}
					}
					else if(bulletsFired == 2 && spacebar){
						do{
							System.out.println("bullet2 not out of frame");
						}while(!bullet2.isOutOfFrame());							
						if (bullet2.isOutOfFrame()){
							System.out.println("bullet2 outofFrame");
							dataOut.writeInt(playerID);
							dataOut.writeDouble(900 - bullet2.getPositionX());
							dataOut.flush();
							dataOut.writeInt(0);
							dataOut.flush();

						}					
					}
					else if(bulletsFired == 3 && spacebar){
						do{
							System.out.println("bullet3 not out of frame");
						}while(!bullet3.isOutOfFrame());
						if (bullet3.isOutOfFrame()){
							System.out.println("bullet3 outofFrame");
							dataOut.writeInt(playerID);
							dataOut.writeDouble(900 - bullet3.getPositionX());
							dataOut.flush();
							dataOut.writeInt(0);
							dataOut.flush();
						}
					}
					else if(bulletsFired == 4 && spacebar){
						do{
							System.out.println("bullet4 not out of frame");
						}while(!bullet4.isOutOfFrame());
						if (bullet4.isOutOfFrame()){
							System.out.println("bullet4 outofFrame");
							dataOut.writeInt(playerID);
							dataOut.writeDouble(900 - bullet4.getPositionX());
							dataOut.flush();
							dataOut.writeInt(0);
							dataOut.flush();
						}
					}
					else if(bulletsFired == 5 && spacebar){
						do{
							System.out.println("bullet5 not out of frame");
						}while(!bullet5.isOutOfFrame());
						if (bullet5.isOutOfFrame()){
							System.out.println("bullet5 outofFrame");
							dataOut.writeInt(playerID);
							dataOut.writeDouble(900 - bullet5.getPositionX());
							dataOut.flush();
							dataOut.writeInt(0);
							dataOut.flush();
						}
					}
					/**
					* the thread is also called when the playersprite is hit by a shot
					* this will tell the server that the player is shot and it will update its own record of the players' healths
					*/
					if(isHit){
						System.out.println(playerID + "got hit!");
						dataOut.writeInt(0);
						dataOut.writeDouble(0);
						dataOut.flush();
						dataOut.writeInt(playerID);
						isHit = false;
					}					
				}
			}
			catch(IOException e){
				System.out.println("IOException in run() method from CSC of Player " + playerID);
			}
		}
		/**
		*
		* most of the server output goes through this inner inner class
		*/
		private class ClientSideReader implements Runnable{
				private DataInputStream dataI;
				/**
				*Constructor of the inner inner class
				*
				*/
				private ClientSideReader(){
					dataI = dataIn;
					shotCounter = 0;
				}

				/**
				* Run method is constantly happening as soon as the game starts
				*constantly checks if the player has been hit, or if there's a shot to be received from the server
				*/

				public void run(){
					try{
						while (true){
							shotCoordinates = dataIn.readDouble();
							playerHit = dataIn.readInt();
							System.out.println(shotCoordinates);
							System.out.println(playerHit);
							if(shotCounter != 5 && shotCoordinates != -1000.0){
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
							if(playerHit == playerID && playerHit != 0){
								playerHP--;
								System.out.println("Player " + playerID + " has " + playerHP + "left.");
								System.out.println("other player has " + enemyHP + "left.");

							}
							else if(playerHit != playerID && playerHit != 0){
								enemyHP--;
								System.out.println("Player " + playerID + " has " + playerHP + "left.");
								System.out.println("other player has " + enemyHP + "left.");
							}

						}
					}
					catch(IOException e){
							System.out.println("IOException from CSR run() method");
					}
				}
		}			
	}

	/**
	* connectToServer method: Instantiates the Client Side connection, which connects to the GameServer
	*@param String i: holds the IP address that comes from the Menu class
	*/

	public void connectToServer(String i){
		csc = new ClientSideConnection(this, i);	
	}

	/**
	* main method
	* runs the entire program
	* opens a menu JFrame which receives an IP Address, which will later on be sent to the connect to server method of Player.
	*/
	public static void main(String[] args) {
		Player p = new Player(900, 650);
		Menu m = new Menu();
		m.setUpGUI();
		String host = "";
		do{
			System.out.println("Please enter an IP Adress");
			host = m.getHost();
		}while(host == "");
		
		// p.setUpGUI();
		
		p.connectToServer(host);	
		
		// p.connectToServer(host);
			
	}

}