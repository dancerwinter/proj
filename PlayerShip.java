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

	public void moveLeft(int speed) {
		x += speed;
		if(x<=0)
		{
			x = 0;
		}
	}

	public void moveRight(int speed) {
		x += speed;
		if(x>=835)
		{
			x = 835;
		}
	}

	public void moveUp(int speed) {
		y += speed;
	}

	public void moveDown(int speed) {
		y += speed;
	}
}
