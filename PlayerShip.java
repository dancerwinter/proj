import java.awt.*;
import java.awt.geom.*;

public class PlayerShip implements DrawingObject {

	private Rectangle2D.Double r;
	private Color color;
	private double x, y;
	private double width, height;
	private int health;
	private Projectile bullet;

	public PlayerShip() {
		x = 450;
		y = 325;
		width = 80;
		height = 80;
		health = 5;

		bullet = new Projectile();

	}

	@Override
	public void draw(Graphics2D g2d) {
		r = new Rectangle2D.Double(x, y, width, height);

		
		g2d.setColor(Color.RED);
		g2d.fill(r);
	}

	public void fire() {
		// bullet = new Projectile(this.getPositionX(), this.getPositionY());

		// bullet.loadBullet(this.getPositionX(), this.getPositionY());
		bullet.fireBullet();
	}

	public double getPositionX() {
		double position = x + (width / 2);
		return position;
	}

	public double getPositionY() {
		double position = y + (height / 2);
		return position;
	}

	public int getHealth() {
		return health;
	}

	public void damageShip() {
		health--;
	}

	public void moveLeft(double speed) {
		x += speed;
		
		if (x <= 0) {
			x = 0;
		}
	}
	
	public void moveRight(double speed) {
		x += speed;

		if (x >= 805) {
			x = 805;
		}
	}

	public void moveUp(double speed) {
		y += speed;

		if (y <= 0) {
			y = 0;
		}
	}

	public void moveDown(double speed) {
		y += speed;

		if (y >= 459) {
			y = 459;
		}
	}

	private class Projectile implements DrawingObject {

		private Rectangle2D.Double r;
		private double x, y;

		public Projectile() {
			
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

			x = firingPositionX;
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
}
