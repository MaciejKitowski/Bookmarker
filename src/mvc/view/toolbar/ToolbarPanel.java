package mvc.view.toolbar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ToolbarPanel extends JToolBar {
	private static final long serialVersionUID = 1071937589387532800L;
	private static final Logger log = LoggerFactory.getLogger(ToolbarPanel.class);
	
	private final Dimension buttonSize;
	private JButton addButton = null;
	private JButton editButton = null;
	private JButton deleteButton = null;
	private JButton databaseButton = null;
	private SelectDatabaseButton selectDb = null;
	
	public ToolbarPanel(int width, int height) {
		log.info("Initialize toolbar");
		
		buttonSize = new Dimension(height - 5, height - 5);
		
		setBackground(Color.CYAN);
		setPreferredSize(new Dimension(width, height));
		setFloatable(false);
		setBorder(BorderFactory.createEmptyBorder());

		addButton = generateButton("toolbar_addnew.png", 10);
		editButton = generateButton("toolbar_edit.png", 5);
		deleteButton = generateButton("toolbar_delete.png", 5);
		
		add(Box.createHorizontalStrut(30));
		selectDb = new SelectDatabaseButton(buttonSize);
		add(selectDb);
		
		
		//databaseButton = generateButton("toolbar_database.png", 30);
		//selectDb = (SelectDatabaseButton) generateButton("toolbar_database.png", 30);
		
		initializeButtonListeners();
	}
		
	private JButton generateButton(String iconName, int spaceBefore) {
		log.debug("Initialize button with {} icon image", iconName);
		
		try {
			URL icon = getClass().getResource(String.format("/icons/%s", iconName));
			JButton button = new JButton(new ImageIcon(icon));
			
			button.setPreferredSize(buttonSize);
			button.setMaximumSize(buttonSize);
			button.setMinimumSize(buttonSize);
			button.setSize(buttonSize);
			
			add(Box.createHorizontalStrut(spaceBefore));
			add(button);
			
			return button;
		}
		catch(Exception ex) {
			log.error("Failed to generate button", ex);
			return null;
		}
	}
	
	private void initializeButtonListeners() {
		log.debug("Initialize button listeners");
		
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				log.debug("Pressed add new button");
				
				JPopupMenu popup = new JPopupMenu();
				
				popup.add(new JMenuItem("Category"));
				popup.add(new JMenuItem("Subcategory"));
				popup.addSeparator();
				popup.add(new JMenuItem("Url"));
				
				popup.show(addButton, 0, 25);
			}
		});
		
		editButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				log.debug("Pressed edit button");
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				log.debug("Pressed delete button");
			}
		});
		
		/*databaseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				log.debug("Pressed database selection button");
				
				JPopupMenu popup = new JPopupMenu();
				
				popup.add(new JMenuItem("SqLite"));
				popup.add(new JMenuItem("My-SQL"));
				popup.add(new JMenuItem("PostgreSQL"));
				
				popup.show(databaseButton, 0, 25);
			}
		});*/
	}
}
