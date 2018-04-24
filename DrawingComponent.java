import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.ArrayList;

public class DrawingComponent extends JComponent{

	ArrayList<DrawingObject> objects = new ArrayList<DrawingObject>();

	public DrawingComponent() {
		// objects.add(new InstructionMenu());
		objects.add(new GameScreen());
	}

	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHints(rh);

		for (int i = 0; i < objects.size(); i++){
			objects.get(i).draw(g2d);
		}
	}
}