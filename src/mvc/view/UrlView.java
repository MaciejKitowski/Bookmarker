package mvc.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class UrlView extends JPanel {
	private static final long serialVersionUID = -4908801645938833417L;
	private static final Logger log = LoggerFactory.getLogger(UrlView.class);
	
	public UrlView(int width, int height) {
		log.info("Initialize Url view");
		
		setBackground(Color.GREEN);
		setPreferredSize(new Dimension(width, height));
		setBorder(new TitledBorder("Urls"));
	}
}
