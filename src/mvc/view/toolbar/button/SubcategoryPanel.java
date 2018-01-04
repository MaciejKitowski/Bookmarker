package mvc.view.toolbar.button;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mvc.dao.DAOFactory;
import mvc.model.Category;
import mvc.model.Subcategory;

final class SubcategoryPanel extends JPanel {
	private static final long serialVersionUID = 1294392808012937642L;
	private static final Logger log = LoggerFactory.getLogger(SubcategoryPanel.class);
	
	private boolean displayID = false;
	private Subcategory subcategory = null;
	private GridBagConstraints constraints = new GridBagConstraints();
	private JLabel idLabel = new JLabel("ID");
	private JLabel nameLabel = new JLabel("Name");
	private JLabel selectCategoryLabel = new JLabel("Category");
	private JTextField idField = new JTextField();
	private JTextField nameField = new JTextField();
	private JComboBox<Category> selectCategoryField = null;

	public SubcategoryPanel() {
		log.info("Initialize subcategory panel");
		
		displayID = false;
		idField.setEnabled(false);
		initializePanel();
	}
	
	public SubcategoryPanel(Subcategory subcategory) {
		log.info("Initialize subcategory panel with values");
		
		displayID = true;
		idField.setEnabled(false);
		this.subcategory = subcategory;
		initializePanel();
	}
	
	private void initializePanel() {
		log.debug("Initialize panel");
		
		setLayout(new GridBagLayout());
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(0, 0, 5, 0);
		
		List<Category> categories = DAOFactory.get().getCategory().getAll();
		selectCategoryField = new JComboBox<>(categories.toArray(new Category[categories.size()]));
		
		if(subcategory != null) {
			idField.setText(String.valueOf(subcategory.getID()));
			nameField.setText(subcategory.getName());
			
			Category current = null;
			for(Category cat : categories) {
				if(subcategory.getParent().getID() == cat.getID()) {
					current = cat;
					break;
				}
			}
			
			selectCategoryField.setSelectedItem(current);
		}
		
		if(displayID) {
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.weightx = 0.05;
			add(idLabel, constraints);
			
			constraints.gridx = 1;
			constraints.gridy = 0;
			constraints.weightx = 0.95;
			add(idField, constraints);
		}
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 0.05;
		add(selectCategoryLabel, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.weightx = 0.95;
		add(selectCategoryField, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.weightx = 0.05;
		add(nameLabel, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.weightx = 0.95;
		add(nameField, constraints);
	}
	
	public String getName() {
		return nameField.getText();
	}
	
	public Category getCategory() {
		return (Category) selectCategoryField.getSelectedItem();
	}
}
