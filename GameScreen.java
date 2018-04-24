import java.awt.*;
import java.awt.geom.*;

public class GameScreen implements DrawingObject {

	private PlayerShip ps;
	private Rectangle2D.Double background;

	public GameScreen() {
		ps = new PlayerShip(Color.RED);
		background = new Rectangle2D.Double(0, 0, 900, 650);
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(new Color(30, 30, 30));
		g2d.fill(background);
		ps.draw(g2d);
	}

}