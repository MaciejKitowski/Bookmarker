package mvc.view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ToolbarPanel extends JPanel {
	private static final long serialVersionUID = 1071937589387532800L;
	private static final Logger log = LoggerFactory.getLogger(ToolbarPanel.class);
	private static final int spaceBetweenComponent = 10;
	
	public ToolbarPanel(int width, int height) {
		log.info("Initialize toolbar");
		
		setBackground(Color.CYAN);
		setPreferredSize(new Dimension(width, height));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		add(new JButton("1"));
		add(Box.createHorizontalStrut(spaceBetweenComponent));
		add(new JButton("2"));
		add(Box.createHorizontalStrut(spaceBetweenComponent));
		add(new JButton("3"));
	}
}
