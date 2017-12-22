package mvc.view;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MainFrame extends JFrame {
	private static final Logger log = LoggerFactory.getLogger(MainFrame.class);
	private String frameName = "Bookmarker";
	private int defaultWidth = 800;
	private int defaultHeight = 600;
	
	public MainFrame() {
		log.info("Initialize Main Frame");
		
		setTitle(frameName);
		setSize(defaultWidth, defaultHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
