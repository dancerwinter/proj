import java.awt.*;
import java.awt.geom.*;

import java.util.*;

public class HealthBar {
	
	private double x, y1, y2, y3, y4, y5;
	private Rectangle2D.Double background;
	private Rectangle2D.Double bar1, bar2, bar3, bar4, bar5;

	public HealthBar() {
		x = 100;
		y1 = 550;		
		y2 = 550;		
		y3 = 550;		
		y4 = 550;		
		y5 = 550;		
	}

	public void draw(Graphics2D g2d) {
		background = new Rectangle2D.Double(100, 550, 400, 40);
		bar1 = new Rectangle2D.Double(x, y1, 80, 40);
		bar2 = new Rectangle2D.Double(x + 80, y2, 80, 40);
		bar3 = new Rectangle2D.Double(x + 160, y3, 80, 40);
		bar4 = new Rectangle2D.Double(x + 240, y4, 80, 40);
		bar5 = new Rectangle2D.Double(x + 320, y5, 80, 40);

		g2d.setColor(Color.RED);
		g2d.fill(background);

		g2d.setColor(Color.GREEN);
		g2d.fill(bar1);
		g2d.fill(bar2);
		g2d.fill(bar3);
		g2d.fill(bar4);
		g2d.fill(bar5);
	}

	public void updateHP(int i) {
		if (i == 5) {
			y1 = 550;		
			y2 = 550;		
			y3 = 550;		
			y4 = 550;		
			y5 = 550;
		}

		switch(i) {
			case 4:
				y5 = 650;
				break;
			case 3:
				y4 = 650;
				break;
			case 2:
				y3 = 650;
				break;
			case 1:
				y2 = 650;
				break;
			case 0:
				y1 = 650;
				break;
		}

	}
}

