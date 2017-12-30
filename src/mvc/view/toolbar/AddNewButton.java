package mvc.view.toolbar;

import java.awt.Dimension;

import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AddNewButton extends JButton {
	private static final long serialVersionUID = 3821107696744652502L;
	private static final Logger log = LoggerFactory.getLogger(AddNewButton.class);
	private static final String iconName = "toolbar_addnew.png";
	
	public AddNewButton(Dimension size) {
		log.info("Initialize add new button");
	}
}
