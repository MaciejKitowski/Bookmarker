package mvc.view.toolbar.button;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mvc.dao.DAOFactory;
import mvc.model.Subcategory;
import mvc.model.Url;

//TODO Group subcategories using unselectable categories
final class UrlPanel extends JPanel {
	private static final long serialVersionUID = -7033793384872165623L;
	private static final Logger log = LoggerFactory.getLogger(UrlPanel.class);
	
	private boolean displayID = false;
	private Url url = null;
	private GridBagConstraints constraints = new GridBagConstraints();
	private JLabel idLabel = new JLabel("ID");
	private JLabel titleLabel = new JLabel("Title");
	private JLabel urlLabel = new JLabel("Url");
	private JLabel descriptionLabel = new JLabel("Description");
	private JLabel selectSubcategoryLabel = new JLabel("Subcategory");
	private JTextField idField = new JTextField();
	private JTextField titleField = new JTextField();
	private JTextField urlField = new JTextField();
	private JTextArea descriptionField = new JTextArea(4, 20);
	private JComboBox<Subcategory> selectSubcategoryField = null;
	
	public UrlPanel() {
		log.info("Initialize url panel");
		
		displayID = false;
		idField.setEnabled(false);
		initializePanel();
	}
	
	public UrlPanel(Url url) {
		log.info("Initialize url panel with values");
		
		displayID = true;
		this.url = url;
		idField.setEnabled(false);
		initializePanel();
	}
	
	private void initializePanel() {
		log.debug("Initialize panel");
		
		setLayout(new GridBagLayout());
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(0, 0, 5, 0);
		
		List<Subcategory> subcategories = DAOFactory.get().getSubcategory().getAll();
		selectSubcategoryField = new JComboBox<>(subcategories.toArray(new Subcategory[subcategories.size()]));
		
		if(url != null) {
			idField.setText(String.valueOf(url.getID()));
			titleField.setText(url.getTitle());
			urlField.setText(url.getUrl());
			
			if(url.getDescription() != null) descriptionField.setText(url.getDescription());
			
			Subcategory current = null;
			for(Subcategory sub : subcategories) {
				if(url.getCategory().getID() == sub.getID()) {
					current = sub;
					break;
				}
			}
			
			selectSubcategoryField.setSelectedItem(current);
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
		add(titleLabel, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.weightx = 0.95;
		add(titleField, constraints);
		
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.weightx = 0.05;
		add(urlLabel, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.weightx = 0.95;
		add(urlField, constraints);
		
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.weightx = 0.05;
		add(descriptionLabel, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.weightx = 0.95;
		add(new JScrollPane(descriptionField), constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.weightx = 0.05;
		add(selectSubcategoryLabel, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.weightx = 0.95;
		add(selectSubcategoryField, constraints);
	}
	
	public boolean isDescriptionEmpty() {
		return descriptionField.getText() == null || descriptionField.getText().trim().isEmpty();
	}
	
	public String getTitle() {
		return titleField.getText();
	}
	
	public String getUrl() {
		return urlField.getText();
	}
	
	public String getDescription() {
		return descriptionField.getText();
	}
	
	public Subcategory getSubcategory() {
		return (Subcategory) selectSubcategoryField.getSelectedItem();
	}
}
