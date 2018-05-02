import java.awt.*;
import java.awt.geom.*;

public class Background implements DrawingObject {
	
	Rectangle2D.Double r;

	public Background() {
		r = new Rectangle2D.Double(0, 0, 950, 650);
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(new Color(89, 89, 89));
		g2d.fill(r);
	}
}