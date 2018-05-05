import java.awt.*;
import java.awt.geom.*;

public class Projectile implements Runnable{
	
	private Rectangle2D.Double r;
	private double x, y;
	private double speed;
	private double barrel;
	private PlayerShip ps;
	private boolean isNotFired;

	/**
	 * This is the constructor for the Projectile class.
	 */
	public Projectile(double a, double b) {
		x = a;
		y = b;
		speed = 5;
		isNotFired = true;
	}
	
	/**
	 * this method draws the projectile.
	 */
	public void draw(Graphics2D g2d) {
		r = new Rectangle2D.Double(x, y, 20, 40);

		g2d.setColor(new Color(255, 102, 204));
		g2d.fill(r);
	}

	/**
	 * This method decrements the projectile's position.
	 */
	public void fireBullet() {
		if (y >= -40) {
			y -= 25;	
		}
	}

	/**
	 * This method sets the location of the projectile.
	 * @param firingPositionX the x starting position
	 * @param firingPositionY the y starting position
	 */
	public void loadBullet(double firingPositionX, double firingPositionY) {

		x = firingPositionX;
		y = firingPositionY;
	}

	/**
	 * This method returns the x coordinate of the projectile.
	 * @return this x position.
	 */
	public double getPositionX() {
		return x;
	}

	/**
	 * This method returns the y coordinate of the projectile.
	 */
	public double getPositionY() {
		return y;
	}

	public void reload() {
		isNotFired = true;
	}

	public boolean isOutOfFrame() {

		boolean outOfFrame = false;

		if(y <= -40) {

			outOfFrame = true;
		}

		return outOfFrame;
	}

	public void run() {

	}
	
}