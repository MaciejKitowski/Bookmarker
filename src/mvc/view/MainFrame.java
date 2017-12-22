package mvc.view;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MainFrame extends JFrame {
	private static final long serialVersionUID = 4785143357028575468L;
	private static final Logger log = LoggerFactory.getLogger(MainFrame.class);
	private String windowTitle = "Bookmarker";
	private int defaultWidth = 800;
	private int defaultHeight = 600;
	
	public MainFrame() {
		log.info("Initialize Main Frame with title: {} and size: {}x{}", windowTitle, defaultWidth, defaultHeight);
		
		setTitle(windowTitle);
		setSize(defaultWidth, defaultHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
