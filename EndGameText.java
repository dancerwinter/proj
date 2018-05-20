import java.awt.*;
import java.awt.geom.*;

public class EndGameText {
	
	private String text1, text2, textDisplayed;
	private int a, setTransparency;
	private double y, width, height;

	public EndGameText() {

		y = 550;
		width = 20;
		height = 40;

		text1 = "YOU WIN!";
		text2 = "YOU LOSE!";
		textDisplayed = "";

		a = 0;
		setTransparency = 15;
	}

	public void draw(Graphics2D g2d) {

	

		g2d.setFont(new Font("Consolas", Font.BOLD, 180));
		g2d.setColor(new Color(255, 255, 255, a));

		g2d.drawString(textDisplayed, (int)70, (int)350);
	}

	public void animate(String win) {

		if (win.equals("win")) {
			textDisplayed = text1;
		}else if(win.equals("lose")){
			textDisplayed = text2;
		}
		if (a < 255) {
				a = 255;
			}
		
			
	}
}