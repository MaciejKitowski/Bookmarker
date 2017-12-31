package mvc.view.toolbar;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mvc.model.Category;
import mvc.model.Subcategory;
import mvc.model.Url;
import mvc.observer.category.CategoryEditListener;
import mvc.observer.category.CategoryEditSubject;
import mvc.observer.category.CategorySelectListener;

public final class EditButton extends JButton implements ActionListener, CategorySelectListener, CategoryEditSubject {
	private static final long serialVersionUID = 2452055567420326318L;
	private static final Logger log = LoggerFactory.getLogger(EditButton.class);
	private static final String iconName = "toolbar_edit.png";
	
	private List<CategoryEditListener> categoryEditListeners = new LinkedList<>();
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
		
		if(selectedUrls != null) log.warn("Url edition not implemented yet");
		else if(selectedSubcategories != null) log.warn("Subcategory edition not implemented yet");
		else if(selectedCategories != null) log.warn("Category edition not implemented yet");
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editSubcategories(List<Subcategory> subcategories) {
		// TODO Auto-generated method stub
		
	}
}
