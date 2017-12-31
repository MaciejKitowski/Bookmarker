package mvc.view.toolbar;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JToolBar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ToolbarPanel extends JToolBar {
	private static final long serialVersionUID = 1071937589387532800L;
	private static final Logger log = LoggerFactory.getLogger(ToolbarPanel.class);
	
	private final Dimension buttonSize;
	private SelectDatabaseButton selectDb = null;
	private DeleteButton delete = null;
	private AddNewButton addNew = null;
	private EditButton edit = null;
	
	public ToolbarPanel(int width, int height) {
		log.info("Initialize toolbar");
		
		buttonSize = new Dimension(height - 5, height - 5);
		
		setBackground(Color.CYAN);
		setPreferredSize(new Dimension(width, height));
		setFloatable(false);
		setBorder(BorderFactory.createEmptyBorder());

		add(Box.createHorizontalStrut(5));
		addNew = new AddNewButton(buttonSize);
		add(addNew);
		
		add(Box.createHorizontalStrut(5));
		edit = new EditButton(buttonSize);
		add(edit);
		
		add(Box.createHorizontalStrut(5));
		delete = new DeleteButton(buttonSize);
		add(delete);
		
		add(Box.createHorizontalStrut(30));
		selectDb = new SelectDatabaseButton(buttonSize);
		add(selectDb);
	}
			
	public SelectDatabaseButton getSelectDatabaseButton() {
		return selectDb;
	}
	
	public DeleteButton getDeleteButton() {
		return delete;
	}
	
	public AddNewButton getAddNewButton() {
		return addNew;
	}
}
