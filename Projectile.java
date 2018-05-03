import java.awt.*;
import java.awt.geom.*;

public class Projectile implements Runnable{
	
	private Rectangle2D.Double r;
	private double x, y;
	private double speed;
	private double barrel;
	private PlayerShip ps;
	private boolean isNotFired;

	public Projectile(double a, double b) {
		x = a;
		y = b;
		speed = 5;
		isNotFired = true;
	}
	
	public void draw(Graphics2D g2d) {
		r = new Rectangle2D.Double(x, y, 20, 40);

		g2d.setColor(new Color(255, 102, 204));
		g2d.fill(r);
	}

	// increments bullets
	public void fireBullet() {
		
		if (y != -1000) {
			y -= 25;	
		}
		// isNotFired = false;
	}

	public void loadBullet(double firingPositionX, double firingPositionY) {

		x = firingPositionX;
		y = firingPositionY;
	}

	public double getPositionX() {
		return x;
	}

	public void reload()
	{
		isNotFired = true;
	}

	public double getPositionY() {
		return y;
	}

	public boolean isOutOfFrame() {
		boolean outOfFrame = false;

		if(y <= -40) {
			outOfFrame = true;
		}

		return outOfFrame;
	}
	public void run()
	{

	}
	
}