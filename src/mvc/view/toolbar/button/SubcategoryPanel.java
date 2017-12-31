package mvc.view.toolbar.button;

import java.awt.GridLayout;

import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mvc.model.Subcategory;

final class SubcategoryPanel extends JPanel {
	private static final long serialVersionUID = 1294392808012937642L;
	private static final Logger log = LoggerFactory.getLogger(SubcategoryPanel.class);
	
	private boolean displayID = false;
	private Subcategory subcategory = null;

	public SubcategoryPanel() {
		log.info("Initialize subcategory panel");
		
		displayID = false;
		initializePanel();
	}
	
	private void initializePanel() {
		log.debug("Initialize panel");
		
		setLayout(new GridLayout(0, 1));
	}
}
