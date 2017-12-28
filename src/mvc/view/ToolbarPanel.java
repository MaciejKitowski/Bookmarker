package mvc.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ToolbarPanel extends JPanel {
	private static final long serialVersionUID = 1071937589387532800L;
	private static final Logger log = LoggerFactory.getLogger(ToolbarPanel.class);
	
	public ToolbarPanel(int width, int height) {
		log.info("Initialize toolbar");
		
		setBackground(Color.CYAN);
		setPreferredSize(new Dimension(width, height));
		setBorder(new TitledBorder("Toolbar"));
	}
}
