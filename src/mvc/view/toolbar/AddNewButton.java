package mvc.view.toolbar;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AddNewButton extends JButton implements ActionListener {
	private static final long serialVersionUID = 3821107696744652502L;
	private static final Logger log = LoggerFactory.getLogger(AddNewButton.class);
	private static final String iconName = "toolbar_addnew.png";
	
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

	@Override
	public void actionPerformed(ActionEvent e) {
		log.debug("Pressed button");	
		popup.show(this, 0, 25);
	}
}
