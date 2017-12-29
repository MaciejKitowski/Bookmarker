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
import mvc.observer.category.CategorySelectListener;

public final class DeleteButton extends JButton implements ActionListener, CategorySelectListener {
	private static final long serialVersionUID = 5739651433521986611L;
	private static final Logger log = LoggerFactory.getLogger(DeleteButton.class);
	private static final String iconName = "toolbar_delete.png";
	
	public DeleteButton(Dimension size) {
		log.info("Create delete button");
		
		initializeSize(size);
		initializeIcon();
				
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
		
	}

	@Override
	public void onSelectSubcategory(List<Subcategory> subcategories) {
		log.debug("Subcategories selected");
	}

	@Override
	public void onUnselectAll() {
		log.debug("All categories unselected");
		
	}
}
