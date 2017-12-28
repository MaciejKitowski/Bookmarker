package mvc.view;

import java.awt.Color;
import java.awt.Dimension;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ToolbarPanel extends JToolBar {
	private static final long serialVersionUID = 1071937589387532800L;
	private static final Logger log = LoggerFactory.getLogger(ToolbarPanel.class);
	
	private final Dimension buttonSize;
	
	public ToolbarPanel(int width, int height) {
		log.info("Initialize toolbar");
		
		buttonSize = new Dimension(height - 5, height - 5);
		
		setBackground(Color.CYAN);
		setPreferredSize(new Dimension(width, height));
		setFloatable(false);
		setBorder(BorderFactory.createEmptyBorder());

		generateButton("toolbar_addnew.png", 10);
		generateButton("toolbar_edit.png", 5);
		generateButton("toolbar_delete.png", 5);
		generateButton("toolbar_database.png", 30);
		//generateButton("toolbar_settings.png");
	}
	
	private JButton generateButton(String iconName, int spaceBefore) {
		try {
			URL icon = getClass().getResource(String.format("/icons/%s", iconName));
			JButton button = new JButton(new ImageIcon(icon));
			
			button.setPreferredSize(buttonSize);
			button.setMaximumSize(buttonSize);
			button.setMinimumSize(buttonSize);
			button.setSize(buttonSize);
			
			add(Box.createHorizontalStrut(spaceBefore));
			add(button);
			
			return button;
		}
		catch(Exception ex) {
			log.error("Failed to generate button", ex);
			return null;
		}
	}
}
