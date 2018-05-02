import java.awt.*;
import java.awt.geom.*;

public class Projectile implements DrawingObject{
	
	private Rectangle2D.Double r;
	private double x, y;
	private double speed;
	private double barrel;
	private PlayerShip ps;

	public Projectile(double a, double b) {
		x = a;
		y = b;
		speed = 5;
	}

	@Override
	public void draw(Graphics2D g2d) {
		r = new Rectangle2D.Double(x, y, 20, 40);

		g2d.setColor(new Color(255, 102, 204));
		g2d.fill(r);
	}

	public void fireBullet() {	
		y -= 5;	
	}

	public void loadBullet(double firingPositionX, double firingPositionY) {

		x = firingPositionX - 9;
		y = firingPositionY;
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

	public boolean isOutOfFrame() {
		boolean outOfFrame = false;

		if(y <= -40) {
			outOfFrame = true;
		}

		return outOfFrame;
	}
}