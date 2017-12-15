package test.dao.Url;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import mvc.dao.PostgresFactory;
import mvc.dao.SqliteFactory;
import mvc.dao.Url.IUrlDAO;
import mvc.dao.Url.PostgresUrl;
import mvc.dao.Url.SqliteUrl;

public class PostgresUrlTest {
	private List<String> getTableNames() throws Exception {
		Connection connection = PostgresFactory.getConnection();
		DatabaseMetaData meta = connection.getMetaData();
		List<String> buffer = new ArrayList<>();
		
		ResultSet rs = meta.getTables(null, null, "%", null);
		while(rs.next()) buffer.add(rs.getString(3));
		
		rs.close();
		connection.close();
		
		return buffer;
	}
	
	private int getProfileCount() throws Exception {
		String sql = "SELECT COUNT(*) FROM Url";
		int count = 0;
		Connection connection = PostgresFactory.getConnection();
		Statement statement = connection.createStatement();
		
		ResultSet rs = statement.executeQuery(sql);
		if(rs != null && rs.next()) count = rs.getInt(1);
		
		rs.close();
		statement.close();
		connection.close();
		
		return count;
	}
	
	@Test
	public void createTableTest() throws Exception {
		IUrlDAO dao = new PostgresUrl();
		dao.createTable();
		
		List<String> tables = getTableNames();
		
		assertTrue(tables.contains("url"));
	}
}
