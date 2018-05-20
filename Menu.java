import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
		height = 175;

		ip = "";

		bl = new MyButtonListener();
		container = this.getContentPane();
		visible = true;
	}

	public void setUpGUI() {
		this.setPreferredSize(new Dimension(width, height));
		this.pack();
		this.setTitle("Input Box");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		label = new JLabel("Input the IP Address");
		address = new JTextField("");
		enter = new JButton("Okay");

		address.setColumns(12);

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

	
}