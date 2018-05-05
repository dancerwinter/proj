import java.awt.*;
import java.awt.geom.*;

public class Projectile implements Runnable {
	
	private Rectangle2D.Double r;
	private double x, y, width, height;
	private double speed;
	private double barrel;

	/**
	 * This is the constructor for the Projectile class.
	 * @param a the x coordinate
	 * @param b the y coordinate
	 */
	public Projectile(double a, double b) {
		x = a;
		y = b;
		width = 20;
		height = 40;
		speed = 5;
	}
	
	/**
	 * this method draws the projectile.
	 * @param g2d Graphics2D
	 */
	public void draw(Graphics2D g2d) {
		r = new Rectangle2D.Double(x, y, width, height);

		g2d.setColor(new Color(255, 102, 204));
		g2d.fill(r);
	}

	/**
	* this method is the method used to move the projectile from the top of the frame to the bottom.
	*/
	public void shoot() {
		if (y != 650){
			y += 25;
			this.isOutOfFrame();
		}
	}

	/**
	 * This method decrements the projectile's position.
	 */
	public void fireBullet() {
		if (y != -40) {
			y -= 25;
			this.isOutOfFrame();
		}
	}

	/**
	 * This method sets the location of the projectile.
	 * @param positionX the x starting position
	 * @param positionY the y starting position
	 */
	public void loadBullet(double positionX, double positionY) {

		x = positionX;
		y = positionY;
	}

	/**
	 * @param 
	 * @return a boolean if the projectile has collided.
	 */
	// public boolean hasCollision(PlayerShip ship) {
	// 	boolean collide = false;

	// 	// bottom of projectile vs top of ship
	// 	this.y + this.width == ship.getWidth() ||

	// 	// left

	// 	// right

	// 	return collide;
	// }

	/**
	 * This method returns the x coordinate of the projectile.
	 * @return this x position.
	 */
	public double getPositionX() {
		return x;
	}

	/**
	 * This method returns the y coordinate of the projectile.
	 * @return y coordinate of the projectile.
	 */
	public double getPositionY() {
		return y;
	}

	/**
	 * @return this is outOfFrame boolean.
	 */
	public boolean isOutOfFrame() {

		boolean outOfFrame = false;

		if(y <= -40 || y >= 650) {

			outOfFrame = true;
		}

		return outOfFrame;
	}

	/**
	 * This method is for the thread.
	 */
	public void run() {

	}
	
}