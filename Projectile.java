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
	 * This method returns a boolean whether or not there was collision between the Projectile and the PlayerShip.
	 * @param ship a PlayShip class
	 * @return a boolean if the projectile has collided.
	 */
	public boolean isColliding(PlayerShip ship) {
		/* The collision boolean arguments are as follows:
		 * bottom of the projectile vs top of the ship
		 * left of the projectile vs right of the ship
		 * right of the projectile vs left of the ship
		 * top of the projectile vs bottom of the ship
		 */
		boolean collide = this.y + this.height <= ship.getY() || this.x >= ship.getX() + ship.getWidth() || this.x + this.width <= ship.getX() || this.y >= ship.getY() + ship.getHeight();

		return !collide;
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