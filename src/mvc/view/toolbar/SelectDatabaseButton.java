package mvc.view.toolbar;

import java.awt.Dimension;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SelectDatabaseButton extends JButton {
	private static final long serialVersionUID = -3802417046411311406L;
	private static final Logger log = LoggerFactory.getLogger(SelectDatabaseButton.class);
	private static final String iconName = "toolbar_database.png";
	
	public SelectDatabaseButton(Dimension size) {
		log.info("Create select database button");
		
		setSize(size);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		
		try {
			log.debug("Set icon");
			
			URL icon = getClass().getResource(String.format("/icons/%s", iconName));
			setIcon(new ImageIcon(icon));
		}
		catch(Exception ex) {
			log.error("Failed to set icon", ex);
		}
	}
}
