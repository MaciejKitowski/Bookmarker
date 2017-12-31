package mvc.view.toolbar.button;

import java.awt.GridLayout;

import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class CategoryPanel extends JPanel {
	private static final long serialVersionUID = -4079172705587516541L;
	private static final Logger log = LoggerFactory.getLogger(CategoryPanel.class);

	public CategoryPanel(boolean displayID) {
		log.info("Initialize category insert/edit panel");
		
		setLayout(new GridLayout(0, 1));
	}
}
