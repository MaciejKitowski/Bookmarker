package mvc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mvc.controller.observer.url.UrlChangedListener;
import mvc.model.Url;

public final class UrlView extends JPanel implements UrlChangedListener {
	private static final long serialVersionUID = -4908801645938833417L;
	private static final Logger log = LoggerFactory.getLogger(UrlView.class);
	private static final String[] columnNames = {"ID", "Title", "Url", "Description"};
	private static final int rowHeight = 20;
	
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
		setTableColumnsSize();
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
		table.setRowHeight(rowHeight);
		table.setAutoCreateRowSorter(true);
		
		tableScroll.getViewport().setBackground(Color.GREEN);
	}
	
	private void setTableColumnsSize() {
		log.debug("Set table columns size");
		
		setColumnWidth(columnNames[0], 30, 60);
		setColumnWidth(columnNames[1], 100, 300);
		setColumnWidth(columnNames[2], 100, 300);
		setColumnWidth(columnNames[3], 10, 200);
	}
	
	private void setColumnWidth(String name, int min, int prefered) {
		log.debug("Set width for column: {} (min={}, prefered={})", name, min, prefered);
		
		table.getColumn(name).setMinWidth(min);
		table.getColumn(name).setPreferredWidth(prefered);
	}

	@Override
	public void onUrlChanged(List<Url> urls) {
		log.debug("Received {} urls", urls.size());
		
		for(Url url : urls) {
			log.debug("{} {} {} {}", url.getID(), url.getTitle(), url.getUrl(), url.getDescription());
		}
		
		refreshTable(urls);
	}
	
	//TODO Change sort order cause exception
	private void refreshTable(List<Url> urls) {
		if(urls.size() == 0) tableModel.setRowCount(0);
		else {
			tableModel.getDataVector().clear();
			
			for(Url url : urls) {
				tableModel.addRow(new Object[] {url.getID(), url.getTitle(), url.getUrl(), url.getDescription()});
			}
		}
	}
}
