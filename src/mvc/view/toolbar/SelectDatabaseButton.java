package mvc.view.toolbar;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SelectDatabaseButton extends JButton implements ActionListener {
	private static final long serialVersionUID = -3802417046411311406L;
	private static final Logger log = LoggerFactory.getLogger(SelectDatabaseButton.class);
	private static final String iconName = "toolbar_database.png";
	
	public SelectDatabaseButton(Dimension size) {
		log.info("Create select database button");
		
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
		
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		log.debug("Pressed button");
	}
}
