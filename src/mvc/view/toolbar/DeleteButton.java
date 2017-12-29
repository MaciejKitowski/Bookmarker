package mvc.view.toolbar;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DeleteButton extends JButton implements ActionListener {
	private static final long serialVersionUID = 5739651433521986611L;
	private static final Logger log = LoggerFactory.getLogger(SelectDatabaseButton.class);
	private static final String iconName = "toolbar_delete.png";
	
	public DeleteButton(Dimension size) {
		log.info("Create delete button");
				
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		log.debug("Pressed button");
	}
}
