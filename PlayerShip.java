import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
public class PlayerShip implements DrawingObject {

	private Rectangle2D.Double r;
	private Color color;
	private double x, y;
	private double width, height;
	private int health;
	private Projectile bullet1,bullet2,bullet3,bullet4,bullet5;
	private Projectile[] ammo;
	

	public PlayerShip() {
		x = 450;
		y = 325;
		width = 80;
		height = 80;
		health = 5;
		ammo = new Projectile[5];
		bullet1 = new Projectile(100,500);
		bullet2 = new Projectile(150,500);
		bullet3 = new Projectile(200,500);
		bullet4 = new Projectile(250,500);
		bullet5 = new Projectile(300,500);
		

		ammo[0] = bullet1;
		ammo[1] = bullet2;
		ammo[2] = bullet3;
		ammo[3] = bullet4;
		ammo[4] = bullet5;
		
	}

	@Override
	public void draw(Graphics2D g2d) {
		for (int i = 0; i<5;i++)
		{
			ammo[i].draw(g2d);
		}
		r = new Rectangle2D.Double(x, y, width, height);
		g2d.setColor(new Color(0,0,0,100));
		g2d.fill(r);
		
		
	}
	public void loadBullet(){

	}
	public void fire() {

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
}
