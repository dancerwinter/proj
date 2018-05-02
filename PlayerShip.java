import java.awt.*;
import java.awt.geom.*;

import java.util.ArrayList;

public class PlayerShip {


	private Rectangle2D.Double r;
	private Color color;
	private double x, y;
	private double width, height;
	private int health;
	private Projectile bullet;
	private Projectile[] ammo;

	public PlayerShip() {
		x = 450;
		y = 325;
		width = 80;
		height = 80;
		health = 5;
		ammo = new Projectile[5];
		for (int i = 0; i<5 ; i++)
		{
			ammo[i] = new Projectile(100+(i*10),500);

		}
	}

	public void draw(Graphics2D g2d) {
		r = new Rectangle2D.Double(x, y, width, height);

		g2d.setColor(Color.RED);
		g2d.fill(r);

		for(int i = 0; i<5; i++)

		{
			ammo[i].draw(g2d);
		}
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
