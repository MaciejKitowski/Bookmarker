package mvc.view.toolbar.button;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	private JLabel idLabel = new JLabel("ID");
	private JLabel titleLabel = new JLabel("Title");
	private JLabel urlLabel = new JLabel("Url");
	private JLabel descriptionLabel = new JLabel("Description");
	private JLabel selectSubcategoryLabel = new JLabel("Select subcategory");
	private JTextField idField = new JTextField();
	private JTextField titleField = new JTextField();
	private JTextField urlField = new JTextField();
	private JTextArea descriptionField = new JTextArea();
	private JComboBox<Subcategory> selectSubcategoryField = null;
	
	public UrlPanel() {
		log.info("Initialize url panel");
		
		displayID = false;
		initializePanel();
	}
	
	public UrlPanel(Url url) {
		log.info("Initialize url panel with values");
		
		displayID = true;
		this.url = url;
		initializePanel();
	}
	
	private void initializePanel() {
		log.debug("Initialize panel");
		
		setLayout(new GridLayout(0, 1));
		
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
			idField.setEnabled(false);
			add(idLabel);
			add(idField);
		}
		
		add(titleLabel);
		add(titleField);
		add(urlLabel);
		add(urlField);
		add(descriptionLabel);
		add(descriptionField);
		add(selectSubcategoryLabel);
		add(selectSubcategoryField);
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
