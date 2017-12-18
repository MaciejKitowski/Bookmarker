package test.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mvc.dao.DAOFactory;
import mvc.dao.SqliteFactory;

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
	
	public static int count(String tableName, int database) throws Exception {
		String sql = "SELECT COUNT(*) FROM " + tableName;
		int result = 0;
		Connection connection = DAOFactory.get(database).createConnection();
		Statement statement = connection.createStatement();
		
		ResultSet rs = statement.executeQuery(sql);
		if(rs != null && rs.next()) result = rs.getInt(1);
		
		rs.close();
		statement.close();
		connection.close();
		
		return result;
	}
}
