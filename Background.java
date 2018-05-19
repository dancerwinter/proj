import java.awt.*;
import java.awt.geom.*;

public class Background {
	
	private Rectangle2D.Double playingField;

	/**
	 * This is the constructor for the Background class.
	 */
	public Background() {
		playingField = new Rectangle2D.Double(0, 0, 950, 650);
	}

	/**
	 * This method draws the background for the playing field and the status bar.
	 * @param g2d Graphics2D
	 */
	public void draw(Graphics2D g2d) {
		
		g2d.setColor(new Color(89, 89, 89));
		g2d.fill(playingField);
	}
}