package test.dao.Category;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import mvc.dao.SqliteFactory;
import mvc.dao.Category.ICategoryDAO;
import mvc.dao.Category.SqliteCategory;

public class SqliteCategoryTest {
	private List<String> getTableNames() throws Exception {
		Connection connection = SqliteFactory.getConnection();
		DatabaseMetaData meta = connection.getMetaData();
		List<String> buffer = new ArrayList<>();
		
		ResultSet rs = meta.getTables(null, null, "%", null);
		while(rs.next()) buffer.add(rs.getString(3));
		
		rs.close();
		connection.close();
		
		return buffer;
	}
	
	@Test
	public void createTableTest() throws Exception {
		ICategoryDAO dao = new SqliteCategory();
		dao.createTable();
		
		List<String> tables = getTableNames();
		
		assertTrue(tables.contains("MainCategory"));
	}
}
