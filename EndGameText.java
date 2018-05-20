import java.awt.*;
import java.awt.geom.*;
/**
 * @author Neil Daniel B. Bautista, Jessica Anne M. Manzano
 * @IDNumber 170252, 171429
 * @version April 24, 2018
 * Bautista, Neil Daniel - 170252
 *
 *We have not discussed the Java language code 
 *in our program with anyone
 *other than our instructor or the teaching
 *assistants assigned to this course.
 *
 *We have not used Java language code 
 *obtained from another student, or
 *any other unauthorized source, either 
 *modified or unmodified.
 *
 *If any Java language
 *code or documentation used in our program was
 *obtained from another source, such as a text
 *book or course notes, those have been clearly
 *noted with a proper citation in the 
 *comments of our code. 
 *
 * This program is the end game text class
 */
public class EndGameText {
	
	private String text1, text2, textDisplayed;
	private int a, setTransparency;
	private double y, width, height;
	/**
	* EndGameText constructor
	* EndGameText is a drawString that will appear once a player has 0 health
	* text that will show depends on who had the 0 health
	*
	*/

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
	/**
	* draw method: does all the drawing needed for the JFrame
	*@param Graphics2D g2d: allows the method to draw the needed String
	*
	*/
	public void draw(Graphics2D g2d) {
		g2d.setFont(new Font("Consolas", Font.BOLD, 180));
		g2d.setColor(new Color(255, 255, 255, a));
		g2d.drawString(textDisplayed, (int)70, (int)350);
	}
	/**
	*Animate method
	*sets the text to be drawn when a player reaches 0 health
	*@param String win: holds a string either "win" or "lose" which determines which text will be used to be drawn
	*sets transparency of the text to 255
	*/
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