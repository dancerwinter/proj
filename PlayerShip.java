import java.awt.*;
import java.awt.geom.*;

public class PlayerShip implements DrawingObject {

	private Path2D.Double p;
	private Color color;
	private int x;

	public PlayerShip(Color c) {
		p = new Path2D.Double();
		color = c;
		x = 450;
	}

	@Override
	public void draw(Graphics2D g2d) {
		p.moveTo(x, 325);
		p.lineTo(x + 20, 365);
		p.lineTo(x - 20, 365);
		p.lineTo(x, 325);

		g2d.setColor(color);
		g2d.fill(p);
	}

	public void moveLeft(int speed) {
		x -= speed;
	}

	public void moveRight(int speed) {
		x += speed;
	}
}