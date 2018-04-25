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
		r = new Rectangle2D.Double(x, y, 85, 85);

		g2d.setColor(color);
		g2d.fill(r);
	}

	public void moveLeft(double speed) {
		x += speed;
		if(x <= 0) {
			x = 0;
		}
	}

	public void moveRight(double speed) {
		x += speed;
		if(x >= 800) {
			x = 800;
		}
	}

	public void moveUp(double speed) {
		y += speed;

		if (y <= 0) {
			y = 0;
		}
	}

	public void moveDown(double speed) {
		y += speed;

		if (y >= 525) {
			y = 525;
		}
	}
}
