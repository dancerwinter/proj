import java.awt.*;
import java.awt.geom.*;

public class Projectile implements DrawingObject {
	
	private Rectangle2D.Double r;
	private double x, y;
	private double speed;
	private double barrel;
	private PlayerShip ps;

	public Projectile() {
		x = 490;
		y = 365;
	}

	@Override
	public void draw(Graphics2D g2d) {
		r = new Rectangle2D.Double(x, y, 10, 35);

		g2d.setColor(new Color(255, 102, 204));
		g2d.fill(r);
	}

	public void fireBullet(double s) {

		System.out.println("Bullet fired from Projectile");

		if (y != 0) {
			y--;
		}
	}

	// Make a thread in the projectile
}