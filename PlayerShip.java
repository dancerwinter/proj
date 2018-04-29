import java.awt.*;
import java.awt.geom.*;

public class PlayerShip implements DrawingObject {

	private Rectangle2D.Double r;
	private Color color;
	private double x, y;
	private double width, height;

	public PlayerShip(Color c) {
		color = c;
		x = 450;
		y = 325;
		width = 80;
		height = 80;
	}

	@Override
	public void draw(Graphics2D g2d) {
		r = new Rectangle2D.Double(x, y, width, height);

		g2d.setColor(color);
		g2d.fill(r);
	}

	public double getPositionX() {
		double position = x + (width / 2);
		return position;
	}

	public double getPositionY() {
		double position = y + (height / 2);
		return position;
	}

	public void moveLeft(double speed) {
		x += speed;
		
		if (x <= 0) {
			x = 0;
		}
	}
	
	public void moveRight(double speed) {
		x += speed;

		if (x >= 800) {
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

		if (y >= 524) {
			y = 524;
		}
	}
}
