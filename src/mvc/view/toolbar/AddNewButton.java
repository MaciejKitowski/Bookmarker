package mvc.view.toolbar;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mvc.model.Category;
import mvc.model.Subcategory;
import mvc.observer.category.CategoryEditListener;
import mvc.observer.category.CategoryEditSubject;

public final class AddNewButton extends JButton implements ActionListener, CategoryEditSubject {
	private static final long serialVersionUID = 3821107696744652502L;
	private static final Logger log = LoggerFactory.getLogger(AddNewButton.class);
	private static final String iconName = "toolbar_addnew.png";
	
	private List<CategoryEditListener> categoryEditListeners = new LinkedList<>();
	private JPopupMenu popup = null;
	
	public AddNewButton(Dimension size) {
		log.info("Initialize add new button");
		
		initializeSize(size);
		initializeIcon();
		initializePopup();
		
		addActionListener(this);
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
	
	private void initializePopup() {
		log.debug("Initialize popup");
		
		ActionListener optionSelectedAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String source = e.getActionCommand();
				log.debug("Selected option: {}", source);
				
				if(source.equalsIgnoreCase("cat")) addNewCategory();
			}
		};
		
		JMenuItem cat = new JMenuItem("Category");
		cat.setActionCommand("cat");
		cat.addActionListener(optionSelectedAction);
		
		JMenuItem subcat = new JMenuItem("Subcategory");
		subcat.setActionCommand("subcat");
		subcat.addActionListener(optionSelectedAction);
		
		JMenuItem url = new JMenuItem("Url");
		url.setActionCommand("url");
		url.addActionListener(optionSelectedAction);
		
		popup = new JPopupMenu();
		popup.add(cat);
		popup.add(subcat);
		popup.addSeparator();
		popup.add(url);
	}
	
	private void addNewCategory() {
		log.debug("Add new category");
		
		JLabel catNameLabel = new JLabel("Name");
		JTextField catName = new JTextField();
		
		JPanel panel = new JPanel(new GridLayout(0, 1));
		panel.add(catNameLabel);
		panel.add(catName);
		
		int result = JOptionPane.showConfirmDialog(this, panel, "Add new category", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		
		if(result == JOptionPane.OK_OPTION) {
			log.debug("Add new category with name: {}", catName.getText());
			addCategory(new Category(catName.getText()));
		}
		else {
			log.debug("Add new category canceled");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		log.debug("Pressed button");	
		popup.show(this, 0, 25);
	}

	@Override
	public void addCategoryEditListener(CategoryEditListener listener) {
		log.debug("Add new listener");
		categoryEditListeners.add(listener);
	}

	@Override
	public void removeCategoryEditListener(CategoryEditListener listener) {
		log.debug("Remove listener");
		categoryEditListeners.remove(listener);
	}

	@Override
	public void deleteCategories(List<Category> categories) {
		log.warn("Unwanted behaviour, add new button shouldn't delete categories");
	}

	@Override
	public void deleteSubcategories(List<Subcategory> subcategories) {
		log.warn("Unwanted behaviour, add new button shouldn't delete subcategories");
	}

	@Override
	public void addCategory(Category category) {
		log.debug("Add new category with name: {}", category.getName());
		for(CategoryEditListener listener : categoryEditListeners) listener.onCategoryAdd(category);
	}
}
