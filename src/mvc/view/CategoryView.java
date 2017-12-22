package mvc.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mvc.Main;

public final class CategoryView extends JPanel {
	private static final long serialVersionUID = 8970054597563459574L;
	private static final Logger log = LoggerFactory.getLogger(CategoryView.class);
	
	public CategoryView(int width, int height) {
		log.info("Initialize Category view");
		
		setBackground(Color.CYAN);
		setPreferredSize(new Dimension(width, height));
		setBorder(new TitledBorder("Toolbar"));
	}
}
