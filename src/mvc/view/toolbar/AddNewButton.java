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
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
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
import mvc.observer.url.UrlEditListener;
import mvc.observer.url.UrlEditSubject;

public final class AddNewButton extends JButton implements ActionListener, CategoryEditSubject, UrlEditSubject {
	private static final long serialVersionUID = 3821107696744652502L;
	private static final Logger log = LoggerFactory.getLogger(AddNewButton.class);
	private static final String iconName = "toolbar_addnew.png";
	
	private List<CategoryEditListener> categoryEditListeners = new LinkedList<>();
	private List<UrlEditListener> urlEditListeners = new LinkedList<>();
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
				else if(source.equalsIgnoreCase("subcat")) addNewSubcategory();
				else if(source.equalsIgnoreCase("url")) addNewUrl();
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
	
	private void addNewSubcategory() {
		log.debug("Add new subcategory");
		
		JLabel subcatNameLabel = new JLabel("Name");
		JTextField subcatName = new JTextField();
		
		JLabel catSelectLabel = new JLabel("Select category");
		List<Category> catList = DAOFactory.get().getMainCategory().getAll();
		JComboBox<Category> catSelect = new JComboBox<>(catList.toArray(new Category[catList.size()]));
		
		JPanel panel = new JPanel(new GridLayout(0, 1));
		panel.add(catSelectLabel);
		panel.add(catSelect);
		panel.add(subcatNameLabel);
		panel.add(subcatName);
		
		int result = JOptionPane.showConfirmDialog(this, panel, "Add new subcategory", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		
		if(result == JOptionPane.OK_OPTION) {
			log.debug("Add new subcategory with name: {}", subcatNameLabel.getText());
			addSubcategory(new Subcategory(subcatName.getText(), (Category) catSelect.getSelectedItem()));
		}
		else {
			log.debug("Add new subcategory canceled");
		}
	}
	
	private void addNewUrl() {
		log.debug("Add new url");
		
		JLabel titleLabel = new JLabel("Title");
		JTextField title = new JTextField();
		
		JLabel urlLabel = new JLabel("Url");
		JTextField url = new JTextField();
		
		JLabel descriptionLabel = new JLabel("Description");
		JTextArea description = new JTextArea();
		
		JLabel subcategoryLabel = new JLabel("Select subcategory");
		List<Subcategory> subList = DAOFactory.get().getCategory().getAll();
		JComboBox<Subcategory> subcategory = new JComboBox<>(subList.toArray(new Subcategory[subList.size()]));
		
		JPanel panel = new JPanel(new GridLayout(0, 1));
		panel.add(titleLabel);
		panel.add(title);
		panel.add(urlLabel);
		panel.add(url);
		panel.add(descriptionLabel);
		panel.add(description);
		panel.add(subcategoryLabel);
		panel.add(subcategory);
		
		int result = JOptionPane.showConfirmDialog(this, panel, "Add new url", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		
		if(result == JOptionPane.OK_OPTION) {
			log.debug("Add new url: title={} url={} subcategory: name={}", title.getText(), url.getText(), ((Subcategory)(subcategory.getSelectedItem())).getName());
			Subcategory subcat = (Subcategory) subcategory.getSelectedItem();
			
			if(!description.getText().trim().isEmpty()) addUrl(new Url(url.getText(), title.getText(), description.getText(), subcat));
			else  addUrl(new Url(url.getText(), title.getText(), subcat));
		}
		else {
			log.debug("Add new url canceled");
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

	@Override
	public void addSubcategory(Subcategory subcategory) {
		log.debug("Add new subcategory with name: {}", subcategory.getName());
		for(CategoryEditListener listener : categoryEditListeners) listener.onSubcategoryAdd(subcategory);
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
		log.warn("Unwanted behaviour, add new button shouldn't delete urls");
	}

	@Override
	public void addUrl(Url url) {
		log.debug("Add new url: title={} url={}", url.getTitle(), url.getUrl());
		for(UrlEditListener listener : urlEditListeners) listener.onUrlAdd(url);
	}
}
