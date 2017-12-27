package mvc.view.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mvc.model.Url;

public final class UrlTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 3938608858574512836L;
	private static final Logger log = LoggerFactory.getLogger(UrlTableModel.class);
	private static final String[] columnNames = {"ID", "Title", "Url", "Description"};
	
	private List<Url> urls = new ArrayList<>();
	
	public UrlTableModel() {
		log.info("Initialize model");
		
		fireTableStructureChanged();
	}
	
	public void setList(List<Url> urls) {
		log.debug("Set new list with {} values", urls.size());
		this.urls = urls;
		
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return urls.size();
	}
	@Override
	public String getColumnName(int col) {
	    return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		Url url = urls.get(row);
		Object toReturn = null;

		if(col == 0) toReturn = url.getID();
		else if(col == 1) toReturn = url.getTitle();
		else if(col == 2) toReturn = url.getUrl();
		else if(col == 3) toReturn = url.getDescription();
		
		return toReturn;
	}

}
