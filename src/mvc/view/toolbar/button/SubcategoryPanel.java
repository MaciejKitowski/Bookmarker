package mvc.view.toolbar.button;

import java.awt.GridLayout;
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
	private JLabel idLabel = new JLabel("ID");
	private JLabel nameLabel = new JLabel("Name");
	private JLabel selectCategoryLabel = new JLabel("Select category");
	private JTextField idField = new JTextField();
	private JTextField nameField = new JTextField();
	private JComboBox<Category> selectCategoryField = null;

	public SubcategoryPanel() {
		log.info("Initialize subcategory panel");
		
		displayID = false;
		initializePanel();
	}
	
	public SubcategoryPanel(Subcategory subcategory) {
		log.info("Initialize subcategory panel with values");
		
		displayID = true;
		this.subcategory = subcategory;
		initializePanel();
	}
	
	private void initializePanel() {
		log.debug("Initialize panel");
		
		setLayout(new GridLayout(0, 1));
		
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
			idField.setEnabled(false);
			add(idLabel);
			add(idField);
		}
		
		add(selectCategoryLabel);
		add(selectCategoryField);
		add(nameLabel);
		add(nameField);
	}
	
	public String getName() {
		return nameField.getText();
	}
	
	public Category getCategory() {
		return (Category) selectCategoryField.getSelectedItem();
	}
}
