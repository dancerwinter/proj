import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// https://docs.oracle.com/javase/tutorial/uiswing/layout/flow.html
// https://docs.oracle.com/javase/tutorial/uiswing/components/textfield.html
// https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html
// https://docs.oracle.com/javase/7/docs/api/java/awt/Font.html

public class Menu extends JFrame{

	private String ip;
	private int width, height;
	private Container container;
	private JLabel label;
	private JTextField address;
	private JButton enter;
	private boolean visible;

	private MyButtonListener bl;
	
	public Menu() {
		width = 500;
		height = 130;

		ip = "";

		bl = new MyButtonListener();
		container = this.getContentPane();
		visible = true;
	}

	public void setUpGUI() {
		this.setPreferredSize(new Dimension(width, height));
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		container.setLayout(new FlowLayout());

		label = new JLabel("Connect to a Server");

		address = new JTextField("");
		address.setColumns(18);

		enter = new JButton("Enter");

		Font font = new Font("Impact", Font.PLAIN, 24);
		label.setFont(font);
		address.setFont(font);
		enter.setFont(font);

		container.add(label);
		container.add(address);
		container.add(enter);

		enter.addActionListener(bl);
		this.setVisible(true);
	}

	private class MyButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			ip = address.getText();
			dispose();
		}
	}

	public String getHost() {
		return ip;
	}

	public static void main (String[] args) {
		Menu menu = new Menu();
		menu.setUpGUI();
	}
}