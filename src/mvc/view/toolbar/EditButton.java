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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mvc.dao.DAOFactory;
import mvc.model.Category;
import mvc.model.Subcategory;
import mvc.model.Url;
import mvc.observer.category.CategoryEditListener;
import mvc.observer.category.CategoryEditSubject;
import mvc.observer.category.CategorySelectListener;
import mvc.observer.url.UrlEditListener;
import mvc.observer.url.UrlEditSubject;
import mvc.observer.url.UrlSelectListener;

public final class EditButton extends JButton implements ActionListener, CategorySelectListener, CategoryEditSubject, UrlSelectListener, UrlEditSubject {
	private static final long serialVersionUID = 2452055567420326318L;
	private static final Logger log = LoggerFactory.getLogger(EditButton.class);
	private static final String iconName = "toolbar_edit.png";
	
	private List<CategoryEditListener> categoryEditListeners = new LinkedList<>();
	private List<UrlEditListener> urlEditListeners = new LinkedList<>();
	private List<Category> selectedCategories = null;
	private List<Subcategory> selectedSubcategories = null;
	private List<Url> selectedUrls = null;
	
	public EditButton(Dimension size) {
		log.info("Initialize edit button");
		
		initializeSize(size);
		initializeIcon();
		
		setEnabled(false);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		log.debug("Pressed button");
		
		if(selectedUrls != null) editUrls(selectedUrls);
		else if(selectedSubcategories != null) editSubcategories(selectedSubcategories);
		else if(selectedCategories != null) editCategories(selectedCategories);
		else {
			log.warn("Unwanted behaviour, edit button should be disabled if everything is unselected");
		}
	}

	@Override
	public void onSelectCategory(List<Category> categories) {
		log.debug("Categories selected");
		setEnabled(true);
		selectedCategories = categories;
	}

	@Override
	public void onSelectSubcategory(List<Subcategory> subcategories) {
		log.debug("Subcategories selected");
		setEnabled(true);
		selectedSubcategories = subcategories;
	}

	@Override
	public void onUnselectAllCategories() {
		log.debug("All categories unselected");
		selectedCategories = null;
		selectedSubcategories = null;
		
		setEnabled(false);
	}
	
	@Override
	public void onSelectUrl(List<Url> urls) {
		log.debug("Urls selected");
		setEnabled(true);
		selectedUrls = urls;
	}

	@Override
	public void onUnselectAllUrls() {
		log.debug("All urls unselected");
		selectedUrls = null;
		
		if(selectedCategories == null && selectedSubcategories == null) setEnabled(false);
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
		log.warn("Unwanted behaviour, edit button shouldn't delete categories");
	}

	@Override
	public void deleteSubcategories(List<Subcategory> subcategories) {
		log.warn("Unwanted behaviour, edit button shouldn't delete subcategories");
	}

	@Override
	public void addCategory(Category category) {
		log.warn("Unwanted behaviour, edit button shouldn't add new categories");
	}

	@Override
	public void addSubcategory(Subcategory subcategory) {
		log.warn("Unwanted behaviour, edit button shouldn't delete subcategories");
	}

	@Override
	public void editCategories(List<Category> categories) {
		log.debug("Edit categories");
			
		for(Category category : categories) {
			log.debug("Edit category: ID={}, name={}", category.getID(), category.getName());
			
			JLabel idLabel = new JLabel("ID");
			JTextField id = new JTextField(String.valueOf(category.getID()));
			id.setEnabled(false);
			
			JLabel catNameLabel = new JLabel("Name");
			JTextField catName = new JTextField(category.getName());
			
			JPanel panel = new JPanel(new GridLayout(0, 1));
			panel.add(idLabel);
			panel.add(id);
			panel.add(catNameLabel);
			panel.add(catName);
			
			int result = JOptionPane.showConfirmDialog(this, panel, "Edit category", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			
			if(result == JOptionPane.OK_OPTION) {
				category.setName(catName.getText());
			}
			else {
				log.debug("Edit category canceled");
			}
		}
		
		for(CategoryEditListener listener : categoryEditListeners) listener.onCategoryEdit(categories);
	}

	@Override
	public void editSubcategories(List<Subcategory> subcategories) {
		log.debug("Edit subcategories");
		
		for(Subcategory subcategory : subcategories) {
			log.debug("Edit subcategory: ID={}, name={}", subcategory.getID(), subcategory.getName());
			
			JLabel idLabel = new JLabel("ID");
			JTextField id = new JTextField(String.valueOf(subcategory.getID()));
			id.setEnabled(false);
			
			JLabel subcatNameLabel = new JLabel("Name");
			JTextField subcatName = new JTextField(subcategory.getName());
			
			JLabel catSelectLabel = new JLabel("Select category");
			List<Category> catList = DAOFactory.get().getMainCategory().getAll();
			JComboBox<Category> catSelect = new JComboBox<>(catList.toArray(new Category[catList.size()]));
			Category current = null;
			for(Category cat : catList) {
				if(subcategory.getParent().getID() == cat.getID()) {
					current = cat;
					break;
				}
			}
			catSelect.setSelectedItem(current);
			
			JPanel panel = new JPanel(new GridLayout(0, 1));
			panel.add(idLabel);
			panel.add(id);
			panel.add(catSelectLabel);
			panel.add(catSelect);
			panel.add(subcatNameLabel);
			panel.add(subcatName);
			
			int result = JOptionPane.showConfirmDialog(this, panel, "Edit subcategory", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			
			if(result == JOptionPane.OK_OPTION) {
				log.debug("Edit subcategories");
				
				subcategory.setName(subcatName.getText());
				subcategory.setParent((Category) catSelect.getSelectedItem());
			}
			else {
				log.debug("Add new subcategory canceled");
			}
		}
		
		for(CategoryEditListener listener : categoryEditListeners) listener.onSubcategoryEdit(subcategories);
	}

	@Override
	public void addUrlEditListener(UrlEditListener listener) {
		log.debug("Add new listener");
		urlEditListeners.add(listener);
	}

	@Override
	public void removeUrlEditListener(UrlEditListener listener) {
		log.debug("Remove listener");
		urlEditListeners.remove(listener);
	}

	@Override
	public void deleteUrls(List<Url> urls) {
		log.warn("Unwanted behaviour, edit button shouldn't delete urls");
	}

	@Override
	public void addUrl(Url url) {
		log.warn("Unwanted behaviour, edit button shouldn't add new urls");
	}

	@Override
	public void editUrls(List<Url> urls) {
		log.debug("Edit urls");
		
		for(Url url : urls) {
			log.debug("Edit subcategory: ID={}, title={}, url={}", url.getID(), url.getTitle(), url.getUrl());
			
			JLabel idLabel = new JLabel("ID");
			JTextField id = new JTextField(String.valueOf(url.getID()));
			id.setEnabled(false);
			
			JLabel titleLabel = new JLabel("Title");
			JTextField title = new JTextField();
			
			JLabel urlLabel = new JLabel("Url");
			JTextField urlField = new JTextField();
			
			JLabel descriptionLabel = new JLabel("Description");
			JTextArea description = new JTextArea();
			
			JLabel subcategoryLabel = new JLabel("Select subcategory");
			List<Subcategory> subList = DAOFactory.get().getCategory().getAll();
			JComboBox<Subcategory> subcategory = new JComboBox<>(subList.toArray(new Subcategory[subList.size()]));
			Subcategory current = null;
			for(Subcategory cat : subList) {
				if(url.getCategory().getID() == cat.getID()) {
					current = cat;
					break;
				}
			}
			subcategory.setSelectedItem(current);
			
			JPanel panel = new JPanel(new GridLayout(0, 1));
			panel.add(titleLabel);
			panel.add(title);
			panel.add(urlLabel);
			panel.add(urlField);
			panel.add(descriptionLabel);
			panel.add(description);
			panel.add(subcategoryLabel);
			panel.add(subcategory);
			
			int result = JOptionPane.showConfirmDialog(this, panel, "Edit url", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		}
	}
}
