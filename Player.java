import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.ArrayList;

import java.io.*;
import java.net.*;

/**
 * @author Neil Daniel B. Bautista, Jessica Anne M. Manzano
 * @IDNumber 17, 171429
 * @version April 24, 2018
 *
 */

public class Player extends JFrame {
	
	private int width, height;
	private Container container;
	private DrawingComponent dc;
	private MyKeyListener mkl;

	/**
	 *
	 */
	public Player(int w, int h) {
		width = w;
		height = h;
		container = this.getContentPane();
		dc = new DrawingComponent();
	}

	public void setUpGUI() {
		this.setSize(width, height);
		this.setTitle("SPACE WARS");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		container.setLayout(new BorderLayout());

		container.add(dc);

		this.setVisible(true);
	}

	private class DrawingComponent extends JComponent{

		ArrayList<DrawingObject> objects = new ArrayList<DrawingObject>();

		public DrawingComponent() {
			objects.add(new InstructionMenu());
			objects.add(new GameScreen());
		}

		protected void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setRenderingHints(rh);

			for (int i = 0; i < objects.size(); i++){
				objects.get(i).draw(g2d);
			}
		}
	}

	public interface DrawingObject {
		void draw(Graphics2D g2d);
	}

	private class GameScreen implements DrawingObject {

		private Path2D.Double p;

		public GameScreen() {

		}

		@Override
		public void draw(Graphics2D g2d) {

		}

	}

	private class MyKeyListener implements KeyListener {

		public void KeyPressed(KeyEvent ke) {
			int keyCode = ke.getKeyCode();

			switch(keyCode) {
				case KeyEvent.VK_UP : System.out.println("^"); break;
                case KeyEvent.VK_DOWN : System.out.println("V"); break;
                case KeyEvent.VK_LEFT : System.out.println("<"); break;
                case KeyEvent.VK_RIGHT : System.out.println(">"); break;
                default : System.out.println("Other key was pressed"); break;
			}
		}

		public void KeyTyped(KeyEvent ke) {

		}

		public void keyReleased(KeyEvent ke) {
			System.out.println("Key has been released");
		}
	}

	private class InstructionMenu implements DrawingObject {

		private Rectangle2D.Double background, foreground;
		private Rectangle2D.Double healthBarBackground, healthBarCounter;
		private Rectangle2D.Double ammoBackground, ammoCounter;
		private String healthBarText, ammoText, shipText; 

		public InstructionMenu() {
			
			foreground = new Rectangle2D.Double(0, 0, 900, 650);
			background = new Rectangle2D.Double(0, 0, 900, 650);

			healthBarText = "This is your health bar. The game ends when you reach 0.";
			ammoText = "This is the amount of ammo you have.";
			shipText = "This is you!";

			healthBarBackground = new Rectangle2D.Double(200, 570, 500, 30);
			healthBarCounter = new Rectangle2D.Double(205, 575, 490, 20);

			ammoBackground = new Rectangle2D.Double(800, 200, 30, 200);
		}

		@Override
		public void draw(Graphics2D g2d) {
			g2d.setColor(new Color(100, 100, 100));
			g2d.fill(background);

			{ // The objects in the game itself

				g2d.setColor(new Color(204, 204, 204));
				g2d.fill(healthBarBackground);
				g2d.fill(ammoBackground);

				g2d.setColor(new Color(0, 230, 0));
				g2d.fill(healthBarCounter);

			}

			g2d.setColor(new Color(0, 0, 0, 180));
			g2d.fill(foreground);

			g2d.setFont(new Font("Impact", Font.PLAIN, 25));
			g2d.setColor(new Color(255, 255, 255));
			
			g2d.drawString(healthBarText, 150, 530);
			g2d.drawString(ammoText, 400, 325);
			g2d.drawString(shipText, 200, 450);
		}
	}

	public static void main (String[] args) {
		Player p = new Player(900, 650);
		p.setUpGUI();
	}
}