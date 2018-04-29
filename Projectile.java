import java.awt.*;
import java.awt.geom.*;

public class Projectile implements Runnable{
	
	private Rectangle2D.Double r;
	private double x, y;
	
	private double barrel;
	private PlayerShip ps;

	public Projectile(double a, double b) {
		x = a;
		y = b;
	}

	public void draw(Graphics2D g2d) {
		r = new Rectangle2D.Double(x, y, 20, 34);

		g2d.setColor(new Color(255, 102, 204));
		g2d.fill(r);
	}

	public void run() {
		try {
			
		} 

		catch (InterruptedException e) {}
	}

	public void fireBullet(double speed, double firingPositionX, double firingPositionY) {

		x = firingPositionX - 1;
		
		// if (y != -34) {
		// 	y -= speed;
		// }

		if (y < -36) {
			y = firingPositionY;
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
}