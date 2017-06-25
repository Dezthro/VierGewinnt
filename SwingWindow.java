import java.awt.*;
import javax.swing.*;

/**
 * a class which creates the view
 * 
 * @author Marius Bonke 4839983 Gruppe 3b
 * @author Lucas Schröder 4809832 Gruppe 3b
 */
public class SwingWindow {

	private JFrame window;
	private String nameA;
	private String nameB;
	private int count;
	
	private GridBagLayout layout;
	private GridBagConstraints dimensions;
	
	private JLabel namePlayerA;
	private JLabel namePlayerB;
	private JPanel gameField;
	private JPanel score;
	private JPanel scorePlayerA;
	private JPanel scorePlayerB;
	
	// constructor
	public SwingWindow() {
	
		this.prepareWindow();
	
	}
	
	public void prepareWindow() {
		
		count = 1;
		
		//Window
		window = new JFrame();
		window.setTitle("Vier Gewinnt");
		window.setLocation(160, 90);
		window.setSize(1600, 900);
		window.setBackground(Color.white);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(layout);
		
		// Layout
		layout = new GridBagLayout();
		dimensions = new GridBagConstraints();
		dimensions.fill = GridBagConstraints.BOTH;
		dimensions.anchor = GridBagConstraints.CENTER;
		dimensions.weightx = 0.2;
		dimensions.weighty = 0.2;
		dimensions.gridx = 0;
		dimensions.gridy = 0;
		this.makeLabel(namePlayerA, Color.red, nameA, layout, dimensions);
		dimensions.weightx = 0.2;
		dimensions.gridx = 2;
		dimensions.gridy = 0;
		this.makeLabel(namePlayerB, Color.blue, nameB,  layout, dimensions);
		dimensions.weightx = 1.0;
		dimensions.gridx = 1;
		dimensions.gridy = 0;
		this.makePanel(score, Color.black, layout, dimensions);
		dimensions.weightx = 1.0;
		dimensions.weighty = 1.0;
		dimensions.gridx = 1;
		dimensions.gridy = 1;
		this.makePanel(gameField, Color.darkGray, layout, dimensions);
		dimensions.weightx = 0.2;
		dimensions.weighty = 1.0;
		dimensions.gridx = 0;
		dimensions.gridy = 1; 
		this.makePanel(scorePlayerA, Color.green, layout, dimensions);
		dimensions.weightx = 0.2;
		dimensions.weighty = 1.0;
		dimensions.gridx = 2;
		dimensions.gridy = 1; 
		this.makePanel(scorePlayerB, Color.magenta, layout, dimensions);
		dimensions.weightx = 1.0;
		dimensions.weighty = 0.2;
		dimensions.gridx = 1;
		dimensions.gridy = 2;
		
		// window-visibility
		window.setVisible(true);
		
	}
	
	public void makePanel (JPanel panel, Color color, GridBagLayout gridbag, GridBagConstraints c) {
		panel = new JPanel();
		gridbag.setConstraints(panel, c);
		panel.setBackground(color);
		window.add(panel);
	}
	
	public void makeLabel (JLabel label, Color color, String name, GridBagLayout gridbag, GridBagConstraints c) {
			label = new JLabel();
			if (name == null) {
				name = "Player " + (char)(count + 64); 
			}
			label.setText(name);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 64));
			gridbag.setConstraints(label, c);
			label.setForeground(color);
			label.setBackground(new Color(255, 255, 255));
			label.setOpaque(true);
			window.add(label);
			count++;
} 
}
