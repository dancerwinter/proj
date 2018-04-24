import java.awt.*;
import java.awt.geom.*;

public class StartMenu implements DrawingObject {
		
	private Rectangle2D.Double r;
	private String titleText, startText;

	public StartMenu() {
		r = new Rectangle2D.Double(0, 0, 900, 650);
		titleText = "S P A C E W A R S";
		startText = "PRESS ENTER";
	}

	@Override
	public void draw(Graphics2D g2d) {
		
		g2d.setColor(new Color(30, 30, 30));
		g2d.fill(r);

		g2d.setFont(new Font("Impact", Font.PLAIN, 110));
		g2d.setColor(new Color(255, 255, 255, 165));
		g2d.drawString(titleText, 100, 150);

		g2d.setFont(new Font("Impact", Font.PLAIN, 100));
		g2d.setColor(new Color(255, 255, 255, 165));
		g2d.drawString(startText, 100, 400);
	}
}