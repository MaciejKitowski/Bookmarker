package mvc.view.toolbar;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mvc.model.Category;
import mvc.model.Subcategory;
import mvc.model.Url;
import mvc.observer.category.CategorySelectListener;
import mvc.observer.url.UrlSelectListener;

public final class DeleteButton extends JButton implements ActionListener, CategorySelectListener, UrlSelectListener {
	private static final long serialVersionUID = 5739651433521986611L;
	private static final Logger log = LoggerFactory.getLogger(DeleteButton.class);
	private static final String iconName = "toolbar_delete.png";
	
	public DeleteButton(Dimension size) {
		log.info("Create delete button");
		
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
	}

	@Override
	public void onSelectCategory(List<Category> categories) {
		log.debug("Categories selected");
		setEnabled(true);
	}

	@Override
	public void onSelectSubcategory(List<Subcategory> subcategories) {
		log.debug("Subcategories selected");
		setEnabled(true);
	}

	@Override
	public void onUnselectAllCategories() {
		log.debug("All categories unselected");
		//setEnabled(false);
	}

	@Override
	public void onSelectUrl(List<Url> urls) {
		log.debug("Urls selected");
		setEnabled(true);
	}

	@Override
	public void onUnselectAllUrls() {
		log.debug("All urls unselected");
		//setEnabled(false);
	}
}
