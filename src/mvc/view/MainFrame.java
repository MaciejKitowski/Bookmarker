package mvc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MainFrame extends JFrame {
	private static final long serialVersionUID = 4785143357028575468L;
	private static final Logger log = LoggerFactory.getLogger(MainFrame.class);
	
	private final String windowTitle = "Bookmarker";
	private final int defaultWidth = 800;
	private final int defaultHeight = 600;
	
	private BorderLayout layout = null;
	private final int layoutHorizontalGap = 5;
	private final int layoutVerticalGap = 5;
	
	public MainFrame() {
		log.info("Initialize Main Frame with title: {} and size: {}x{}", windowTitle, defaultWidth, defaultHeight);
		
		setTitle(windowTitle);
		setSize(defaultWidth, defaultHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initializeLayout();
		testPanels();
	}
	
	private void initializeLayout() {
		log.debug("Initialize layout");
		
		layout = new BorderLayout();
		layout.setHgap(layoutHorizontalGap);
		layout.setVgap(layoutVerticalGap);
		
		setLayout(layout);
	}
	
	private void testPanels() {
		JPanel toolbar = new JPanel();
		toolbar.setBackground(Color.CYAN);
		toolbar.setPreferredSize(new Dimension(defaultWidth, 50));
		toolbar.setBorder(new TitledBorder("Toolbar"));
		
		JPanel categories = new JPanel();
		categories.setBackground(Color.LIGHT_GRAY);
		categories.setPreferredSize(new Dimension(100, defaultHeight));
		categories.setBorder(new TitledBorder("Categories"));
		
		JPanel urls = new JPanel();
		urls.setBackground(Color.GREEN);
		urls.setPreferredSize(new Dimension(400, defaultHeight));
		urls.setBorder(new TitledBorder("Urls"));
		
		add(toolbar, BorderLayout.PAGE_START);
		add(categories, BorderLayout.LINE_START);
		add(urls, BorderLayout.CENTER);
	}
}
