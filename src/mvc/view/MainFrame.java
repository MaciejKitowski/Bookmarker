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
	}
	
	private void initializeLayout() {
		log.debug("Initialize layout");
		
		layout = new BorderLayout();
		layout.setHgap(layoutHorizontalGap);
		layout.setVgap(layoutVerticalGap);
		
		setLayout(layout);
		
		JPanel test = new JPanel();
		test.setPreferredSize(new Dimension(200, 100));
		test.setBorder(new TitledBorder("Test 1"));
		test.setBackground(Color.BLUE);
		
		JPanel tesat = new JPanel();
		tesat.setSize(500, 400);
		tesat.setPreferredSize(new Dimension(200, 100));
		tesat.setBorder(new TitledBorder("Test 2"));
		tesat.setBackground(Color.GREEN);
		
		
		add(test, BorderLayout.LINE_START);
		add(tesat, BorderLayout.AFTER_LAST_LINE);
	}
}
