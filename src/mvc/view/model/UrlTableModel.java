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

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return urls.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Url url = urls.get(row);
		Object toReturn = null;

		
		return toReturn;
	}

}
