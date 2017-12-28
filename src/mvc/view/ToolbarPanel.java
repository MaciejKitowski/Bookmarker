package mvc.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ToolbarPanel extends JPanel {
	private static final long serialVersionUID = 1071937589387532800L;
	private static final Logger log = LoggerFactory.getLogger(ToolbarPanel.class);
	private static final int spaceBetweenComponent = 10;
	
	private final Dimension buttonSize;
	
	public ToolbarPanel(int width, int height) {
		log.info("Initialize toolbar");
		
		setBackground(Color.CYAN);
		setPreferredSize(new Dimension(width, height));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		buttonSize = new Dimension(height - 10, height - 10);
		
		generateButton("toolbar_addnew.png");
	}
	
	private JButton generateButton(String iconName) {
		try {
			URL icon = getClass().getResource(String.format("/icons/%s", iconName));
			JButton button = new JButton(new ImageIcon(icon));
			
			button.setPreferredSize(buttonSize);
			button.setMaximumSize(buttonSize);
			button.setMinimumSize(buttonSize);
			button.setSize(buttonSize);
			
			add(Box.createHorizontalStrut(spaceBetweenComponent));
			add(button);
			
			return button;
		}
		catch(Exception ex) {
			log.error("Failed to generate button", ex);
			return null;
		}
	}
}
