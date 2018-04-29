import java.awt.*;
import java.awt.geom.*;

public class Projectile implements DrawingObject {
	
	private Rectangle2D.Double r;
	private double x, y;
	
	private double barrel;
	private PlayerShip ps;

	public Projectile(double a, double b) {
		x = a;
		y = b;
	}

	@Override
	public void draw(Graphics2D g2d) {
		r = new Rectangle2D.Double(x, y, 10, 34);

		g2d.setColor(new Color(255, 102, 204));
		g2d.fill(r);
	}

	public void fireBullet(double speed, double firingPositionX, double firingPositionY) {

		x = firingPositionX;
		
		if (y != -34) {
			y -= speed;
		}
	}

	public void reloadBullet(double xLocation, double yLocation) {
		x = xLocation;
		y = yLocation;
	}

	public double getPositionX() {
		return x;
	}

	public double getPositionY() {
		return y;
	}

	// Make a thread in the projectile
}