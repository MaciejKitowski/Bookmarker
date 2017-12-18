package test.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import mvc.dao.DAOFactory;

public class DAOutils {
	private DAOutils() {}
	
	public static List<String> getTableNames(int database) throws Exception {
		Connection connection = DAOFactory.get(database).createConnection();
		DatabaseMetaData meta = connection.getMetaData();
		List<String> buffer = new ArrayList<>();
		
		ResultSet rs = meta.getTables(null, null, "%", null);
		while(rs.next()) buffer.add(rs.getString(3));
		
		rs.close();
		connection.close();
		
		return buffer;
	}
}
