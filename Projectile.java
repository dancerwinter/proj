import java.awt.*;
import java.awt.geom.*;

public class Projectile implements Runnable{
	
	private Rectangle2D.Double r;
	private double x, y;
	private double speed;
	private double barrel;
	private PlayerShip ps;


	/**
	 * This is the constructor for the Projectile class.
	 */
	public Projectile(double a, double b) {
		x = a;
		y = b;
		speed = 5;
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
	* this method is the method used to move the projectile from the top of the frame to the bottom.
	*/
	public void shoot(){
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
	 */
	public void loadBullet(double firingPositionX, double firingPositionY) {

		x = firingPositionX;
		y = firingPositionY;
	}

	/**
	 * This method returns the x coordinate of the projectile.
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



	public boolean isOutOfFrame() {

		boolean outOfFrame = false;

		if(y <= -40 || y >= 650) {

			outOfFrame = true;
		}

		return outOfFrame;
	}

	public void run() {

	}
	
}