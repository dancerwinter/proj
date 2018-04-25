import java.awt.*;
import java.awt.geom.*;

public class PlayerShip implements DrawingObject {

	private Rectangle2D.Double r;
	private Color color;
	private double x, y;

	public PlayerShip(Color c) {
		color = c;
		x = 450;
		y = 325;
	}

	@Override
	public void draw(Graphics2D g2d) {
		r = new Rectangle2D.Double(x, y, 50, 50);

		g2d.setColor(color);
		g2d.fill(r);
	}

	public void moveLeft(double speed) {
		x += speed;
	}

	public void moveRight(double speed) {
		x += speed;
	}

	public void moveUp(double speed) {
		y += speed;
	}

	public void moveDown(double speed) {
		y += speed;
	}
}