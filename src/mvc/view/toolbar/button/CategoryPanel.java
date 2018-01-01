package mvc.view.toolbar.button;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mvc.model.Category;

final class CategoryPanel extends JPanel {
	private static final long serialVersionUID = -4079172705587516541L;
	private static final Logger log = LoggerFactory.getLogger(CategoryPanel.class);
	
	private boolean displayID = false;
	private Category category = null;
	private JLabel idLabel = new JLabel("ID");
	private JLabel nameLabel = new JLabel("Name");
	private JTextField idField = new JTextField();
	private JTextField nameField = new JTextField();

	public CategoryPanel() {
		log.info("Initialize category panel");
		
		displayID = false;
		initializePanel();
	}
	
	public CategoryPanel(Category category) {
		log.info("Initialize category panel with values");
		
		displayID = true;
		this.category = category;
		initializePanel();
	}
	
	private void initializePanel() {
		log.debug("Initialize panel");
		
		setLayout(new GridLayout(0, 1));
		
		if(category != null) {
			idField.setText(String.valueOf(category.getID()));
			nameField.setText(category.getName());
		}
		
		if(displayID) {
			idField.setEnabled(false);
			add(idLabel);
			add(idField);
		}
		
		add(nameLabel);
		add(nameField);
	}
	
	public String getName() {
		return nameField.getText();
	}
}
