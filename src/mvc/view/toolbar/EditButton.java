package mvc.view.toolbar;

import java.awt.Dimension;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class EditButton extends JButton {
	private static final long serialVersionUID = 2452055567420326318L;
	private static final Logger log = LoggerFactory.getLogger(EditButton.class);
	private static final String iconName = "toolbar_edit.png";
	
	public EditButton(Dimension size) {
		log.info("Initialize edit button");
		
		initializeSize(size);
		initializeIcon();
	}
	
	private void initializeSize(Dimension size) {
		log.debug("Set size: {}", size.toString());
		
		setSize(size);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
	}
	
	private void initializeIcon() {
		log.debug("Set icon");
		
		try {
			URL icon = getClass().getResource(String.format("/icons/%s", iconName));
			setIcon(new ImageIcon(icon));
		}
		catch(Exception ex) {
			log.error("Failed to set icon", ex);
		}
	}
}
