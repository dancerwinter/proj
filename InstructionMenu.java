import java.awt.*;
import java.awt.geom.*;

public class InstructionMenu implements DrawingObject {

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