import java.awt.*;
import java.awt.geom.*;

public class ReloadText {
	
	private String text1, text2;
	private int a, setTransparency;
	private Rectangle2D.Double bullet1, bullet2, bullet3, bullet4, bullet5;
	private double y, width, height;

	public ReloadText() {

		y = 550;
		width = 20;
		height = 40;

		bullet1 = new Rectangle2D.Double(690, y, width, height);
		bullet2 = new Rectangle2D.Double(730, y, width, height);
		bullet3 = new Rectangle2D.Double(770, y, width, height);
		bullet4 = new Rectangle2D.Double(810, y, width, height);
		bullet5 = new Rectangle2D.Double(850, y, width, height);

		text1 = "RELOAD";
		text2 = "AMMO";
		a = 0;
		setTransparency = 15;
	}

	public void draw(Graphics2D g2d) {

		g2d.setColor(new Color(40, 40, 40, 255));

		g2d.fill(bullet1);
		g2d.fill(bullet2);
		g2d.fill(bullet3);
		g2d.fill(bullet4);
		g2d.fill(bullet5);

		g2d.setFont(new Font("Consolas", Font.BOLD, 48));
		g2d.setColor(new Color(255, 255, 255));

		g2d.drawString(text2, (int)545, (int)585);

		g2d.setFont(new Font("Consolas", Font.BOLD, 100));
		g2d.setColor(new Color(255, 255, 255, a));

		g2d.drawString(text1, (int)285, (int)350);
	}

	public void animate(int i) {

		if (i >= 5) {
			if (a < 255) {
				a += setTransparency;
			}
		}
		
		else if (i < 5) {
			a = 0;
		}		
	}
}