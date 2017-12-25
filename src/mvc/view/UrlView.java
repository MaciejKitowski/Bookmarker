package mvc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class UrlView extends JPanel {
	private static final long serialVersionUID = -4908801645938833417L;
	private static final Logger log = LoggerFactory.getLogger(UrlView.class);
	private static final String[] columnNames = {"ID", "Title", "Url", "Description"};
	
	private DefaultTableModel tableModel = null;
	private JTable table = null;
	private JScrollPane tableScroll = null;
	
	public UrlView(int width, int height) {
		log.info("Initialize Url view");
		
		setBackground(Color.GREEN);
		setPreferredSize(new Dimension(width, height));
		setBorder(new TitledBorder("Urls"));
		setLayout(new BorderLayout(5,5));
				
		initializeTable();
		setTableStyle();
		add(tableScroll, BorderLayout.CENTER);
	}
	
	private void initializeTable() {
		log.debug("Initialize table");
		
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);
		tableScroll = new JScrollPane(table);
	}
	
	private void setTableStyle() {
		log.debug("Set table style");
		
		table.setFont(new Font("Arial", Font.PLAIN, 26));
		table.setRowHeight(table.getRowHeight() + 16);
		table.setAutoCreateRowSorter(true);
	}
}
